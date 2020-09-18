package project.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import project.client.GalacServiceClient;

@RunWith(SpringRunner.class)
public class TestServiceTest {

    @Mock
    private GalacServiceClient galacServiceClient;

    @Mock
    private RestTemplate restTemplate;

}