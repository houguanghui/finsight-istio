package org.finsight.istio.fundamental.repository;
import org.finsight.istio.fundamental.entity.ProfitStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfitStatementRepository extends JpaRepository<ProfitStatement, Long> {

    // 根据股票代码查询所有利润表数据
    List<ProfitStatement> findBySecucodeOrderByReportDateDesc(String secucode);

    // 查询最新一期的数据
    @Query("SELECT p FROM ProfitStatement p WHERE p.secucode = :secucode AND p.reportDate = " +
            "(SELECT MAX(p2.reportDate) FROM ProfitStatement p2 WHERE p2.secucode = :secucode)")
    ProfitStatement findLatestBySecucode(@Param("secucode") String secucode);

    // 按年份聚合分析
    @Query("SELECT p.secucode, p.reportYear, " +
            "CAST(AVG(p.operateIncomeYoy) as java.math.BigDecimal), " +
            "CAST(AVG(p.netprofitYoy) as java.math.BigDecimal), " +
            "CAST(AVG(p.grossMargin) as java.math.BigDecimal) " +
            "FROM ProfitStatement p " +
            "WHERE p.secucode = :secucode AND p.reportYear >= :startYear " +
            "GROUP BY p.secucode, p.reportYear " +
            "ORDER BY p.reportYear DESC")
    List<Object[]> findYearlyAnalysis(@Param("secucode") String secucode,
                                      @Param("startYear") Integer startYear);
}