package com.irrigation.system.service;

import com.irrigation.system.exception.PlotNotFoundException;
import com.irrigation.system.model.Configuration;
import com.irrigation.system.model.Plot;
import com.irrigation.system.model.Status;
import com.irrigation.system.repository.PlotRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlotService {

  @Autowired
  private PlotRepository plotRepository;

  public List<Plot> getPlots() {
    List<Plot> plots = plotRepository.findAll();
    return plots;
  }

  public Plot addPlot(Plot plotBody) {
    return plotRepository.save(plotBody);
  }

  public Plot updatePlot(String plotId, Plot plotBody) {
    Optional<Plot> plotOpt = plotRepository.findById(UUID.fromString(plotId));

    if (plotOpt.isEmpty()) {
      log.error("Plot not found for id : {}", plotId);
      throw new PlotNotFoundException("Plot not found for id : " + plotId);
    }
    plotBody.setId(UUID.fromString(plotId));
    return plotRepository.save(plotBody);
  }

  public Plot configurePlot(String plotId, Configuration configuration) {
    Optional<Plot> plotOpt = plotRepository.findById(UUID.fromString(plotId));

    if (plotOpt.isEmpty()) {
      log.error("Plot not found for id : {}", plotId);
      throw new PlotNotFoundException("Plot not found for id : " + plotId);
    }
    Plot plot = plotOpt.get();
    plot.setConfiguration(configuration);
    plotRepository.save(plot);
    return plot;
  }

  public void assignSensor(String plotId) {
    Optional<Plot> plotOpt = plotRepository.findById(UUID.fromString(plotId));

    if (plotOpt.isEmpty()) {
      log.error("Plot not found for id : {}", plotId);
      throw new PlotNotFoundException("Plot not found for id : " + plotId);
    }

    Plot plot = plotOpt.get();
    plot.setStatus(Status.STOPPED);
    plotRepository.save(plot);
  }
}
