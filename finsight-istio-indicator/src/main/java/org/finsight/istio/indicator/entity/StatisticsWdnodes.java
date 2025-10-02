package org.finsight.istio.indicator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "statistics_wdnodes")
@Data
public class StatisticsWdnodes {
    @Id
    private String code;
    private String cname;
    private Integer dotcount;
    private String exp;
    private Boolean ifshowcode;
    private String memo;
    private String name;
    private String nodesort;
    private Integer sortcode;
    private String tag;
    private String unit;
}
