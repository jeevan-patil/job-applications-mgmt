package xyz.jeevan.jobs.event;

import org.springframework.context.ApplicationEvent;

public class ApplicationStatusChangeEvent<T> extends ApplicationEvent {

  private T entity;

  public ApplicationStatusChangeEvent(Object source, T entity) {
    super(source);
    this.entity = entity;
  }

  public T getEntity() {
    return entity;
  }
}
