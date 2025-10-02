package org.finsight.istio.indicator.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReturndataDTO<D,W> {
    private List<D> datanodes;
    private List<W> wdnodes;

    private Integer hasdatacount;
}
