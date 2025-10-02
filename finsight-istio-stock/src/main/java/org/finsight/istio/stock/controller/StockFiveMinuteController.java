package org.finsight.istio.stock.controller;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.stock.entity.StockFiveMinute;
import org.finsight.istio.stock.service.StockFiveMinuteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock-minute")
public class StockFiveMinuteController {

    private final StockFiveMinuteService stockFiveMinuteService;

    @GetMapping("/code/{code}/date-range")
    public ResponseEntity<List<StockFiveMinute>> getByCodeAndDateRange(
            @PathVariable String code,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "false") boolean ordered) {

        List<StockFiveMinute> result;
        if (ordered) {
            result = stockFiveMinuteService.findByCodeAndDateRangeOrdered(code, startDate, endDate);
        } else {
            result = stockFiveMinuteService.findByCodeAndDateRange(code, startDate, endDate);
        }

        return ResponseEntity.ok(result);
    }
}