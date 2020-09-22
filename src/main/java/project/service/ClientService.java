package project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.client.ClientServiceHttp;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;

import java.util.List;

@Slf4j
@Service
public class ClientService {

    @Autowired
    private ClientServiceHttp galacServiceClient;

    public ResponseJson getAip(String status) {
        ResponseJson responseJson = new ResponseJson();
        List<Petstore> petstore = galacServiceClient.getResponseFromDatabase(status);

        String name = petstore.stream().map(c->c.getName()).findFirst().orElse(null);

        CostumerInformation costumerInformation = new CostumerInformation.MyBuilder()
                .name(name)
                .family("berger")
                .personalId("12")
                .myBuild();

        responseJson.setCompleteName(costumerInformation.getName() + "  " + costumerInformation.getFamily());
        responseJson.setCostumerInformation(costumerInformation);
        return responseJson;
    }

}
