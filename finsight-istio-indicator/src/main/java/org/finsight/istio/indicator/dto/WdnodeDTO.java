package org.finsight.istio.indicator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class WdnodeDTO<T> {
    List<T> nodes;
    String wdcode;
    String wdname;
}
