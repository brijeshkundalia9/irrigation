package com.irrigation.system.repository;

import com.irrigation.system.model.Plot;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepository extends JpaRepository<Plot, UUID> {

  @Query(value = "select p from Plot as p join Configuration as c on p.configuration = c.id"
      + " where (c.lastIrrigationEnd + c.reqTimeAfterLastIrrigate) < :irrigation_time")
  List<Plot> getPlotsForIrrigation(@Param("irrigation_time") Long irrigationTime);
}
