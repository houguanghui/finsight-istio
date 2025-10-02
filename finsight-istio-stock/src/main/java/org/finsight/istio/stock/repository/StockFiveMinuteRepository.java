package org.finsight.istio.stock.repository;

import org.finsight.istio.stock.entity.StockFiveMinute;
import org.finsight.istio.stock.entity.StockFiveMinuteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockFiveMinuteRepository extends JpaRepository<StockFiveMinute, StockFiveMinuteId>, JpaSpecificationExecutor<StockFiveMinute> {

    // 按股票代码和日期范围查询（不分页）
    List<StockFiveMinute> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // 按股票代码和日期范围查询（带排序，不分页）
    List<StockFiveMinute> findByCodeAndDateBetweenOrderByDateAscTimeAsc(String code, LocalDate startDate, LocalDate endDate);
}