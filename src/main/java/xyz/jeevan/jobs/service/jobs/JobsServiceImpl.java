package xyz.jeevan.jobs.service.jobs;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import xyz.jeevan.jobs.dto.JobOffer;
import xyz.jeevan.jobs.domain.Offer;
import xyz.jeevan.jobs.exception.ApplicationException;
import xyz.jeevan.jobs.exception.ErrorResponseEnum;
import xyz.jeevan.jobs.helper.PaginationHelper;
import xyz.jeevan.jobs.mapper.JobOfferMapper;
import xyz.jeevan.jobs.repository.JobsRepository;

@Service
public class JobsServiceImpl implements JobsService {

  private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
      .getLogger(JobsServiceImpl.class);

  @Autowired
  private JobsRepository jobsRepository;

  @Autowired
  private PaginationHelper paginationHelper;

  @Override
  public Offer getById(String id) {
    Assert.notNull(id, "Job offer id can not be null.");

    try {
      return jobsRepository.findById(id).get();
    } catch (Exception e) {
      throw new ApplicationException(ErrorResponseEnum.JOB_OFFER_NOT_FOUND);
    }
  }

  @Override
  public List<Offer> getByPages(Integer page, Integer size) {
    Page<Offer> jobOffers = jobsRepository.findAll(paginationHelper.pageRequest(page, size));
    return jobOffers.getContent();
  }

  @Override
  public Offer create(JobOffer newOffer) {
    Offer offer = jobsRepository.save(JobOfferMapper.mapNewJobOffer(newOffer));
    return jobsRepository.save(offer);
  }
}
