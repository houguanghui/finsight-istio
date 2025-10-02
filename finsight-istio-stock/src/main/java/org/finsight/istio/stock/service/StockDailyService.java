package org.finsight.istio.stock.service;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.stock.entity.StockDaily;
import org.finsight.istio.stock.repository.StockDailyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockDailyService {

    private final StockDailyRepository stockDailyRepository;

    // 不分页查询：按股票代码和日期范围
    public List<StockDaily> findByCodeAndDateRange(String code, LocalDate startDate, LocalDate endDate) {
        return stockDailyRepository.findByCodeAndDateBetween(code, startDate, endDate);
    }

    // 不分页查询：按股票代码、复权标志和日期范围
    public List<StockDaily> findByCodeAndAdjustflagAndDateRange(String code, String adjustflag, LocalDate startDate, LocalDate endDate) {
        return stockDailyRepository.findByCodeAndAdjustflagAndDateBetween(code, adjustflag, startDate, endDate);
    }
}