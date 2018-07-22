package xyz.jeevan.jobs;

import javax.validation.constraints.NotNull;

public class JobOffer {

  @NotNull(message = "Start date can not be null. Should in in format MM/dd/yyyy")
  private String startDate;

  @NotNull(message = "Job title can not be null.")
  private String title;

  public JobOffer(String startDate, String title) {
    this.startDate = startDate;
    this.title = title;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getTitle() {
    return title;
  }
}
