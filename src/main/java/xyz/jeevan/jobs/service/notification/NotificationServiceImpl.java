package xyz.jeevan.jobs.service.notification;

import org.springframework.stereotype.Service;
import xyz.jeevan.jobs.domain.Application;

@Service
public class NotificationServiceImpl implements NotificationService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(NotificationServiceImpl.class);

  @Override
  public void notifyApplicationStatusChange(Application application) {
    LOG.info(
        "NOTIFICATION :: Send notification to user {} and convey that application status has been changed to {}",
        application.getCandidateEmail(), application.getApplicationStatus());
  }
}
