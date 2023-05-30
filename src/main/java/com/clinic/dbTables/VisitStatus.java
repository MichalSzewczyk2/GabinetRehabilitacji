package com.clinic.dbTables;

public enum VisitStatus {
  PENDING("pending"),
  ENDED("ended"),
  CANCELED("canceled"),
  ;

  VisitStatus(String status) {
  }

  public static VisitStatus setVisitStatus(String visitStatus) {
    return switch (visitStatus.toLowerCase()) {
      case "pending" -> VisitStatus.PENDING;
      case "ended" -> VisitStatus.ENDED;
      case "cancelled" -> VisitStatus.CANCELED;
      default -> null;
    };
  }
}
