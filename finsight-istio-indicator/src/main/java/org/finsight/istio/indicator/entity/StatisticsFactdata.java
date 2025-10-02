package org.finsight.istio.indicator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "statistics_factdata")
@Data
public class StatisticsFactdata {
    @Id
    @Column(name = "code", length = 50)
    private String code;
    @Column(name = "sales_metric", length = 50)
    private String salesMetric;
    @Column(name = "data_value", precision = 20, scale = 4)
    private BigDecimal dataValue;
    private Integer dotcount;
    private Boolean hasdata;
    @Column(name = "strdata", length = 50)
    private String strdata;
    @JsonIgnore
    @Column(name = "data_time")
    private String dataTime;
}
