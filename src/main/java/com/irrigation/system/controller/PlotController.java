package com.irrigation.system.controller;

import com.irrigation.system.model.Configuration;
import com.irrigation.system.model.Plot;
import com.irrigation.system.service.PlotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/plot")
public class PlotController {

  @Autowired
  private PlotService plotService;

  @GetMapping
  // TODO : Instead of get all - it should be fetch by filter and should return Pageable object
  public ResponseEntity<List<Plot>> getPlots() {
    List<Plot> plots = plotService.getPlots();
    return new ResponseEntity<>(plots, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Plot> addPlot(@RequestBody Plot plotBody) {
    Plot plot = plotService.addPlot(plotBody);
    return new ResponseEntity<>(plot, HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Plot> updatePlot(@RequestBody Plot plotBody) {
    Plot plot = plotService.updatePlot(plotBody);
    return new ResponseEntity<>(plot, HttpStatus.OK);
  }

  @PutMapping(value =  "/{plot_id}/configure")
  public ResponseEntity<Plot> configurePlot(@PathVariable(value = "plot_id") String plotId,
      @RequestBody Configuration configuration) {
    Plot plot = plotService.configurePlot(plotId, configuration);
    return new ResponseEntity<>(plot, HttpStatus.OK);
  }

}
