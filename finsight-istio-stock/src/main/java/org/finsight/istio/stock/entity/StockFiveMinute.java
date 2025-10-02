package org.finsight.istio.stock.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "stock_five_minute_data") // 体现5分钟特性的表名
@IdClass(StockFiveMinuteId.class)
@Data
public class StockFiveMinute {

    @Id
    @Column(name = "time", nullable = false)
    private Long time;

    @Id
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "open", nullable = false, precision = 10, scale = 4)
    private BigDecimal open;

    @Column(name = "high", nullable = false, precision = 10, scale = 4)
    private BigDecimal high;

    @Column(name = "low", nullable = false, precision = 10, scale = 4)
    private BigDecimal low;

    @Column(name = "close", nullable = false, precision = 10, scale = 4)
    private BigDecimal close;

    @Column(name = "volume", nullable = false)
    private Long volume;

    @Column(name = "amount", nullable = false, precision = 15, scale = 4)
    private BigDecimal amount;
}