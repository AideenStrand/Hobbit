package project.service;

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
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    private final static String baseUrl = "http://localhost:";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ClientController clientController;
    @Autowired
    ClientService clientService;

    @MockBean
    ClientServiceHttp clientServiceHttp;

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
        // Assert.assertEquals("true", "true");
        List<Petstore> petstoreList = new ArrayList<>();
        Petstore petstore = new Petstore();
        petstore.setName("name");
        petstore.setId("id");
        petstoreList.add(petstore);


        String url = baseUrl + "8082" + "/api/client";
        ResponseEntity<ResponseJson[]> responseEntity = restTemplate.getForEntity(url,
                ResponseJson[].class);
        clientController.getAvailabelCustomers();
        when(clientServiceHttp.fetchCustomers(null)).thenReturn(petstoreList);
    }

}
