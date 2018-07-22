package xyz.jeevan.jobs.service.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner.StrictStubs;
import xyz.jeevan.jobs.domain.Offer;
import xyz.jeevan.jobs.repository.JobsRepository;

@RunWith(StrictStubs.class)
public class JobsServiceImplTest {

  @InjectMocks
  private JobsServiceImpl cut;

  @Mock
  private JobsRepository mockJobsRepository;

  @Test
  public void getById() {
    final String jobId = "ec35fae9-fe26-48f7-a685-3297316f4bad";

    Offer offer = new Offer("Tech Lead", new Date());
    Optional<Offer> optionalOffer = Optional.of(offer);

    when(mockJobsRepository.findById(jobId)).thenReturn(optionalOffer);

    Offer response = cut.getById(jobId);
    assertNotNull(response);
    assertEquals(offer.getJobTitle(), response.getJobTitle());
    assertEquals(offer.getNumberOfApplications(), response.getNumberOfApplications());

    verify(mockJobsRepository).findById(jobId);
    verifyNoMoreInteractions(mockJobsRepository);
  }

}
