package com.irrigation.system.repository;

import com.irrigation.system.model.Plot;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepository extends JpaRepository<Plot, UUID> {

}
