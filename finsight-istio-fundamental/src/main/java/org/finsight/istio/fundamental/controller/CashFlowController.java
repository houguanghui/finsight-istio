package org.finsight.istio.fundamental.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finsight.istio.common.web.exception.BusinessException;
import org.finsight.istio.fundamental.dto.request.CashFlowCompareRequest;
import org.finsight.istio.fundamental.dto.response.CashFlowHealthResponse;
import org.finsight.istio.fundamental.dto.response.CashFlowTrendResponse;
import org.finsight.istio.fundamental.entity.CashFlow;
import org.finsight.istio.fundamental.service.CashFlowService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cash-flow")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CashFlowController {

    private final CashFlowService cashFlowService;

    @GetMapping("/{secucode}")
    public List<CashFlow> getCashFlowData(
            @PathVariable String secucode,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (startDate == null) {
            startDate = LocalDate.now().minusYears(5);
        }
        if (endDate == null) {
            endDate = LocalDate.now();
        }

        return cashFlowService.getCashFlowData(secucode, startDate, endDate);
    }

    @GetMapping("/{secucode}/trend")
    public CashFlowTrendResponse getCashFlowTrend(
            @PathVariable String secucode,
            @RequestParam(defaultValue = "5") int years) {

        if (years < 1 || years > 10) {
            throw new BusinessException("年份范围必须在1-10之间");
        }

        return cashFlowService.getCashFlowTrend(secucode, years);
    }

    @PostMapping("/compare")
    public Map<String, Object> compareCashFlow(
            @Validated @RequestBody CashFlowCompareRequest request) {

        return cashFlowService.compareWithPeers(
                request.getSecucodes().getFirst(), // 第一个证券代码作为目标公司
                request.getSecucodes().subList(1, request.getSecucodes().size()), // 其余作为同行
                request.getReportDate()
        );
    }

    @GetMapping("/{secucode}/health")
    public CashFlowHealthResponse getCashFlowHealth(
            @PathVariable String secucode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate) {
        Map<String, Object> healthData = cashFlowService.assessCashFlowHealth(secucode, reportDate);

        CashFlowHealthResponse response = new CashFlowHealthResponse();
        response.setScore((Integer) healthData.get("score"));
        response.setHealthLevel((String) healthData.get("healthLevel"));
        Object warningsObj = healthData.get("warnings");
        if (warningsObj instanceof List<?> warningList) {
            List<String> warnings = new ArrayList<>();
            for(Object item : warningList) {
                warnings.add((String) item);
            }
            response.setWarnings(warnings);
        }

        return response;
    }

    @GetMapping("/{secucode}/quality")
    public Map<String, Object> getCashFlowQuality(
            @PathVariable String secucode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate reportDate) {

        return cashFlowService.analyzeCashFlowQuality(secucode, reportDate);
    }
}