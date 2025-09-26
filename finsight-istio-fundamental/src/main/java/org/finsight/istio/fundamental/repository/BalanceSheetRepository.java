package org.finsight.istio.fundamental.repository;

import org.finsight.istio.fundamental.entity.BalanceSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BalanceSheetRepository extends JpaRepository<BalanceSheet, Long>, JpaSpecificationExecutor<BalanceSheet> {

    // 按证券代码和报告日期范围查询
    Page<BalanceSheet> findBySecucodeAndReportDateBetween(String secucode, LocalDate startDate, LocalDate endDate, Pageable pageable);
}