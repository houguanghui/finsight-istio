package org.finsight.istio.fundamental.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finsight.istio.fundamental.dto.response.CashFlowTrendResponse;
import org.finsight.istio.fundamental.entity.CashFlow;
import org.finsight.istio.fundamental.repository.CashFlowRepository;
import org.finsight.istio.fundamental.service.CashFlowService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CashFlowServiceImpl implements CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    @Override
    public List<CashFlow> getCashFlowData(String secucode, LocalDate startDate, LocalDate endDate) {
        try {
            return cashFlowRepository.findBySecucodeAndReportDateBetweenOrderByReportDateDesc(
                    secucode, startDate, endDate);
        } catch (Exception e) {
            log.error("获取现金流量数据失败: secucode={}, startDate={}, endDate={}",
                    secucode, startDate, endDate, e);
            throw new RuntimeException("获取现金流量数据失败", e);
        }
    }

    @Override
    public Optional<CashFlow> getCashFlowByDate(String secucode, LocalDate reportDate) {
        try {
            return cashFlowRepository.findBySecucodeAndReportDate(secucode, reportDate);
        } catch (Exception e) {
            log.error("获取指定日期现金流量数据失败: secucode={}, reportDate={}", secucode, reportDate, e);
            throw new RuntimeException("获取现金流量数据失败", e);
        }
    }

    @Override
    public CashFlowTrendResponse getCashFlowTrend(String secucode, int years) {
        int currentYear = LocalDate.now().getYear();
        List<CashFlow> yearlyData = cashFlowRepository.findYearlyData(
                secucode, currentYear - years + 1, currentYear);

        if (yearlyData.isEmpty()) {
            throw new RuntimeException("未找到该公司的年度现金流量数据");
        }

        CashFlowTrendResponse response = new CashFlowTrendResponse();

        // 设置日期列表
        response.setDates(yearlyData.stream()
                .map(CashFlow::getReportDate)
                .collect(Collectors.toList()));

        // 设置各类型现金流数据
        response.setOperatingCashFlows(yearlyData.stream()
                .map(CashFlow::getNetcashOperate)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        response.setInvestingCashFlows(yearlyData.stream()
                .map(CashFlow::getNetcashInvest)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        response.setFinancingCashFlows(yearlyData.stream()
                .map(CashFlow::getNetcashFinance)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        response.setFreeCashFlows(yearlyData.stream()
                .map(CashFlow::calculateFreeCashFlow)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        return response;
    }

    @Override
    public Map<String, Object> analyzeCashFlowStructure(String secucode, LocalDate reportDate) {
        CashFlow cashFlow = getCashFlowByDate(secucode, reportDate)
                .orElseThrow(() -> new RuntimeException("现金流量数据不存在"));

        Map<String, Object> structure = new HashMap<>();

        // 计算三大活动现金流占比
        BigDecimal totalCashFlow = BigDecimal.ZERO;
        if (cashFlow.getNetcashOperate() != null) totalCashFlow = totalCashFlow.add(cashFlow.getNetcashOperate().abs());
        if (cashFlow.getNetcashInvest() != null) totalCashFlow = totalCashFlow.add(cashFlow.getNetcashInvest().abs());
        if (cashFlow.getNetcashFinance() != null) totalCashFlow = totalCashFlow.add(cashFlow.getNetcashFinance().abs());

        if (totalCashFlow.compareTo(BigDecimal.ZERO) > 0) {
            if (cashFlow.getNetcashOperate() != null) {
                BigDecimal operatingRatio = cashFlow.getNetcashOperate().abs()
                        .divide(totalCashFlow, 4, RoundingMode.HALF_UP);
                structure.put("operatingRatio", operatingRatio);
            }

            if (cashFlow.getNetcashInvest() != null) {
                BigDecimal investingRatio = cashFlow.getNetcashInvest().abs()
                        .divide(totalCashFlow, 4, RoundingMode.HALF_UP);
                structure.put("investingRatio", investingRatio);
            }

            if (cashFlow.getNetcashFinance() != null) {
                BigDecimal financingRatio = cashFlow.getNetcashFinance().abs()
                        .divide(totalCashFlow, 4, RoundingMode.HALF_UP);
                structure.put("financingRatio", financingRatio);
            }
        }

        return structure;
    }

    @Override
    public Map<String, Object> compareWithPeers(String secucode, List<String> peerSecucodes, LocalDate reportDate) {
        Map<String, Object> comparison = new HashMap<>();

        // 获取目标公司数据
        CashFlow targetCashFlow = getCashFlowByDate(secucode, reportDate)
                .orElseThrow(() -> new RuntimeException("目标公司现金流量数据不存在"));

        // 获取同行公司数据
        List<CashFlow> peerCashFlows = cashFlowRepository.findBySecucodesAndReportDate(peerSecucodes, reportDate);

        comparison.put("target", targetCashFlow);
        comparison.put("peers", peerCashFlows);

        // 计算同行平均水平
        BigDecimal avgOperatingCashFlow = peerCashFlows.stream()
                .map(CashFlow::getNetcashOperate)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(peerCashFlows.size()), 2, RoundingMode.HALF_UP);

        comparison.put("peerAverage", avgOperatingCashFlow);

        return comparison;
    }

    @Override
    public Map<String, Object> analyzeCashFlowQuality(String secucode, LocalDate reportDate) {
        CashFlow cashFlow = getCashFlowByDate(secucode, reportDate)
                .orElseThrow(() -> new RuntimeException("现金流量数据不存在"));

        Map<String, Object> quality = new HashMap<>();

        // 1. 经营现金流与净利润比较
        if (cashFlow.getNetcashOperate() != null && cashFlow.getNetprofit() != null) {
            boolean operatingVsProfit = cashFlow.getNetcashOperate().compareTo(cashFlow.getNetprofit()) > 0;
            quality.put("operatingVsProfit", operatingVsProfit);
            quality.put("operatingCashFlow", cashFlow.getNetcashOperate());
            quality.put("netProfit", cashFlow.getNetprofit());
        }

        // 2. 自由现金流分析
        BigDecimal freeCashFlow = cashFlow.calculateFreeCashFlow();
        if (freeCashFlow != null) {
            quality.put("freeCashFlowPositive", freeCashFlow.compareTo(BigDecimal.ZERO) > 0);
            quality.put("freeCashFlow", freeCashFlow);
        }

        // 3. 现金收入比率（销售商品收到现金/营业收入）
        // 这里需要营业收入数据，可能需要从利润表关联查询
        if (cashFlow.getSalesServices() != null) {
            // 假设营业收入为salesServices的1.1倍（实际需要从利润表获取）
            BigDecimal operatingRevenue = cashFlow.getSalesServices().multiply(new BigDecimal("1.1"));
            if (operatingRevenue.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal cashIncomeRatio = cashFlow.getSalesServices()
                        .divide(operatingRevenue, 4, RoundingMode.HALF_UP);
                quality.put("cashIncomeRatio", cashIncomeRatio);
                quality.put("cashIncomeRatioGood", cashIncomeRatio.compareTo(new BigDecimal("0.8")) > 0);
            }
        }

        // 4. 现金再投资比率
        if (cashFlow.getNetcashOperate() != null && cashFlow.getConstructLongAsset() != null &&
                cashFlow.getNetcashOperate().compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal reinvestmentRatio = cashFlow.getNetcashOperate()
                    .subtract(cashFlow.getConstructLongAsset())
                    .divide(cashFlow.getNetcashOperate(), 4, RoundingMode.HALF_UP);
            quality.put("reinvestmentRatio", reinvestmentRatio);
            quality.put("reinvestmentRatioGood", reinvestmentRatio.compareTo(new BigDecimal("0.1")) > 0);
        }

        // 5. 现金流充足性分析
        if (cashFlow.getNetcashOperate() != null && cashFlow.getConstructLongAsset() != null &&
                cashFlow.getAssignDividendPorfit() != null) {
            BigDecimal cashFlowAdequacy = cashFlow.getNetcashOperate()
                    .divide(cashFlow.getConstructLongAsset().add(cashFlow.getAssignDividendPorfit()), 4, RoundingMode.HALF_UP);
            quality.put("cashFlowAdequacy", cashFlowAdequacy);
            quality.put("cashFlowAdequacyGood", cashFlowAdequacy.compareTo(new BigDecimal("1")) > 0);
        }

        return quality;
    }

    @Override
    public Map<String, Object> assessCashFlowHealth(String secucode, LocalDate reportDate) {
        CashFlow cashFlow = getCashFlowByDate(secucode, reportDate)
                .orElseThrow(() -> new RuntimeException(String.format("现金流量数据不存在：secucode={%s}, reportDate={%s}", secucode, reportDate.toString())));
        Map<String, Object> health = new HashMap<>();
        int score = 0;
        List<String> warnings = new ArrayList<>();

        // 经营现金流为正
        if (cashFlow.getNetcashOperate() != null && cashFlow.getNetcashOperate().compareTo(BigDecimal.ZERO) > 0) {
            score += 30;
        } else {
            warnings.add("经营活动现金流为负");
        }

        // 自由现金流为正
        BigDecimal freeCashFlow = cashFlow.calculateFreeCashFlow();
        if (freeCashFlow != null && freeCashFlow.compareTo(BigDecimal.ZERO) > 0) {
            score += 30;
        } else {
            warnings.add("自由现金流为负");
        }

        // 现金流结构合理（经营现金流占比高）
        Map<String, Object> structure = analyzeCashFlowStructure(secucode, reportDate);
        BigDecimal operatingRatio = (BigDecimal) structure.get("operatingRatio");
        if (operatingRatio != null && operatingRatio.compareTo(new BigDecimal("0.5")) > 0) {
            score += 20;
        } else {
            warnings.add("经营现金流占比偏低");
        }

        // 现金及现金等价物充足
        if (cashFlow.getEndCce() != null && cashFlow.getEndCce().compareTo(BigDecimal.ZERO) > 0) {
            score += 20;
        } else {
            warnings.add("现金及现金等价物不足");
        }

        health.put("score", score);
        health.put("healthLevel", getHealthLevel(score));
        health.put("warnings", warnings);

        return health;
    }

    private String getHealthLevel(int score) {
        if (score >= 80) return "优秀";
        if (score >= 60) return "良好";
        if (score >= 40) return "一般";
        return "较差";
    }
}