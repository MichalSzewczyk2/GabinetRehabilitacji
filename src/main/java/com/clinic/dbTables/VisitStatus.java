package com.clinic.dbTables;

public enum VisitStatus {
    PENDING("pending"),
    COMPLETED("completed"),
    CANCELED("canceled"),;

    VisitStatus(String pending) {
    }

    public static VisitStatus setVisitStatus(String visitStatus) {
        return switch (visitStatus) {
            case "pending" -> VisitStatus.PENDING;
            case "completed" -> VisitStatus.COMPLETED;
            case "cancelled" -> VisitStatus.CANCELED;
            default -> null;
        };
    }
}
