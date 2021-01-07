package project.service;

import com.sun.research.ws.wadl.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import project.ServiceRun;
import project.api.ClientController;
import project.client.ClientServiceHttp;
import project.config.RestTemplateConfig;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
/*@AutoConfigureMockMvc*/
/*@WebMvcTest(ClientController.class)
@ContextConfiguration(classes= {ServiceRun.class, RestTemplateConfig.class} )*/

public class ClientServiceTest {

/*    @MockBean
    private ClientServiceHttp clientServiceHttp;

    @Autowired
    private RestTemplate restTemplate;*/

/*    @MockBean
    private ClientService clientService;*/

/*    @InjectMocks
    ClientController clinetController;*/

    @Autowired
    private MockMvc mockMvc;

    @Value("${server.port}")
    private String port;

    private final static String STATUS = "availabel";

    private final static String baseUrl = "http://localhost:";

    @LocalServerPort
    int randomServerPort;

    @Test
    public void getCustomersTest() throws Exception {
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

/*                when(clientServiceHttp.fetchCustomers("available"))
                .thenReturn(petstoreList);*/

/*        when(clientService.getCustomers(null))
                .thenReturn(responseJsonList);*/

                MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/client")
                        .accept(MediaType.APPLICATION_JSON)).andReturn();

     /*      ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,
                String.class);  */

/*           ResponseEntity<List<ResponseJson>> responseEntity = clinetController.getAvailabelCustomers();*/

/*        ResponseEntity<List<ResponseJson>> responseEntity = getMethods(url);*/

/*        Assert.assertEquals(200, responseEntity.getStatusCodeValue());*/

    }
/*
    public ResponseEntity<List<ResponseJson>> getMethods(String url){
        ResponseEntity<List<ResponseJson>> responseJsonList = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ResponseJson>>() {
        });
        return responseJsonList;
    }*/

}


