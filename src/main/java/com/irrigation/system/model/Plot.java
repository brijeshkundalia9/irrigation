package com.irrigation.system.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "plots")
public class Plot {

  @Id
  @GeneratedValue
  private UUID id;
  private int size;
  private String unit;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "configuration_id", referencedColumnName = "id")
  private Configuration configuration;

  private Status status = Status.MISSING_SENSOR;
  private int missingSensorAlert = 0;
}
