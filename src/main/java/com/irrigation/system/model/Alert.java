package com.irrigation.system.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;

@Data
@Entity
@Table(name = "alerts")
public class Alert {

  @Id
  @GeneratedValue
  private UUID id;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "plot_id", referencedColumnName = "id")
  private Plot plot;

  private Status reasonToAlert;
  private LocalDateTime generatedAt;

}
