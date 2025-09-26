package org.finsight.istio.fundamental.repository;

import lombok.NonNull;
import org.finsight.istio.fundamental.entity.CompanyInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer>, JpaSpecificationExecutor<CompanyInfo> {
    @NonNull
    Page<CompanyInfo> findAll(@NonNull Pageable pageable);
    @NonNull
    Page<CompanyInfo> findAll(Specification<CompanyInfo> spec,@NonNull  Pageable pageable); // 添加Specification支持
}