package xyz.jeevan.jobs.exception;

import org.springframework.http.HttpStatus;

public enum ErrorResponseEnum {
  GENERAL_ERROR(100, "An exception occurred while processing your request.",
      HttpStatus.INTERNAL_SERVER_ERROR),
  JOB_OFFER_NOT_FOUND(101, "Job offer not found", HttpStatus.NOT_FOUND),
  JOB_APPLICATION_NOT_FOUND(102, "Job application not found", HttpStatus.NOT_FOUND),
  APPLICATION_STATUS_INVALID(103, "Job application status is invalid", HttpStatus.BAD_REQUEST),
  ALREADY_APPLIED_FOR_JOB(104, "Candidate has already applied for the job", HttpStatus.BAD_REQUEST);

  private int code;
  private String errorText;
  private HttpStatus httpStatus;

  ErrorResponseEnum(int code, String errorText, HttpStatus httpStatus) {
    this.code = code;
    this.errorText = errorText;
    this.httpStatus = httpStatus;
  }

  public int getCode() {
    return code;
  }

  public String getErrorText() {
    return errorText;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}