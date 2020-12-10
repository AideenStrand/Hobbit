package project.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.client.ClientServiceHttp;
import project.data.ResponseJson;

import java.util.ArrayList;
import java.util.List;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(classes = { ApplicationConfig.class })*/
@ContextConfiguration(locations = {""})
@WebAppConfiguration
public class ClientServiceTest {

    @Mock
    private ClientServiceHttp clientServiceHttp;

    @Autowired
    private ClientService clientService;

    private MockMvc mockMvc;

    private final static String STATUS = "availabel";

/*
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getCustomersTest() {
        List<ResponseJson> responseJsonList = new ArrayList<>();
        When()
    }
*/

}
