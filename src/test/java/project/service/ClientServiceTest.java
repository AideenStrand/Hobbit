package project.service;

import org.glassfish.jersey.servlet.WebConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import project.api.ClinetController;
import project.client.ClientServiceHttp;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration*/
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ClientServiceTest {

    @Mock
    private ClientServiceHttp clientServiceHttp;

    @Autowired
    private RestTemplate restTemplate;

    @InjectMocks
    private ClientService clientService;

    @InjectMocks
    ClinetController clinetController;

    @Autowired
    private MockMvc mockMvc;

    @Value("${server.port}")
    private String port;

    private final static String STATUS = "availabel";

    private final static String baseUrl = "http://localhost:";

    @LocalServerPort
    int randomServerPort;

    @Before
    public void setUp() throws Exception {
/*        this.base = new URL("http://localhost:" + port + "/rest/customers");
        template = new TestRestTemplate();*/
    }

    @Test
    public void getCustomersTest() throws URISyntaxException {
        List<ResponseJson> responseJsonList = new ArrayList<>();
        ResponseJson responseJson = new ResponseJson();
        CostumerInformation costumerInformation = new CostumerInformation.MyBuilder()
                .name("name")
                .family("family")
                .personalId("123").myBuild();

        responseJson.setCostumerInformation(costumerInformation);
        responseJsonList.add(responseJson);


        List<Petstore> petstoreList = new ArrayList<>();

        String url = baseUrl + "8082" + "/api/client";

                when(clientServiceHttp.fetchCustomers("available"))
                .thenReturn(petstoreList);


     /*      ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,
                String.class);  */

           ResponseEntity<List<ResponseJson>> responseEntity = clinetController.getAvailabelCustomers();

/*        ResponseEntity<List<ResponseJson>> responseEntity = getMethods(url);*/

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

    }

    public ResponseEntity<List<ResponseJson>> getMethods(String url){
        ResponseEntity<List<ResponseJson>> responseJsonList = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResponseJson>>() {
        });
        return responseJsonList;
    }

}


