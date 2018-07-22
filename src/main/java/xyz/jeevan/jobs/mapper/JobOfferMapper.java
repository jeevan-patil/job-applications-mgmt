package xyz.jeevan.jobs.mapper;

import xyz.jeevan.jobs.JobOffer;
import xyz.jeevan.jobs.domain.Offer;
import xyz.jeevan.jobs.util.DateUtil;

public class JobOfferMapper {

  public static Offer mapNewJobOffer(final JobOffer newOffer) {
    Offer offer = new Offer(newOffer.getTitle(),
        DateUtil.convertFromString(newOffer.getStartDate()));
    return offer;
  }


}
