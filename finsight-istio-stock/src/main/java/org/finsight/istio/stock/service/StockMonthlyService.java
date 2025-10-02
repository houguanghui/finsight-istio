package org.finsight.istio.stock.service;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.stock.entity.StockMonthly;
import org.finsight.istio.stock.repository.StockMonthlyRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StockMonthlyService {

    private final StockMonthlyRepository stockMonthlyRepository;

    // 不分页查询：按股票代码和年月范围
    public List<StockMonthly> findByCodeAndYearMonthRange(String code, String startYearMonth, String endYearMonth) {
        return stockMonthlyRepository.findByCodeAndYearMonthBetween(code, startYearMonth, endYearMonth);
    }

    // 不分页查询：按股票代码、复权标志和年月范围
    public List<StockMonthly> findByCodeAndAdjustflagAndYearMonthRange(String code, String adjustflag, String startYearMonth, String endYearMonth) {
        return stockMonthlyRepository.findByCodeAndAdjustflagAndYearMonthBetween(code, adjustflag, startYearMonth, endYearMonth);
    }
}