package com.irrigation.system.controller;

import com.irrigation.system.model.Plot;
import com.irrigation.system.service.IrrigationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sensor")
public class SensorController {

  @Autowired
  private IrrigationService irrigationService;

  @GetMapping(value = "/irrigation/plots")
  public ResponseEntity<List<Plot>> getPlotsForIrrigation() {
    List<Plot> plots = irrigationService.getPlotsForIrrigation();
    return new ResponseEntity<>(plots, HttpStatus.OK);
  }

  @PostMapping(value = "/irrigate/plot/{plot_id}")
  public ResponseEntity<?> irrigatePlot(@PathVariable(value = "plot_id") String plotId) {
    irrigationService.irrigatePlot(plotId);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

}
