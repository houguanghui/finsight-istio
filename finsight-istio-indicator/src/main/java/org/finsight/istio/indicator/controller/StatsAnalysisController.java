package org.finsight.istio.indicator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finsight.istio.indicator.dto.DatanodeDTO;
import org.finsight.istio.indicator.dto.ReturndataDTO;
import org.finsight.istio.indicator.dto.WdnodeDTO;
import org.finsight.istio.indicator.entity.StatisticsFactdata;
import org.finsight.istio.indicator.entity.StatisticsWdnodes;
import org.finsight.istio.indicator.service.StatsAnalysisService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Validated
@Slf4j
public class StatsAnalysisController {
    public final StatsAnalysisService statsAnalysisService;

    @GetMapping("{salesMetric}")
    public ReturndataDTO<DatanodeDTO<StatisticsFactdata>, WdnodeDTO<StatisticsWdnodes>> findStatsAnalysis(
            @PathVariable String salesMetric,
            @RequestParam String startTime,
            @RequestParam String endTime) {

        ReturndataDTO<DatanodeDTO<StatisticsFactdata>, WdnodeDTO<StatisticsWdnodes>> returndataDTO =
                statsAnalysisService.findGDPFactDataByTypeAndTimeBetween(salesMetric, startTime, endTime);

        return returndataDTO;
    }
}
