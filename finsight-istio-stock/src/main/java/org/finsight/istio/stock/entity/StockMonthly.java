package org.finsight.istio.stock.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_monthly_data")
@IdClass(StockMonthlyId.class)
@Data
public class StockMonthly {

    @Id
    @Column(name = "year_month", nullable = false, length = 7)
    private String yearMonth;

    @Id
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Id
    @Column(name = "adjustflag", length = 10)
    private String adjustflag;

    @Column(name = "open", precision = 12, scale = 4)
    private BigDecimal open;

    @Column(name = "high", precision = 12, scale = 4)
    private BigDecimal high;

    @Column(name = "low", precision = 12, scale = 4)
    private BigDecimal low;

    @Column(name = "close", precision = 12, scale = 4)
    private BigDecimal close;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "amount", precision = 20, scale = 4)
    private BigDecimal amount;
}