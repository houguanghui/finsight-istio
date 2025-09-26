package org.finsight.istio.fundamental.dto.response;

import lombok.Data;

import java.util.List;

/**
 * 现金流健康度响应DTO
 */
@Data
public class CashFlowHealthResponse {
    private Integer score;
    private String healthLevel;
    private List<String> warnings;
    private List<String> strengths;
}
