package com.irrigation.system.service;

import com.irrigation.system.exception.ConfigurationNotFoundException;
import com.irrigation.system.exception.InvalidStatusException;
import com.irrigation.system.exception.PlotNotFoundException;
import com.irrigation.system.model.Alert;
import com.irrigation.system.model.Configuration;
import com.irrigation.system.model.Plot;
import com.irrigation.system.model.Status;
import com.irrigation.system.repository.AlertRepository;
import com.irrigation.system.repository.PlotRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IrrigationService {

  @Autowired
  private PlotRepository plotRepository;

  @Autowired
  private AlertRepository alertRepository;

  public List<Plot> getPlotsForIrrigation() {
    return plotRepository.getPlotsForIrrigation(System.currentTimeMillis());
  }

  public void irrigatePlot(String plotId) {
    Optional<Plot> plotOpt = plotRepository.findById(UUID.fromString(plotId));

    if (!plotOpt.isPresent()) {
      log.error("Plot not found for id : {}", plotId);
      throw new PlotNotFoundException("Plot not found for id : " + plotId);
    }

    Plot plot = plotOpt.get();

    Configuration configuration = plot.getConfiguration();
    if (configuration == null) {
      log.error("No configuration found for plot {}", plotId);
      throw new ConfigurationNotFoundException("No configuration found for plot " + plotId);
    }

    switch (plot.getStatus()) {
      case MISSING_SENSOR -> {
        int missingSensor = plot.getMissingSensorAlert();
        plot.setMissingSensorAlert(++missingSensor);
        plotRepository.save(plot);
        Alert alert = new Alert();
        alert.setPlot(plot);
        alert.setReasonToAlert(Status.MISSING_SENSOR);
        alert.setGeneratedAt(LocalDateTime.now());
        alertRepository.save(alert);
        log.error("Missing sensor error for plot {}", plotId);
        throw new InvalidStatusException("Missing sensor error for plot " + plotId);
      }
      case IN_PROGRESS -> {
        log.error("Irrigation already in progress for plot {}", plotId);
        throw new InvalidStatusException("Irrigation already in progress for plot " + plotId);
      }
      case STOPPED -> {
        plot.getConfiguration().setLastIrrigationStart(System.currentTimeMillis());
        plot.setStatus(Status.IN_PROGRESS);
        // TODO : Create a job which runs separately to make status from in_progress to stopped
        plotRepository.save(plot);
      }
    }
  }

}
