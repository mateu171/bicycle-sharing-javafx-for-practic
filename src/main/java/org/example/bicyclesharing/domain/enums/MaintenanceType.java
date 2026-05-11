package org.example.bicyclesharing.domain.enums;

public enum MaintenanceType {

  PRE_RENT_INSPECTION("maintenance.pre_rent"),
  POST_RENT_INSPECTION("maintenance.post_rent"),
  REPAIR("maintenance.repair"),
  SERVICE("maintenance.service"),
  WRITE_OFF("maintenance.write_off");

  private final String key;

  MaintenanceType(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }
}
