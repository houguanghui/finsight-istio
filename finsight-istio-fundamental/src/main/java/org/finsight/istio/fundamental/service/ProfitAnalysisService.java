package org.finsight.istio.fundamental.service;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.common.web.exception.BusinessException;
import org.finsight.istio.fundamental.dto.GrowthAnalysisDTO;
import org.finsight.istio.fundamental.dto.ProfitAnalysisDTO;
import org.finsight.istio.fundamental.dto.ProfitabilityAnalysisDTO;
import org.finsight.istio.fundamental.entity.ProfitStatement;
import org.finsight.istio.fundamental.repository.ProfitStatementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfitAnalysisService {

    private final ProfitStatementRepository profitStatementRepository;

    public List<ProfitAnalysisDTO> getProfitHistory(String secucode) {
        List<ProfitStatement> statements = profitStatementRepository.findBySecucodeOrderByReportDateDesc(secucode);
        if (statements == null || statements.isEmpty()) {
            throw new BusinessException("未找到股票代码为 " + secucode + " 的利润表数据");
        }
        return statements.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<GrowthAnalysisDTO> analyzeGrowthTrend(String secucode, Integer years) {
        Integer startYear = LocalDate.now().getYear() - years;
        List<Object[]> results = profitStatementRepository.findYearlyAnalysis(secucode, startYear);

        return results.stream().map(result -> {
            GrowthAnalysisDTO dto = new GrowthAnalysisDTO();
            dto.setSecucode((String) result[0]);
            dto.setYear((Integer) result[1]);
            dto.setRevenueGrowth((BigDecimal) result[2]);
            dto.setProfitGrowth((BigDecimal) result[3]);
            dto.setMarginTrend((BigDecimal) result[4]);
            return dto;
        }).collect(Collectors.toList());
    }

    /**
     * 盈利能力分析 - 返回专门的DTO
     */
    public ProfitabilityAnalysisDTO analyzeProfitability(String secucode) {
        ProfitStatement latest = profitStatementRepository.findLatestBySecucode(secucode);
        if (latest == null) return null;

        ProfitabilityAnalysisDTO dto = new ProfitabilityAnalysisDTO();
        dto.setSecucode(latest.getSecucode());
        dto.setReportDate(latest.getReportDate());
        dto.setProfitabilityScore(calculateProfitabilityScore(latest));
        dto.setGrossMargin(latest.getGrossMargin());
        dto.setNetMargin(latest.getNetMargin());

        // 计算营业利润率
        if (latest.getOperateIncome() != null && latest.getOperateIncome().compareTo(BigDecimal.ZERO) != 0 &&
                latest.getOperateProfit() != null) {
            BigDecimal operateMargin = latest.getOperateProfit()
                    .divide(latest.getOperateIncome(), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            dto.setOperateMargin(operateMargin);
        }

        // 费用结构分析
        dto.setExpenseRatios(analyzeExpenseStructure(latest));

        // 增长指标
        dto.setGrowthIndicators(Map.of(
                "revenueGrowth", latest.getOperateIncomeYoy(),
                "profitGrowth", latest.getNetprofitYoy(),
                "epsGrowth", latest.getBasicEpsYoy()
        ));

        return dto;
    }

    private Map<String, BigDecimal> analyzeExpenseStructure(ProfitStatement statement) {
        BigDecimal totalExpense = statement.getSaleExpense()
                .add(statement.getManageExpense())
                .add(statement.getFinanceExpense())
                .add(statement.getOperateCost() != null ? statement.getOperateCost() : BigDecimal.ZERO);

        if (totalExpense.compareTo(BigDecimal.ZERO) == 0) {
            return Map.of();
        }

        return Map.of(
                "saleExpenseRatio", calculateRatio(statement.getSaleExpense(), totalExpense),
                "manageExpenseRatio", calculateRatio(statement.getManageExpense(), totalExpense),
                "financeExpenseRatio", calculateRatio(statement.getFinanceExpense(), totalExpense),
                "operateCostRatio", calculateRatio(statement.getOperateCost(), totalExpense)
        );
    }

    private ProfitAnalysisDTO convertToDTO(ProfitStatement statement) {
        ProfitAnalysisDTO dto = new ProfitAnalysisDTO();
        dto.setSecucode(statement.getSecucode());
        dto.setReportDate(statement.getReportDate());
        dto.setReportType(statement.getReportType());
        dto.setOperateIncome(statement.getOperateIncome());
        dto.setOperateIncomeYoy(statement.getOperateIncomeYoy());
        dto.setNetprofit(statement.getNetprofit());
        dto.setNetprofitYoy(statement.getNetprofitYoy());
        dto.setGrossMargin(statement.getGrossMargin());
        dto.setNetMargin(statement.getNetMargin());
        dto.setBasicEps(statement.getBasicEps());
        return dto;
    }

    private BigDecimal calculateProfitabilityScore(ProfitStatement statement) {
        BigDecimal score = BigDecimal.ZERO;
        int factorCount = 0;

        if (statement.getGrossMargin() != null) {
            score = score.add(statement.getGrossMargin().multiply(BigDecimal.valueOf(0.4)));
            factorCount++;
        }
        if (statement.getNetMargin() != null) {
            score = score.add(statement.getNetMargin().multiply(BigDecimal.valueOf(0.6)));
            factorCount++;
        }

        return factorCount > 0 ? score.setScale(2, RoundingMode.HALF_UP) : BigDecimal.ZERO;
    }

    private BigDecimal calculateRatio(BigDecimal numerator, BigDecimal denominator) {
        if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0 || numerator == null) {
            return BigDecimal.ZERO;
        }
        return numerator.divide(denominator, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
}