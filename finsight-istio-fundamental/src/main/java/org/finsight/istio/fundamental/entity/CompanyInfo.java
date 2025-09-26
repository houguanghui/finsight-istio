package org.finsight.istio.fundamental.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "sec_info")
@Data
public class CompanyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stock_code", length = 20)
    private String stockCode;

    @Column(name = "org_id", length = 50, unique = true)
    private String orgId;

    @Column(name = "org_name_cn", length = 200, nullable = false)
    private String orgNameCn;

    @Column(name = "org_short_name_cn", length = 100)
    private String orgShortNameCn;

    @Column(name = "org_name_en", length = 200)
    private String orgNameEn;

    @Column(name = "org_short_name_en", length = 100)
    private String orgShortNameEn;

    @Column(name = "main_operation_business", columnDefinition = "TEXT")
    private String mainOperationBusiness;

    @Column(name = "operating_scope", columnDefinition = "TEXT")
    private String operatingScope;

    @Column(name = "district_encode", length = 20)
    private String districtEncode;

    @Column(name = "provincial_name", length = 50)
    private String provincialName;

    @Column(name = "org_cn_introduction", columnDefinition = "TEXT")
    private String orgCnIntroduction;

    @Column(name = "legal_representative", length = 100)
    private String legalRepresentative;

    @Column(name = "general_manager", length = 100)
    private String generalManager;

    @Column(name = "secretary", length = 100)
    private String secretary;

    @Column(name = "chairman", length = 100)
    private String chairman;

    @Column(name = "executives_nums")
    private Integer executivesNums;

    // 使用 BIGINT 存储时间戳，在Java中用Instant或Long
    @Column(name = "established_date")
    private Long establishedDate;

    @Column(name = "reg_asset", precision = 20, scale = 4)
    private BigDecimal regAsset;

    @Column(name = "staff_num")
    private Integer staffNum;

    @Column(name = "telephone", length = 50)
    private String telephone;

    @Column(name = "postcode", length = 20)
    private String postcode;

    @Column(name = "fax", length = 50)
    private String fax;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "org_website", length = 200)
    private String orgWebsite;

    @Column(name = "reg_address_cn", length = 500)
    private String regAddressCn;

    @Column(name = "reg_address_en", length = 500)
    private String regAddressEn;

    @Column(name = "office_address_cn", length = 500)
    private String officeAddressCn;

    @Column(name = "office_address_en", length = 500)
    private String officeAddressEn;

    @Column(name = "currency_encode", length = 20)
    private String currencyEncode;

    @Column(name = "listed_date")
    private Long listedDate;

    @Column(name = "actual_controller", length = 200)
    private String actualController;

    @Column(name = "classi_name", length = 50)
    private String classiName;

    @Column(name = "pre_name_cn", length = 200)
    private String preNameCn;

    @Column(name = "actual_issue_vol", precision = 20, scale = 4)
    private BigDecimal actualIssueVol;

    @Column(name = "issue_price", precision = 10, scale = 4)
    private BigDecimal issuePrice;

    @Column(name = "actual_rc_net_amt", precision = 20, scale = 4)
    private BigDecimal actualRcNetAmt;

    @Column(name = "pe_after_issuing", precision = 10, scale = 4)
    private BigDecimal peAfterIssuing;

    @Column(name = "online_success_rate_of_issue", precision = 10, scale = 6)
    private BigDecimal onlineSuccessRateOfIssue;

    // 使用JPA的Json类型需要额外依赖，这里先用@Lob
    @Column(name = "affiliate_industry", columnDefinition = "jsonb")
    private String affiliateIndustry;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}