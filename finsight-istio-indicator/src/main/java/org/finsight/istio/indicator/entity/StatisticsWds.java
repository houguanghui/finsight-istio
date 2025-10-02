package org.finsight.istio.indicator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "statistics_wds")
@IdClass(StatisticsWdsId.class)
@Data
public class StatisticsWds {
    @Id
    @Column(name = "valuecode", length = 50)
    private String valuecode;

    @Id
    @Column(name = "code", length = 80)
    @JsonIgnore
    private String code;

    @Column(name = "wdcode", length = 50)
    private String wdcode;
}
