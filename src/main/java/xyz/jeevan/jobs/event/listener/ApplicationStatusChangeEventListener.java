package xyz.jeevan.jobs.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import xyz.jeevan.jobs.domain.Application;
import xyz.jeevan.jobs.event.ApplicationStatusChangeEvent;
import xyz.jeevan.jobs.service.notification.NotificationService;

@Component
public class ApplicationStatusChangeEventListener implements
    ApplicationListener<ApplicationStatusChangeEvent> {

  @Autowired
  private NotificationService notificationService;

  @Override
  public void onApplicationEvent(ApplicationStatusChangeEvent applicationStatusChangeEvent) {
    if (applicationStatusChangeEvent.getEntity() != null) {
      if (applicationStatusChangeEvent.getEntity() instanceof Application) {
        Application application = (Application) applicationStatusChangeEvent.getEntity();
        notificationService.notifyApplicationStatusChange(application);
      }
    }
  }
}
