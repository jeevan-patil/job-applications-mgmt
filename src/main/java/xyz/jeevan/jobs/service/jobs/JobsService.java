package xyz.jeevan.jobs.service.jobs;

import java.util.List;
import xyz.jeevan.jobs.dto.JobOffer;
import xyz.jeevan.jobs.domain.Offer;

public interface JobsService {

  /**
   * Fetch job offer data by id.
   *
   * @param id Job/offer id.
   * @return {@code Offer} Offer information.
   */
  Offer getById(final String id);

  /**
   * Fetch job offers. This is pageable fetch.
   *
   * @param page Page number.
   * @param size Page size.
   * @return {@code List<Offer>} List of job offers.
   */
  List<Offer> getByPages(Integer page, Integer size);

  /**
   * Create new job offer.
   *
   * @param newOffer Job offer details.
   * @return {@code Offer} Created offer.
   */
  Offer create(final JobOffer newOffer);

}
