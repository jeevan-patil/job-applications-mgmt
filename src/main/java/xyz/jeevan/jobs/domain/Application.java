package xyz.jeevan.jobs.domain;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import xyz.jeevan.jobs.enums.ApplicationStatus;

@Document(collection = "applications")
public class Application {

  @Id
  private String id;

  @NotNull(message = "Job id can not be null.")
  private String jobId;

  @NotNull(message = "Candidate email can not be null.")
  private String candidateEmail;
  private String applicationStatus;

  public Application(final String jobId, final String candidateEmail) {
    this.id = UUID.randomUUID().toString();
    this.jobId = jobId;
    this.candidateEmail = candidateEmail;
    this.applicationStatus = ApplicationStatus.APPLIED.toString();
  }

  public String getId() {
    return id;
  }

  public String getJobId() {
    return jobId;
  }

  public String getCandidateEmail() {
    return candidateEmail;
  }

  public String getApplicationStatus() {
    return applicationStatus;
  }

  public void changeStatus(final String status) {
    this.applicationStatus = status;
  }
}
