package project.service;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import project.client.ClientServiceHttp;

@RunWith(SpringRunner.class)
public class TestServiceTest {

    @Mock
    private ClientServiceHttp galacServiceClient;

    @Mock
    private RestTemplate restTemplate;

}