package org.finsight.istio.fundamental.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 现金流趋势响应DTO
 */
@Data
public class CashFlowTrendResponse {
    private List<LocalDate> dates;
    private List<BigDecimal> operatingCashFlows;
    private List<BigDecimal> investingCashFlows;
    private List<BigDecimal> financingCashFlows;
    private List<BigDecimal> freeCashFlows;
    private List<BigDecimal> growthRates;
}
