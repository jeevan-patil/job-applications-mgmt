package xyz.jeevan.jobs.util;

/**
 * Application constants reside here.
 */
public final class AppConstants {

  public static final String API_VERSION = "v1";
  public static final String ENDPOINTS_FOR_SWAGGER = "/" + API_VERSION + ".*";
  public static final String JOBS_API_ENDPOINT = "/" + API_VERSION + "/job/";
  public static final String APPLICATIONS_API_ENDPOINT = "/" + API_VERSION + "/applications/";

  private AppConstants() {
  }
}
