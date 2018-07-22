package xyz.jeevan.jobs.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.jeevan.jobs.domain.Application;

public interface JobApplicationRepository extends MongoRepository<Application, String> {

  List<Application> findByJobId(final String jobId);

  List<Application> findByCandidateEmail(final String candidateEmail);

  Application findByCandidateEmailAndJobId(final String candidateEmail, final String jobId);

}
