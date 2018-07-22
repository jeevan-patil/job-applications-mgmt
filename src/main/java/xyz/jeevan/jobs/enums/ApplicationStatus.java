package xyz.jeevan.jobs.enums;

public enum ApplicationStatus {
  APPLIED, INVITED, REJECTED, HIRED;

  public static boolean validate(final String status) {
    for (ApplicationStatus applicationStatus : values()) {
      if (applicationStatus.toString().equalsIgnoreCase(status)) {
        return true;
      }
    }

    return false;
  }
}
