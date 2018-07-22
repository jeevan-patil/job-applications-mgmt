package xyz.jeevan.jobs.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.jeevan.jobs.exception.ApplicationException;
import xyz.jeevan.jobs.exception.ErrorResponse;
import xyz.jeevan.jobs.exception.ErrorResponseEnum;

/**
 * This advice is used to send meaningful errors in the form of JSON back to client.
 */
@RestControllerAdvice(basePackages = {"xyz.jeevan.jobs"})
public class ExceptionHandlerAdvice {

  private static final org.slf4j.Logger _log = org.slf4j.LoggerFactory
      .getLogger(ExceptionHandlerAdvice.class);

  @ExceptionHandler({ApplicationException.class})
  public @ResponseBody
  ResponseEntity<ErrorResponse> handleApplicationException(
      ApplicationException applicationException) {
    _log.warn("RuntimeException is thrown : ");

    // Create error response object.
    ErrorResponse errorResponse = new ErrorResponse(
        applicationException.getErrorResponse().getCode(),
        applicationException.getErrorResponse().getErrorText());

    _log.warn(errorResponse.toString());
    // Return error response with custom status.
    return new ResponseEntity<>(errorResponse,
        applicationException.getErrorResponse().getHttpStatus());
  }

  @ExceptionHandler({Throwable.class})
  public @ResponseBody
  ResponseEntity<ErrorResponse> handleException(Throwable throwable) {
    _log.error(throwable.getMessage(), throwable);

    ErrorResponse errorResponse = new ErrorResponse(ErrorResponseEnum.GENERAL_ERROR.getCode(),
        ErrorResponseEnum.GENERAL_ERROR.getErrorText());
    _log.error(errorResponse.toString());

    return new ResponseEntity<>(errorResponse, ErrorResponseEnum.GENERAL_ERROR.getHttpStatus());
  }

}
