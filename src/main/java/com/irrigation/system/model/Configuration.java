package com.irrigation.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "configurations")
public class Configuration {
  @Id
  @GeneratedValue
  private UUID id;
  private CropType cropType;
  private Long reqTimeToIrrigate;
  private Long lastIrrigationStart;
  private Long lastIrrigationEnd;
}
