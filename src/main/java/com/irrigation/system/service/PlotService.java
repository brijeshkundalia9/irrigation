package com.irrigation.system.service;

import com.irrigation.system.model.Configuration;
import com.irrigation.system.model.Plot;
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
    Plot plot = plotRepository.save(plotBody);
    return plot;
  }

  public Plot updatePlot(Plot plotBody) {
    Plot plot = plotRepository.save(plotBody);
    return plot;
  }

  public Plot configurePlot(String plotId, Configuration configuration) {
    Optional<Plot> plotOpt = plotRepository.findById(UUID.fromString(plotId));

    if (!plotOpt.isPresent()) {
      log.error("Plot not found for id : {}", plotId);
      // TODO : Throw plot not found exception
    }
    Plot plot = plotOpt.get();
    plot.setConfiguration(configuration);
    plotRepository.save(plot);
    return plot;
  }
}
