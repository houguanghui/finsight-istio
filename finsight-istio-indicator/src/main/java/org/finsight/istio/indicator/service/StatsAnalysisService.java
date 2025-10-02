package org.finsight.istio.indicator.service;

import org.finsight.istio.indicator.dto.DatanodeDTO;
import org.finsight.istio.indicator.dto.ReturndataDTO;
import org.finsight.istio.indicator.dto.WdnodeDTO;
import org.finsight.istio.indicator.entity.StatisticsFactdata;
import org.finsight.istio.indicator.entity.StatisticsWdnodes;

public interface StatsAnalysisService {

    ReturndataDTO<DatanodeDTO<StatisticsFactdata>, WdnodeDTO<StatisticsWdnodes>> findGDPFactDataByTypeAndTimeBetween(String type, String startTime, String endTime);
}
