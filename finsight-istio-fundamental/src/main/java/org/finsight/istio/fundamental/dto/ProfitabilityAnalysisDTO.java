package org.finsight.istio.fundamental.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
public class ProfitabilityAnalysisDTO {
    private String secucode;
    private LocalDate reportDate;
    private BigDecimal profitabilityScore;
    private BigDecimal grossMargin;
    private BigDecimal netMargin;
    private BigDecimal operateMargin;
    private Map<String, BigDecimal> expenseRatios; // 费用比率
    private Map<String, Object> growthIndicators;  // 增长指标
}