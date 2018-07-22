package xyz.jeevan.jobs.helper;

import org.springframework.data.domain.PageRequest;

public class PaginationHelper {

  private static final int defaultPageSize = 10;

  private static int refinePage(Integer page) {
    if (page == null || page < 0) {
      return 0;
    }
    return page;
  }

  private static int refineSize(Integer size) {
    if (size == null || size < 0) {
      return defaultPageSize;
    }
    return size;
  }

  public static PageRequest pageRequest(int page, int size) {
    return new PageRequest(refinePage(page), refineSize(size));
  }


}
