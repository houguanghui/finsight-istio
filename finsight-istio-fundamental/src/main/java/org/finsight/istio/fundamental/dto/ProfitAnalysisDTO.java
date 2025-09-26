package org.finsight.istio.fundamental.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class ProfitAnalysisDTO {
    private String secucode;
    private LocalDate reportDate;
    private String reportType;
    private BigDecimal operateIncome;
    private BigDecimal operateIncomeYoy;
    private BigDecimal netprofit;
    private BigDecimal netprofitYoy;
    private BigDecimal grossMargin;
    private BigDecimal netMargin;
    private BigDecimal basicEps;

    // 用于存储额外的分析属性
    private Map<String, Object> additionalProperties = new HashMap<>();

    public void setAdditionalProperty(String key, Object value) {
        additionalProperties.put(key, value);
    }

    public Object getAdditionalProperty(String key) {
        return additionalProperties.get(key);
    }
}