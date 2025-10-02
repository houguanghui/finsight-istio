package org.finsight.istio.stock.service;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.stock.entity.StockFiveMinute;
import org.finsight.istio.stock.repository.StockFiveMinuteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockFiveMinuteService {

    private final StockFiveMinuteRepository stockFiveMinuteRepository;

    public List<StockFiveMinute> findByCodeAndDateRange(String code, LocalDate startDate, LocalDate endDate) {
        return stockFiveMinuteRepository.findByCodeAndDateBetween(code, startDate, endDate);
    }

    public List<StockFiveMinute> findByCodeAndDateRangeOrdered(String code, LocalDate startDate, LocalDate endDate) {
        return stockFiveMinuteRepository.findByCodeAndDateBetweenOrderByDateAscTimeAsc(code, startDate, endDate);
    }
}