package org.finsight.istio.stock.repository;

import org.finsight.istio.stock.entity.StockDaily;
import org.finsight.istio.stock.entity.StockDailyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockDailyRepository extends JpaRepository<StockDaily, StockDailyId>, JpaSpecificationExecutor<StockDaily> {
    // 不分页查询：按股票代码和日期范围
    List<StockDaily> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // 不分页查询：按股票代码、复权标志和日期范围
    List<StockDaily> findByCodeAndAdjustflagAndDateBetween(String code, String adjustflag, LocalDate startDate, LocalDate endDate);
}