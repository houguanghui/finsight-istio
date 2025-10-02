package org.finsight.istio.indicator.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.finsight.istio.indicator.dto.DatanodeDTO;
import org.finsight.istio.indicator.dto.ReturndataDTO;
import org.finsight.istio.indicator.dto.WdnodeDTO;
import org.finsight.istio.indicator.entity.StatisticsFactdata;
import org.finsight.istio.indicator.entity.StatisticsWddata;
import org.finsight.istio.indicator.entity.StatisticsWdnodes;
import org.finsight.istio.indicator.entity.StatisticsWds;
import org.finsight.istio.indicator.repository.StatsAnalysisRepository;
import org.finsight.istio.indicator.service.StatsAnalysisService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatsAnalysisServiceImpl implements StatsAnalysisService {
    private final StatsAnalysisRepository statsAnalysisRepository;

    @Override
    public ReturndataDTO<DatanodeDTO<StatisticsFactdata>, WdnodeDTO<StatisticsWdnodes>> findGDPFactDataByTypeAndTimeBetween(String salesMetric, String startTime, String endTime) {
        List<StatisticsFactdata> factdataList = statsAnalysisRepository.findGDPFactDataByTypeAndTimeBetween(salesMetric, startTime, endTime);

        final Set<String> factdataCodes = new HashSet<>(factdataList.size());
        factdataList.forEach(f -> factdataCodes.add(f.getCode()));
        List<StatisticsWds> wdsList = statsAnalysisRepository.findGDPWdsByFactdataCodes(factdataCodes);
        factdataCodes.clear();
        Map<String, Set<String>> wdNodeAndWdMap = new HashMap<>();
        System.out.println("wdsList "+wdsList.size());
        Map<String, List<StatisticsWds>> dataNodeAndWdsMap = new HashMap<>(factdataList.size());
        wdsList.forEach(f -> {
            dataNodeAndWdsMap.computeIfAbsent(f.getCode(), k -> new ArrayList<>());
            dataNodeAndWdsMap.get(f.getCode()).add(f);

            wdNodeAndWdMap.computeIfAbsent(f.getWdcode(), k -> new HashSet<>());
            wdNodeAndWdMap.get(f.getWdcode()).add(f.getValuecode());
        });

        List<DatanodeDTO<StatisticsFactdata>> datanodes = new ArrayList<>(factdataList.size());
        List<WdnodeDTO<StatisticsWdnodes>> wdnodes = new ArrayList<>(factdataList.size());


        factdataList.forEach(f -> {
            datanodes.add(new DatanodeDTO<>(f.getCode(), f, dataNodeAndWdsMap.get(f.getCode())));
        });

        Set<String> wddataCodes = wdNodeAndWdMap.keySet();
        System.out.println(wddataCodes.size());
        List<StatisticsWddata> wddata = statsAnalysisRepository.findGDPWddataByCodes(wddataCodes);

        wddata.forEach(f->{
            wdnodes.add(new WdnodeDTO<>(
                statsAnalysisRepository.findGDPWdnodesByCodes(wdNodeAndWdMap.get(f.getWdcode())),
                    f.getWdcode(),
                    f.getWdname()
            ));
        });


        ReturndataDTO<DatanodeDTO<StatisticsFactdata>,WdnodeDTO<StatisticsWdnodes>> returndata = new ReturndataDTO<>();
        returndata.setDatanodes(datanodes);
        returndata.setWdnodes(wdnodes);
        returndata.setHasdatacount(factdataList.size());

        return returndata;
    }
}
