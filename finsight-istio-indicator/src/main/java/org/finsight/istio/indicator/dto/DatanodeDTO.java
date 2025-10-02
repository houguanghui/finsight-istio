package org.finsight.istio.indicator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.finsight.istio.indicator.entity.StatisticsWds;

import java.util.List;

@AllArgsConstructor
@Data
public class DatanodeDTO<T> {
    private String code;
    private T data;
    private List<StatisticsWds> wds;
}
