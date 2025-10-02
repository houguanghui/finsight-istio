package org.finsight.istio.indicator.entity;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Embeddable
@EqualsAndHashCode
public class StatisticsWdsId {
    private String valuecode;
    private String code;
}
