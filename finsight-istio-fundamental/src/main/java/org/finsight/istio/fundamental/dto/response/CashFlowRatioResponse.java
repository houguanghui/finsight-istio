package org.finsight.istio.fundamental.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 现金流比率响应DTO
 */
@Data
public class CashFlowRatioResponse {
    private BigDecimal profitCashRatio;
    private BigDecimal reinvestmentRatio;
    private BigDecimal interestCoverage;
    private BigDecimal cashCollectionRatio;
    private BigDecimal operatingMargin;

}
