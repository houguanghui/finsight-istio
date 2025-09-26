package org.finsight.istio.fundamental.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CashFlowCompareRequest {
    @NotEmpty(message = "证券代码列表不能为空")
    private List<String> secucodes;

    @NotNull(message = "报告日期不能为空")
    private LocalDate reportDate;

    private List<String> metrics;
}
