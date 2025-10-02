package org.finsight.istio.stock.controller;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.stock.entity.StockMonthly;
import org.finsight.istio.stock.service.StockMonthlyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock-monthly")
public class StockMonthlyController {

    private final StockMonthlyService stockMonthlyService;

    // 按股票代码和年月范围查询（不分页）
    @GetMapping("/code/{code}/year-month-range")
    public List<StockMonthly> getByCodeAndYearMonthRange(
            @PathVariable String code,
            @RequestParam String startYearMonth,
            @RequestParam String endYearMonth,
            @RequestParam(required = false) String adjustflag) {

        List<StockMonthly> result;
        if (adjustflag != null) {
            result = stockMonthlyService.findByCodeAndAdjustflagAndYearMonthRange(code, adjustflag, startYearMonth, endYearMonth);
        } else {
            result = stockMonthlyService.findByCodeAndYearMonthRange(code, startYearMonth, endYearMonth);
        }
        return result;
    }
}