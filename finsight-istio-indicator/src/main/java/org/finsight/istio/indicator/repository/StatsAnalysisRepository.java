package org.finsight.istio.indicator.repository;

import org.finsight.istio.indicator.entity.StatisticsFactdata;
import org.finsight.istio.indicator.entity.StatisticsWddata;
import org.finsight.istio.indicator.entity.StatisticsWdnodes;
import org.finsight.istio.indicator.entity.StatisticsWds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StatsAnalysisRepository extends JpaRepository<StatisticsFactdata, String>, JpaSpecificationExecutor<StatisticsFactdata> {

    @Query("select s from StatisticsFactdata as s " +
    "where s.dataTime between :startTime AND :endTime and s.salesMetric = :salesMetric")
    List<StatisticsFactdata> findGDPFactDataByTypeAndTimeBetween(@Param("salesMetric") String salesMetric, @Param("startTime") String startTime, @Param("endTime") String endTime);

    @Query("select s from StatisticsWds as s "+
    "where s.code in :codes")
    List<StatisticsWds> findGDPWdsByFactdataCodes(@Param("codes") Set<String> codes);

    @Query("select s from StatisticsWddata as s " +
    "where s.wdcode in :codes")
    List<StatisticsWddata> findGDPWddataByCodes(@Param("codes") Set<String> codes);

    @Query("select s from StatisticsWdnodes as s " +
    "where s.code in :codes")
    List<StatisticsWdnodes> findGDPWdnodesByCodes(@Param("codes") Set<String> codes);
}
