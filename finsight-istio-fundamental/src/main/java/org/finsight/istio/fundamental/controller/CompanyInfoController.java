package org.finsight.istio.fundamental.controller;

import lombok.RequiredArgsConstructor;
import org.finsight.istio.fundamental.entity.CompanyInfo;
import org.finsight.istio.fundamental.service.CompanyInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company-info")
public class CompanyInfoController {

    private final CompanyInfoService companyInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyInfo> getSecInfoById(@PathVariable Integer id) {
        Optional<CompanyInfo> secInfo = companyInfoService.findById(id);
        return secInfo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 修改获取所有数据的方法，支持分页
    @GetMapping
    public Page<CompanyInfo> getAllSecInfo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "listedDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction,
            @ModelAttribute CompanyInfo filter) { // 添加filter参数

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return companyInfoService.findAllWithFilter(filter, pageable);
    }
}