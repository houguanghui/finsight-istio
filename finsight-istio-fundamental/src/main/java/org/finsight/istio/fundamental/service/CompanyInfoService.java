package org.finsight.istio.fundamental.service;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.common.jpa.specification.GenericSpecification;
import org.finsight.istio.fundamental.entity.CompanyInfo;
import org.finsight.istio.fundamental.repository.CompanyInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {

    private final CompanyInfoRepository companyInfoRepository;

    // 添加带过滤的分页查询方法
    public Page<CompanyInfo> findAllWithFilter(CompanyInfo filter, Pageable pageable) {
        Specification<CompanyInfo> spec = GenericSpecification.withDynamicQuery(filter, CompanyInfo.class);
        return companyInfoRepository.findAll(spec, pageable);
    }

    public Optional<CompanyInfo> findById(Integer id) {
        return companyInfoRepository.findById(id);
    }
}