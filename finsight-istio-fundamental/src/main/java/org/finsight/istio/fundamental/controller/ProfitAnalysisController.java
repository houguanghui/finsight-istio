package org.finsight.istio.fundamental.controller;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.common.web.exception.BusinessException;
import org.finsight.istio.fundamental.dto.GrowthAnalysisDTO;
import org.finsight.istio.fundamental.dto.ProfitAnalysisDTO;
import org.finsight.istio.fundamental.dto.ProfitabilityAnalysisDTO;
import org.finsight.istio.fundamental.service.ProfitAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profit-analysis")
@RequiredArgsConstructor
public class ProfitAnalysisController {

    private final ProfitAnalysisService profitAnalysisService;

    /**
     * 获取股票利润表历史数据
     */
    @GetMapping("/history/{secucode}")
    public List<ProfitAnalysisDTO> getProfitHistory(
            @PathVariable String secucode) {
        return profitAnalysisService.getProfitHistory(secucode);
    }

    /**
     * 增长趋势分析
     */
    @GetMapping("/growth/{secucode}")
    public List<GrowthAnalysisDTO> analyzeGrowthTrend(
            @PathVariable String secucode,
            @RequestParam(defaultValue = "5") Integer years) {
        return profitAnalysisService.analyzeGrowthTrend(secucode, years);
    }

    /**
     * 综合盈利能力分析
     */
    @GetMapping("/profitability/{secucode}")
    public ProfitabilityAnalysisDTO analyzeProfitability(
            @PathVariable String secucode) {
        ProfitabilityAnalysisDTO result = profitAnalysisService.analyzeProfitability(secucode);
        if (result == null) {
            throw new BusinessException("未找到相关数据");
        }
        return result;
    }
}