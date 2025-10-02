package org.finsight.istio.stock.repository;

import org.finsight.istio.stock.entity.StockMonthly;
import org.finsight.istio.stock.entity.StockMonthlyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMonthlyRepository extends JpaRepository<StockMonthly, StockMonthlyId>, JpaSpecificationExecutor<StockMonthly> {

    // 不分页查询：按股票代码和年月范围
    List<StockMonthly> findByCodeAndYearMonthBetween(String code, String startYearMonth, String endYearMonth);

    // 不分页查询：按股票代码、复权标志和年月范围
    List<StockMonthly> findByCodeAndAdjustflagAndYearMonthBetween(String code, String adjustflag, String startYearMonth, String endYearMonth);

}