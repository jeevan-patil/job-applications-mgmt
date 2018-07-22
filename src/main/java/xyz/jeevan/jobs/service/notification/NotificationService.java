package xyz.jeevan.jobs.service.notification;

import xyz.jeevan.jobs.domain.Application;

public interface NotificationService {

  void notifyApplicationStatusChange(final Application application);
}
