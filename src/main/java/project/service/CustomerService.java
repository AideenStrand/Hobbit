package project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.client.GalacServiceClient;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;


@Slf4j
@Service
public class CustomerService {

    @Autowired
    private GalacServiceClient galacServiceClient;

    public ResponseJson getAip(String id) {
        ResponseJson responseJson = new ResponseJson();
        Petstore petstore = galacServiceClient.getResponseFromDatabase(id);

        String name = petstore.getName();

        CostumerInformation costumerInformation = new CostumerInformation.MyBuilder()
                .name(name)
                .family("berger")
                .personalId(id)
                .myBuild();

        responseJson.setCompleteName(costumerInformation.getName() + "  " + costumerInformation.getFamily());
        responseJson.setCostumerInformation(costumerInformation);
        return responseJson;
    }

}
