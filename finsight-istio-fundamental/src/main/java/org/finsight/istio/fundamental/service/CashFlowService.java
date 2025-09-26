package org.finsight.istio.fundamental.service;


import org.finsight.istio.fundamental.dto.response.CashFlowTrendResponse;
import org.finsight.istio.fundamental.entity.CashFlow;

import java.time.LocalDate;
import java.util.*;

public interface CashFlowService {

    // 基础查询
    List<CashFlow> getCashFlowData(String secucode, LocalDate startDate, LocalDate endDate);
    Optional<CashFlow> getCashFlowByDate(String secucode, LocalDate reportDate);
    // 趋势分析
    CashFlowTrendResponse getCashFlowTrend(String secucode, int years);
    // 对比分析
    Map<String, Object> compareWithPeers(String secucode, List<String> peerSecucodes, LocalDate reportDate);

    // 现金流质量分析
    Map<String, Object> analyzeCashFlowQuality(String secucode, LocalDate reportDate);

    // 现金流结构分析
    Map<String, Object> analyzeCashFlowStructure(String secucode, LocalDate reportDate);

    // 现金流健康状况评估
    Map<String, Object> assessCashFlowHealth(String secucode, LocalDate reportDate);

}