package org.finsight.istio.fundamental.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GrowthAnalysisDTO {
    private String secucode;
    private Integer year;
    private BigDecimal revenueGrowth;
    private BigDecimal profitGrowth;
    private BigDecimal marginTrend;
}