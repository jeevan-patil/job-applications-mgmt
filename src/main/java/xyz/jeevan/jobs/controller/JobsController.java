package xyz.jeevan.jobs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.jeevan.jobs.dto.JobOffer;
import xyz.jeevan.jobs.domain.Offer;
import xyz.jeevan.jobs.service.jobs.JobsService;
import xyz.jeevan.jobs.util.AppConstants;

@RestController
@RequestMapping(value = AppConstants.JOBS_API_ENDPOINT)
@Api(value = "Job Offers", description = "APIs for managing jobs/offers.")
public class JobsController {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(JobsController.class);

  @Autowired
  private JobsService jobsService;

  @ApiOperation(value = "Fetch job offer by id.",
      notes = "API to fetch job offer by id.",
      response = Offer.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Job offer information has been fetched successfully by id.")
  })
  @GetMapping(value = "/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Offer> getJobById(@PathVariable final String jobId) {
    LOG.info("Incoming request to fetch job offer by id - {}", jobId);
    return new ResponseEntity<>(jobsService.getById(jobId), HttpStatus.OK);
  }

  @ApiOperation(value = "Fetch job offers.",
      notes = "API to fetch job offers.",
      response = Offer.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Job offers have been fetched successfully.")
  })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Offer>> getAll(
      @RequestParam(value = "page") Integer page,
      @RequestParam(value = "size") Integer size) {
    LOG.info("Incoming request to fetch job offers by page - {} and size {}", page, size);
    List<Offer> jobOffers = jobsService.getByPages(page, size);
    return new ResponseEntity<>(jobOffers, HttpStatus.OK);
  }

  @ApiOperation(value = "Create new job offer.",
      notes = "API to create new job offer.",
      response = Offer.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Job offers have been fetched successfully.")
  })
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Offer> create(@Valid final JobOffer offer) {
    LOG.info("Incoming request to create new job offer.");
    return new ResponseEntity<>(jobsService.create(offer), HttpStatus.CREATED);
  }

}
