package org.finsight.istio.stock.controller;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.stock.entity.StockDaily;
import org.finsight.istio.stock.service.StockDailyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock-daily")
public class StockDailyController {

    private final StockDailyService stockDailyService;

    // 按股票代码和日期范围查询（不分页）
    @GetMapping("/code/{code}/date-range")
    public List<StockDaily> getByCodeAndDateRange(
            @PathVariable String code,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) String adjustflag) {

        List<StockDaily> result;
        if (adjustflag != null) {
            result = stockDailyService.findByCodeAndAdjustflagAndDateRange(code, adjustflag, startDate, endDate);
        } else {
            result = stockDailyService.findByCodeAndDateRange(code, startDate, endDate);
        }
        return result;
    }
}