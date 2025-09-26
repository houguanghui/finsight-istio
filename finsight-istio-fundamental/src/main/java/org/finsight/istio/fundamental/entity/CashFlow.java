package org.finsight.istio.fundamental.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 现金流量表实体类
 */
@Entity
@Table(name = "stock_cash_flow", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"secucode", "report_date"}, name = "unique_cash_flow")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CashFlow {

    // 基础信息
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

    @Column(name = "report_date_name", length = 50)
    private String reportDateName;

    @Column(name = "currency", length = 10)
    private String currency = "CNY";

    @Column(name = "notice_date")
    private LocalDateTime noticeDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    // 经营活动现金流量 - 现金流入
    @Column(name = "sales_services", precision = 20, scale = 2)
    private BigDecimal salesServices;

    @Column(name = "deposit_interbank_add", precision = 20, scale = 2)
    private BigDecimal depositInterbankAdd;

    @Column(name = "receive_interest_commission", precision = 20, scale = 2)
    private BigDecimal receiveInterestCommission;

    @Column(name = "receive_other_operate", precision = 20, scale = 2)
    private BigDecimal receiveOtherOperate;

    @Column(name = "total_operate_inflow", precision = 20, scale = 2)
    private BigDecimal totalOperateInflow;

    // 经营活动现金流量 - 现金流出
    @Column(name = "buy_services", precision = 20, scale = 2)
    private BigDecimal buyServices;

    @Column(name = "pbc_interbank_add", precision = 20, scale = 2)
    private BigDecimal pbcInterbankAdd;

    @Column(name = "pay_interest_commission", precision = 20, scale = 2)
    private BigDecimal payInterestCommission;

    @Column(name = "pay_staff_cash", precision = 20, scale = 2)
    private BigDecimal payStaffCash;

    @Column(name = "pay_all_tax", precision = 20, scale = 2)
    private BigDecimal payAllTax;

    @Column(name = "pay_other_operate", precision = 20, scale = 2)
    private BigDecimal payOtherOperate;

    @Column(name = "total_operate_outflow", precision = 20, scale = 2)
    private BigDecimal totalOperateOutflow;

    @Column(name = "netcash_operate", precision = 20, scale = 2)
    private BigDecimal netcashOperate;

    // 投资活动现金流量 - 现金流入
    @Column(name = "withdraw_invest", precision = 20, scale = 2)
    private BigDecimal withdrawInvest;

    @Column(name = "receive_invest_income", precision = 20, scale = 2)
    private BigDecimal receiveInvestIncome;

    @Column(name = "disposal_long_asset", precision = 20, scale = 2)
    private BigDecimal disposalLongAsset;

    @Column(name = "receive_other_invest", precision = 20, scale = 2)
    private BigDecimal receiveOtherInvest;

    @Column(name = "total_invest_inflow", precision = 20, scale = 2)
    private BigDecimal totalInvestInflow;

    // 投资活动现金流量 - 现金流出
    @Column(name = "construct_long_asset", precision = 20, scale = 2)
    private BigDecimal constructLongAsset;

    @Column(name = "invest_pay_cash", precision = 20, scale = 2)
    private BigDecimal investPayCash;

    @Column(name = "pay_other_invest", precision = 20, scale = 2)
    private BigDecimal payOtherInvest;

    @Column(name = "total_invest_outflow", precision = 20, scale = 2)
    private BigDecimal totalInvestOutflow;

    @Column(name = "netcash_invest", precision = 20, scale = 2)
    private BigDecimal netcashInvest;

    // 筹资活动现金流量 - 现金流出
    @Column(name = "assign_dividend_porfit", precision = 20, scale = 2)
    private BigDecimal assignDividendPorfit;

    @Column(name = "pay_other_finance", precision = 20, scale = 2)
    private BigDecimal payOtherFinance;

    @Column(name = "total_finance_outflow", precision = 20, scale = 2)
    private BigDecimal totalFinanceOutflow;

    @Column(name = "netcash_finance", precision = 20, scale = 2)
    private BigDecimal netcashFinance;

    // 现金及现金等价物
    @Column(name = "rate_change_effect", precision = 20, scale = 2)
    private BigDecimal rateChangeEffect;

    @Column(name = "cce_add", precision = 20, scale = 2)
    private BigDecimal cceAdd;

    @Column(name = "begin_cce", precision = 20, scale = 2)
    private BigDecimal beginCce;

    @Column(name = "end_cce", precision = 20, scale = 2)
    private BigDecimal endCce;

    @Column(name = "begin_cash", precision = 20, scale = 2)
    private BigDecimal beginCash;

    @Column(name = "end_cash", precision = 20, scale = 2)
    private BigDecimal endCash;

    @Column(name = "begin_cash_equivalents", precision = 20, scale = 2)
    private BigDecimal beginCashEquivalents;

    @Column(name = "end_cash_equivalents", precision = 20, scale = 2)
    private BigDecimal endCashEquivalents;

    // 现金流量表补充资料 - 净利润调节为经营活动现金流量
    @Column(name = "netprofit", precision = 20, scale = 2)
    private BigDecimal netprofit;

    @Column(name = "fa_ir_depr", precision = 20, scale = 2)
    private BigDecimal faIrDepr;

    @Column(name = "ia_amortize", precision = 20, scale = 2)
    private BigDecimal iaAmortize;

    @Column(name = "lpe_amortize", precision = 20, scale = 2)
    private BigDecimal lpeAmortize;

    @Column(name = "disposal_longasset_loss", precision = 20, scale = 2)
    private BigDecimal disposalLongassetLoss;

    @Column(name = "fairvalue_change_loss", precision = 20, scale = 2)
    private BigDecimal fairvalueChangeLoss;

    @Column(name = "finance_expense", precision = 20, scale = 2)
    private BigDecimal financeExpense;

    @Column(name = "invest_loss", precision = 20, scale = 2)
    private BigDecimal investLoss;

    @Column(name = "defer_tax", precision = 20, scale = 2)
    private BigDecimal deferTax;

    @Column(name = "inventory_reduce", precision = 20, scale = 2)
    private BigDecimal inventoryReduce;

    @Column(name = "operate_rece_reduce", precision = 20, scale = 2)
    private BigDecimal operateReceReduce;

    @Column(name = "operate_payable_add", precision = 20, scale = 2)
    private BigDecimal operatePayableAdd;

    // 同比增长指标
    @Column(name = "sales_services_yoy", precision = 15, scale = 6)
    private BigDecimal salesServicesYoy;

    @Column(name = "receive_interest_commission_yoy", precision = 15, scale = 6)
    private BigDecimal receiveInterestCommissionYoy;

    @Column(name = "total_operate_inflow_yoy", precision = 15, scale = 6)
    private BigDecimal totalOperateInflowYoy;

    @Column(name = "netcash_operate_yoy", precision = 15, scale = 6)
    private BigDecimal netcashOperateYoy;

    @Column(name = "netcash_invest_yoy", precision = 15, scale = 6)
    private BigDecimal netcashInvestYoy;

    @Column(name = "netcash_finance_yoy", precision = 15, scale = 6)
    private BigDecimal netcashFinanceYoy;

    @Column(name = "netprofit_yoy", precision = 15, scale = 6)
    private BigDecimal netprofitYoy;

    // 审计信息
    @Column(name = "opinion_type", length = 100)
    private String opinionType;

    // 时间戳
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // 业务方法 - 计算自由现金流
    public BigDecimal calculateFreeCashFlow() {
        if (netcashOperate == null || constructLongAsset == null) {
            return null;
        }
        return netcashOperate.subtract(constructLongAsset);
    }

    // 业务方法 - 计算经营现金流与净利润比率
    public BigDecimal calculateOperatingCashFlowToNetProfitRatio() {
        if (netcashOperate == null || netprofit == null || netprofit.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return netcashOperate.divide(netprofit, 4, BigDecimal.ROUND_HALF_UP);
    }

    // 业务方法 - 计算现金再投资比率
    public BigDecimal calculateCashReinvestmentRatio() {
        if (netcashOperate == null || constructLongAsset == null || netcashOperate.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        return netcashOperate.subtract(constructLongAsset)
                .divide(netcashOperate, 4, BigDecimal.ROUND_HALF_UP);
    }

    // 业务方法 - 检查数据完整性
    public boolean isDataComplete() {
        return netcashOperate != null && netcashInvest != null && netcashFinance != null;
    }

    // 业务方法 - 获取主要现金流指标
    public BigDecimal getMainCashFlowIndicator(String indicatorType) {
        switch (indicatorType) {
            case "operating":
                return netcashOperate;
            case "investing":
                return netcashInvest;
            case "financing":
                return netcashFinance;
            case "free":
                return calculateFreeCashFlow();
            default:
                return null;
        }
    }

    // 业务方法 - 验证数据逻辑一致性
    public boolean validateDataConsistency() {
        // 验证经营活动现金流净额 = 流入小计 - 流出小计
        if (totalOperateInflow != null && totalOperateOutflow != null && netcashOperate != null) {
            BigDecimal calculated = totalOperateInflow.subtract(totalOperateOutflow);
            if (calculated.compareTo(netcashOperate) != 0) {
                return false;
            }
        }

        // 验证现金及现金等价物净增加额 = 期末余额 - 期初余额
        if (beginCce != null && endCce != null && cceAdd != null) {
            BigDecimal calculated = endCce.subtract(beginCce);
            if (calculated.compareTo(cceAdd) != 0) {
                return false;
            }
        }

        return true;
    }
}