package org.finsight.istio.stock.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class StockMonthlyId implements Serializable {
    private String yearMonth;
    private String code;
    private String adjustflag;
}