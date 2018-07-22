package xyz.jeevan.jobs.helper;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class PaginationHelper {

  private final int defaultPageSize = 10;

  private int refinePage(Integer page) {
    if (page == null || page < 0) {
      return 0;
    }
    return page;
  }

  private int refineSize(Integer size) {
    if (size == null || size < 0) {
      return defaultPageSize;
    }
    return size;
  }

  public PageRequest pageRequest(int page, int size) {
    return new PageRequest(refinePage(page), refineSize(size));
  }


}
