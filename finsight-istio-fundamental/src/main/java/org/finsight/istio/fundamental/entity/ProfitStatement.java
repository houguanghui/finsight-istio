package org.finsight.istio.fundamental.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "fact_profit_statement",
        uniqueConstraints = @UniqueConstraint(columnNames = {"secucode", "report_date"}))
public class ProfitStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profitId;

    @Column(name = "secucode", nullable = false, length = 20)
    private String secucode;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "report_year")
    private Integer reportYear;

    @Column(name = "report_quarter")
    private Integer reportQuarter;

    @Column(name = "report_type", length = 20)
    private String reportType;

    // 收入相关
    @Column(name = "total_operate_income", precision = 20, scale = 2)
    private BigDecimal totalOperateIncome;

    @Column(name = "total_operate_income_yoy", precision = 12, scale = 6)
    private BigDecimal totalOperateIncomeYoy;

    @Column(name = "operate_income", precision = 20, scale = 2)
    private BigDecimal operateIncome;

    @Column(name = "operate_income_yoy", precision = 12, scale = 6)
    private BigDecimal operateIncomeYoy;

    // 成本费用相关
    @Column(name = "total_operate_cost", precision = 20, scale = 2)
    private BigDecimal totalOperateCost;

    @Column(name = "total_operate_cost_yoy", precision = 12, scale = 6)
    private BigDecimal totalOperateCostYoy;

    @Column(name = "operate_cost", precision = 20, scale = 2)
    private BigDecimal operateCost;

    @Column(name = "operate_cost_yoy", precision = 12, scale = 6)
    private BigDecimal operateCostYoy;

    @Column(name = "sale_expense", precision = 20, scale = 2)
    private BigDecimal saleExpense;

    @Column(name = "sale_expense_yoy", precision = 12, scale = 6)
    private BigDecimal saleExpenseYoy;

    @Column(name = "manage_expense", precision = 20, scale = 2)
    private BigDecimal manageExpense;

    @Column(name = "manage_expense_yoy", precision = 12, scale = 6)
    private BigDecimal manageExpenseYoy;

    @Column(name = "finance_expense", precision = 20, scale = 2)
    private BigDecimal financeExpense;

    @Column(name = "finance_expense_yoy", precision = 12, scale = 6)
    private BigDecimal financeExpenseYoy;

    // 利润相关
    @Column(name = "operate_profit", precision = 20, scale = 2)
    private BigDecimal operateProfit;

    @Column(name = "operate_profit_yoy", precision = 12, scale = 6)
    private BigDecimal operateProfitYoy;

    @Column(name = "netprofit", precision = 20, scale = 2)
    private BigDecimal netprofit;

    @Column(name = "netprofit_yoy", precision = 12, scale = 6)
    private BigDecimal netprofitYoy;

    @Column(name = "parent_netprofit", precision = 20, scale = 2)
    private BigDecimal parentNetprofit;

    @Column(name = "parent_netprofit_yoy", precision = 12, scale = 6)
    private BigDecimal parentNetprofitYoy;

    // 每股指标
    @Column(name = "basic_eps", precision = 10, scale = 2)
    private BigDecimal basicEps;

    @Column(name = "basic_eps_yoy", precision = 12, scale = 6)
    private BigDecimal basicEpsYoy;

    // 计算指标
    @Column(name = "gross_margin", precision = 12, scale = 6)
    private BigDecimal grossMargin;

    @Column(name = "net_margin", precision = 12, scale = 6)
    private BigDecimal netMargin;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;
}