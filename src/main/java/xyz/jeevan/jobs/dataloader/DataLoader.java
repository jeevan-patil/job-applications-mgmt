package xyz.jeevan.jobs.dataloader;

import java.sql.Date;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.jeevan.jobs.domain.Offer;
import xyz.jeevan.jobs.repository.JobsRepository;

@Component
public class DataLoader implements CommandLineRunner {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(DataLoader.class);

  private JobsRepository jobsRepository;

  public DataLoader(JobsRepository jobsRepository) {
    this.jobsRepository = jobsRepository;
  }

  @Override
  public void run(String... strings) throws Exception {
    LOG.info("Loading data...");
    LocalDate localDate = LocalDate.now();

    jobsRepository
        .save(new Offer("Software Developer - Java", Date.valueOf(localDate.plusDays(11))));
    jobsRepository
        .save(new Offer("Software Developer - JavaScript", Date.valueOf(localDate.plusDays(20))));
    jobsRepository.save(new Offer("Full stack developer", Date.valueOf(localDate.plusDays(40))));
    jobsRepository
        .save(new Offer("Software Developer - Python", Date.valueOf(localDate.plusDays(4))));
    LOG.info("Finished loading data...");
  }

}
