package project.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import project.client.ClientServiceHttp;
import project.data.CostumerInformation;
import project.data.ResponseJson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientServiceHttp clientServiceHttp;

    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private ClientService clientService;

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

        String url = baseUrl + "8082" + "/api/client/available";

         /*       when(clientService.getCustomers("available"))
                .thenReturn(responseJsonList);*/

        RestTemplate restTemplate = new RestTemplate();

           ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,
                String.class);

/*        ResponseEntity<ResponseJson[]> responseEntity = restTemplate.getForEntity(url,
                ResponseJson[].class);*/

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

    }

/*    public ResponseEntity<List<ResponseJson>> getMethods(String url, String value){
        ResponseEntity<List<ResponseJson>> responseJsonList = restTemplate.exchange("localhost:8080/api/client/available",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResponseJson>>() {
        });
        return responseJsonList;
    }*/

}


