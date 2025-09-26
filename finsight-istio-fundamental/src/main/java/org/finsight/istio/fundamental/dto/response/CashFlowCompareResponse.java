package org.finsight.istio.fundamental.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 现金流量对比响应DTO
 */
@Data
public class CashFlowCompareResponse {
    private String secucode;
    private String companyName;
    private LocalDate reportDate;
    private BigDecimal operatingCashFlow;
    private BigDecimal investingCashFlow;
    private BigDecimal financingCashFlow;
    private BigDecimal freeCashFlow;
    private BigDecimal netProfit;

    // 比率指标
    private BigDecimal profitCashRatio;
    private BigDecimal reinvestmentRatio;

    // 排名信息
    private Integer operatingRank;
    private Integer freeCashFlowRank;
}
