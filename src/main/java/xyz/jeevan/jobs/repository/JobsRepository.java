package xyz.jeevan.jobs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import xyz.jeevan.jobs.domain.Offer;

public interface JobsRepository extends MongoRepository<Offer, String> {

}
