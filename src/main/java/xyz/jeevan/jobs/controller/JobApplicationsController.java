package xyz.jeevan.jobs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.jeevan.jobs.domain.Application;
import xyz.jeevan.jobs.service.jobs.applications.JobApplicationService;
import xyz.jeevan.jobs.util.AppConstants;

@RestController
@RequestMapping(value = AppConstants.APPLICATIONS_API_ENDPOINT)
@Api(value = "Job Applications", description = "APIs for managing job applications.")
public class JobApplicationsController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(JobApplicationsController.class);

  @Autowired
  private JobApplicationService jobApplicationService;

  @ApiOperation(value = "Fetch job application by id.",
      notes = "API to fetch job application by id.",
      response = Application.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Job application information has been fetched successfully by id.")
  })
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Application> getJobApplicationById(@PathVariable final String id) {
    LOG.info("Incoming request to fetch job application by id - {}", id);
    return new ResponseEntity<>(jobApplicationService.getById(id), HttpStatus.OK);
  }

  @ApiOperation(value = "Apply for the job",
      notes = "API to apply for the job.",
      response = Application.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully applied for the job.")
  })
  @GetMapping(value = "/apply", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Application> applyForJob(
      @RequestParam(value = "jobId") final String jobId,
      @RequestParam(value = "candidateEmail") final String candidateEmail) {
    LOG.info("{} applying for the job - {}", candidateEmail, jobId);
    return new ResponseEntity<>(jobApplicationService.apply(jobId, candidateEmail), HttpStatus.OK);
  }

  @ApiOperation(value = "List job applications by candidate email.",
      notes = "API to fetch job applications by candidate email.",
      response = Application.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully fetched job applications by candidate email.")
  })
  @GetMapping(value = "/jobs/{email}/candidate", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Application>> jobApplicationsByCandidate(
      @PathVariable("email") final String candidateEmail) {
    LOG.info("List applications by candidate email {}", candidateEmail);
    return new ResponseEntity<>(jobApplicationService.getByCandidate(candidateEmail),
        HttpStatus.OK);
  }

  @ApiOperation(value = "List job applications by job id.",
      notes = "API to fetch job applications by job id.",
      response = Application.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully fetched job applications by candidate email.")
  })
  @GetMapping(value = "/job/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Application>> applicationsByJobId(
      @PathVariable("jobId") final String jobId) {
    LOG.info("List applications by job id {}", jobId);
    return new ResponseEntity<>(jobApplicationService.getByJobId(jobId), HttpStatus.OK);
  }

  @ApiOperation(value = "Manage status of an application.",
      notes = "API manage status of an application.",
      response = Application.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Successfully changed job applications status.")
  })
  @GetMapping(value = "/{id}/progress/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Application> progress(
      @PathVariable("id") final String id,
      @PathVariable("status") final String status) {
    LOG.info("Change status of application {} to {}", id, status);
    return new ResponseEntity<>(jobApplicationService.changeStatus(id, status), HttpStatus.OK);
  }

}
