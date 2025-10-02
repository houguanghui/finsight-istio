package org.finsight.istio.stock.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
@EqualsAndHashCode
public class StockDailyId implements Serializable {
    private LocalDate date;
    private String code;
    private String adjustflag;
}