package xyz.jeevan.jobs.domain;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document(collection = "offers")
public class Offer {

  @Id
  private String id;

  @NotNull(message = "Job title can not be null")
  private String jobTitle;
  private long numberOfApplications;

  @DateTimeFormat(iso = ISO.DATE)
  private Date startDate;

  public Offer(String jobTitle, Date startDate) {
    this.id = UUID.randomUUID().toString();
    this.jobTitle = jobTitle;
    this.startDate = startDate;
    this.numberOfApplications = 0;
  }

  public String getId() {
    return id;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public Date getStartDate() {
    return startDate;
  }

  public long getNumberOfApplications() {
    return numberOfApplications;
  }

  public void candidateAppliedForJob() {
    ++this.numberOfApplications;
  }
}
