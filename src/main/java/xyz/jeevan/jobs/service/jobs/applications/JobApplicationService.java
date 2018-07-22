package xyz.jeevan.jobs.service.jobs.applications;

import java.util.List;
import xyz.jeevan.jobs.domain.Application;

public interface JobApplicationService {

  /**
   * Get job application details by id.
   *
   * @param id Unique application id.
   * @return {@code {@link Application}} Application information.
   */
  Application getById(final String id);

  /**
   * Get applications by job id.
   *
   * @param jobId Job id.
   * @return Job applications.
   */
  List<Application> getByJobId(final String jobId);

  /**
   * Get applications by candidate.
   *
   * @param candidateEmail Candidate email.
   * @return Job applications.
   */
  List<Application> getByCandidate(final String candidateEmail);

  /**
   * Get applications by candidate and job id.
   *
   * @param candidateEmail Candidate email.
   * @param jobId Job ID.
   * @return Application information.
   */
  Application getByCandidateAndJob(final String candidateEmail, final String jobId);

  /**
   * Check if candidate has already applied for the job.
   *
   * @param candidateEmail Candidate email.
   * @param jobId Job ID.
   * @return True if already applier, false otherwise.
   */
  boolean hasAlreadyAppliedForJob(final String candidateEmail, final String jobId);

  /**
   * Method which facilitates job application.
   *
   * @param jobId Job id.
   * @param candidateEmail Candidate email.
   * @return Application information.
   */
  Application apply(final String jobId, final String candidateEmail);

  /**
   * Change status of application.
   *
   * @param id Application id.
   * @param status Application status.
   * @return Application information.
   */
  Application changeStatus(final String id, final String status);

}
