package org.finsight.istio.stock.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "stock_daily_data")
@IdClass(StockDailyId.class)
@Data
public class StockDaily {

    @Id
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Id
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Id
    @Column(name = "adjustflag", nullable = false, length = 10)
    private String adjustflag;

    @Column(name = "open", precision = 12, scale = 4)
    private BigDecimal open;

    @Column(name = "high", precision = 12, scale = 4)
    private BigDecimal high;

    @Column(name = "low", precision = 12, scale = 4)
    private BigDecimal low;

    @Column(name = "close", precision = 12, scale = 4)
    private BigDecimal close;

    @Column(name = "preclose", precision = 12, scale = 4)
    private BigDecimal preclose;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "amount", precision = 20, scale = 4)
    private BigDecimal amount;

    @Column(name = "turn", precision = 10, scale = 6)
    private BigDecimal turn;

    @Column(name = "tradestatus", length = 10)
    private String tradestatus;

    @Column(name = "pctChg", precision = 10, scale = 6)
    private BigDecimal pctChg;

    @Column(name = "isST")
    private Boolean isST;
}