package org.finsight.istio.fundamental.controller;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.fundamental.entity.BalanceSheet;
import org.finsight.istio.fundamental.service.BalanceSheetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/balance-sheet")
public class BalanceSheetController {

    private final BalanceSheetService balanceSheetService;

    // 按证券代码和报告日期范围查询
    @GetMapping("/secucode/{secucode}/date-range")
    public Page<BalanceSheet> getBySecucodeAndDateRange(
            @PathVariable String secucode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("reportDate").descending());
        return balanceSheetService.findBySecucodeAndReportDateRange(secucode, startDate, endDate, pageable);
    }
}