package com.irrigation.system.model;

public enum Status {

  STOPPED("Stopped"),
  IN_PROGRESS("In Progress"),
  MISSING_SENSOR("Sensor is missing");

  Status(String status) {
  }
}
