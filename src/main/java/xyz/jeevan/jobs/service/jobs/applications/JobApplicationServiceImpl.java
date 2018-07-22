package xyz.jeevan.jobs.service.jobs.applications;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import xyz.jeevan.jobs.domain.Application;
import xyz.jeevan.jobs.domain.Offer;
import xyz.jeevan.jobs.enums.ApplicationStatus;
import xyz.jeevan.jobs.event.ApplicationStatusChangeEvent;
import xyz.jeevan.jobs.exception.ApplicationException;
import xyz.jeevan.jobs.exception.ErrorResponseEnum;
import xyz.jeevan.jobs.repository.JobApplicationRepository;
import xyz.jeevan.jobs.repository.JobsRepository;
import xyz.jeevan.jobs.service.jobs.JobsService;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(JobApplicationServiceImpl.class);

  @Autowired
  private JobsService jobsService;

  @Autowired
  private JobsRepository jobsRepository;

  @Autowired
  private JobApplicationRepository jobApplicationRepository;

  @Autowired
  private ApplicationEventPublisher applicationEventPublisher;

  @Override
  public Application apply(final String jobId, final String candidateEmail) {
    Offer offer = jobsService.getById(jobId);

    // check if candidate has already applied for the job
    if (hasAlreadyAppliedForJob(candidateEmail, jobId)) {
      LOG.error("{} has already applied by job {}", candidateEmail, jobId);
      throw new ApplicationException(ErrorResponseEnum.ALREADY_APPLIED_FOR_JOB);
    }

    // apply for the job
    Application application = new Application(jobId, candidateEmail);
    Application appliedJob = jobApplicationRepository.save(application);

    // increment job applicant count
    offer.candidateAppliedForJob();
    jobsRepository.save(offer);

    return appliedJob;
  }

  @Override
  public Application getById(final String id) {
    try {
      return jobApplicationRepository.findById(id).get();
    } catch (Exception e) {
      LOG.error("Could not find job application by id {}", id);
      throw new ApplicationException(ErrorResponseEnum.JOB_APPLICATION_NOT_FOUND);
    }
  }

  @Override
  public List<Application> getByJobId(final String jobId) {
    return jobApplicationRepository.findByJobId(jobId);
  }

  @Override
  public List<Application> getByCandidate(final String candidateEmail) {
    return jobApplicationRepository.findByCandidateEmail(candidateEmail);
  }

  @Override
  public Application changeStatus(String id, String status) {
    boolean isNewStatusValid = ApplicationStatus.validate(status);

    if (!isNewStatusValid) {
      LOG.error("Could not change application status because it is invalid.");
      throw new ApplicationException(ErrorResponseEnum.APPLICATION_STATUS_INVALID);
    }

    Application application = getById(id);
    application.changeStatus(status);
    publishApplicationStatusChangeEvent(application);
    return jobApplicationRepository.save(application);
  }

  @Override
  public Application getByCandidateAndJob(String candidateEmail, String jobId) {
    return jobApplicationRepository.findByCandidateEmailAndJobId(candidateEmail, jobId);
  }

  @Override
  public boolean hasAlreadyAppliedForJob(String candidateEmail, String jobId) {
    Application application = getByCandidateAndJob(candidateEmail, jobId);
    return (application != null);
  }

  void publishApplicationStatusChangeEvent(final Application application) {
    ApplicationStatusChangeEvent entityCreatedEvent = new ApplicationStatusChangeEvent(this,
        application);
    applicationEventPublisher.publishEvent(entityCreatedEvent);
  }
}
