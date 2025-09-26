package org.finsight.istio.fundamental.service;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.fundamental.entity.BalanceSheet;
import org.finsight.istio.fundamental.repository.BalanceSheetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BalanceSheetService {

    private final BalanceSheetRepository balanceSheetRepository;

    // 按证券代码和报告日期范围查询
    public Page<BalanceSheet> findBySecucodeAndReportDateRange(String secucode, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return balanceSheetRepository.findBySecucodeAndReportDateBetween(secucode, startDate, endDate, pageable);
    }
}