package project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import project.api.ClientController;
import project.client.ClientServiceHttp;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientController clientController;
    @Autowired
    ClientService clientService;

    @MockBean
    ClientServiceHttp clientServiceHttp;

    private final static String LOCAT_HOST = "http://localhost:";

    private String PORT = "8082";

    private final static String Available_Customer_URL = "/api/client";

    @Test
    public void mytest() {
        List<ResponseJson> responseJsonList = new ArrayList<>();
        ResponseJson responseJson = new ResponseJson();
        CostumerInformation costumerInformation = new CostumerInformation.MyBuilder()
                .name("name")
                .family("family")
                .personalId("123").myBuild();
        responseJson.setCostumerInformation(costumerInformation);
        responseJsonList.add(responseJson);
        List<Petstore> petstoreList = new ArrayList<>();
        Petstore petstore = new Petstore();
        petstore.setName("name");
        petstore.setId("id");
        petstoreList.add(petstore);
        when(clientServiceHttp.fetchCustomers(null))
                .thenReturn(Collections.singletonList(petstore));
        clientController.getAvailabelCustomers();
        ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(LOCAT_HOST
                        + PORT + Available_Customer_URL,
                Object[].class);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

    }

   /* public <T> ResponseEntity<T> getForEntityRest(String url){

        ResponseEntity<ResponseJson> responseEntity;

        responseEntity = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ResponseJson>() {
                });

        return responseEntity.getBody();*/
    //}
}
