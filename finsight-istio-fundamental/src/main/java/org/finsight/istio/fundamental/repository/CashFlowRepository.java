package org.finsight.istio.fundamental.repository;

import org.finsight.istio.fundamental.entity.CashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CashFlowRepository extends JpaRepository<CashFlow, Long> {

    // 根据证券代码和时间范围查询
    List<CashFlow> findBySecucodeAndReportDateBetweenOrderByReportDateDesc(
            String secucode, LocalDate startDate, LocalDate endDate);

    // 根据证券代码和报告日期查询
    Optional<CashFlow> findBySecucodeAndReportDate(String secucode, LocalDate reportDate);

    // 获取多家公司同一报告期数据
    @Query("SELECT c FROM CashFlow c WHERE c.secucode IN :secucodes AND c.reportDate = :reportDate")
    List<CashFlow> findBySecucodesAndReportDate(
            @Param("secucodes") List<String> secucodes,
            @Param("reportDate") LocalDate reportDate);

    // 获取年度数据
    @Query("SELECT c FROM CashFlow c WHERE c.secucode = :secucode " +
            "AND EXTRACT(YEAR FROM c.reportDate) BETWEEN :startYear AND :endYear " +
            "ORDER BY c.reportDate")
    List<CashFlow> findYearlyData(@Param("secucode") String secucode,
                                       @Param("startYear") int startYear,
                                       @Param("endYear") int endYear);
}