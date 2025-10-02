package org.finsight.istio.stock.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@Embeddable
@EqualsAndHashCode
public class StockFiveMinuteId implements Serializable {
    private Long time;
    private String code;
}