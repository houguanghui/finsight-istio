package org.finsight.istio.indicator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "statistics_wddata")
@Data
public class StatisticsWddata {
    @Id
    private String wdcode;
    private String wdname;
}
