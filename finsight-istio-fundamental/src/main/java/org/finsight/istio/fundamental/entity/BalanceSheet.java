package org.finsight.istio.fundamental.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_balance_sheet", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"secucode", "report_date"})
})
@Data
public class BalanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "secucode", nullable = false, length = 20)
    private String secucode;

    @Column(name = "security_name_abbr", nullable = false, length = 100)
    private String securityNameAbbr;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "report_type", length = 20)
    private String reportType;

    @Column(name = "currency", length = 10)
    private String currency = "CNY";

    // 核心资产项目
    @Column(name = "monetaryfunds", precision = 20, scale = 2)
    private BigDecimal monetaryfunds;

    @Column(name = "note_rece", precision = 20, scale = 2)
    private BigDecimal noteRece;

    @Column(name = "accounts_rece", precision = 20, scale = 2)
    private BigDecimal accountsRece;

    @Column(name = "prepayment", precision = 20, scale = 2)
    private BigDecimal prepayment;

    @Column(name = "inventory", precision = 20, scale = 2)
    private BigDecimal inventory;

    @Column(name = "fixed_asset", precision = 20, scale = 2)
    private BigDecimal fixedAsset;

    @Column(name = "cip", precision = 20, scale = 2)
    private BigDecimal cip;

    @Column(name = "intangible_asset", precision = 20, scale = 2)
    private BigDecimal intangibleAsset;

    @Column(name = "defer_tax_asset", precision = 20, scale = 2)
    private BigDecimal deferTaxAsset;

    @Column(name = "other_current_asset", precision = 20, scale = 2)
    private BigDecimal otherCurrentAsset;

    @Column(name = "long_equity_invest", precision = 20, scale = 2)
    private BigDecimal longEquityInvest;

    @Column(name = "trade_finasset", precision = 20, scale = 2)
    private BigDecimal tradeFinasset;

    // 核心负债项目
    @Column(name = "note_accounts_payable", precision = 20, scale = 2)
    private BigDecimal noteAccountsPayable;

    @Column(name = "accounts_payable", precision = 20, scale = 2)
    private BigDecimal accountsPayable;

    @Column(name = "contract_liab", precision = 20, scale = 2)
    private BigDecimal contractLiab;

    @Column(name = "tax_payable", precision = 20, scale = 2)
    private BigDecimal taxPayable;

    @Column(name = "lease_liab", precision = 20, scale = 2)
    private BigDecimal leaseLiab;

    @Column(name = "defer_tax_liab", precision = 20, scale = 2)
    private BigDecimal deferTaxLiab;

    @Column(name = "total_other_payable", precision = 20, scale = 2)
    private BigDecimal totalOtherPayable;

    @Column(name = "short_loan", precision = 20, scale = 2)
    private BigDecimal shortLoan;

    @Column(name = "long_loan", precision = 20, scale = 2)
    private BigDecimal longLoan;

    @Column(name = "bond_payable", precision = 20, scale = 2)
    private BigDecimal bondPayable;

    // 核心权益项目
    @Column(name = "share_capital", precision = 20, scale = 2)
    private BigDecimal shareCapital;

    @Column(name = "capital_reserve", precision = 20, scale = 2)
    private BigDecimal capitalReserve;

    @Column(name = "surplus_reserve", precision = 20, scale = 2)
    private BigDecimal surplusReserve;

    @Column(name = "unassign_rprofit", precision = 20, scale = 2)
    private BigDecimal unassignRprofit;

    @Column(name = "minority_equity", precision = 20, scale = 2)
    private BigDecimal minorityEquity;

    // 关键汇总指标
    @Column(name = "total_assets", precision = 20, scale = 2)
    private BigDecimal totalAssets;

    @Column(name = "total_current_assets", precision = 20, scale = 2)
    private BigDecimal totalCurrentAssets;

    @Column(name = "total_noncurrent_assets", precision = 20, scale = 2)
    private BigDecimal totalNoncurrentAssets;

    @Column(name = "total_liabilities", precision = 20, scale = 2)
    private BigDecimal totalLiabilities;

    @Column(name = "total_current_liab", precision = 20, scale = 2)
    private BigDecimal totalCurrentLiab;

    @Column(name = "total_noncurrent_liab", precision = 20, scale = 2)
    private BigDecimal totalNoncurrentLiab;

    @Column(name = "total_equity", precision = 20, scale = 2)
    private BigDecimal totalEquity;

    @Column(name = "total_parent_equity", precision = 20, scale = 2)
    private BigDecimal totalParentEquity;

    // 关键同比增长指标
    @Column(name = "total_assets_yoy", precision = 10, scale = 6)
    private BigDecimal totalAssetsYoy;

    @Column(name = "total_equity_yoy", precision = 10, scale = 6)
    private BigDecimal totalEquityYoy;

    @Column(name = "total_liabilities_yoy", precision = 10, scale = 6)
    private BigDecimal totalLiabilitiesYoy;

    @Column(name = "inventory_yoy", precision = 10, scale = 6)
    private BigDecimal inventoryYoy;

    @Column(name = "monetaryfunds_yoy", precision = 10, scale = 6)
    private BigDecimal monetaryfundsYoy;

    // 审计信息
    @Column(name = "opinion_type", length = 100)
    private String opinionType;

    // 时间戳
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}