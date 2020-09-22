package project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import project.client.ClientServiceHttp;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;
import project.data.TimeRegister;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ClientService implements TimeRegister {

    @Autowired
    private ClientServiceHttp galacServiceClient;

    private final static String BIRTHDATE = "12/03/1980";

    public ResponseJson getAip(String status) {
        ResponseJson responseJson = new ResponseJson();
        List<Petstore> petstore = galacServiceClient.getResponseFromDatabase(status);
        String name = petstore.stream().map(c -> c.getName()).findFirst().orElse(null);
        if (fixBirthDate(BIRTHDATE)) {
            CostumerInformation costumerInformation = new CostumerInformation.MyBuilder()
                    .name(name)
                    .family("berger")
                    .personalId("12")
                    .myBuild();
            responseJson.setCompleteName(costumerInformation.getName() + "  " + costumerInformation.getFamily());
            responseJson.setCostumerInformation(costumerInformation);

        }
        return responseJson;
    }

    @Override
    public Boolean fixBirthDate(String date) {
        Date today = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(today);
        if (BIRTHDATE.equals(stringDate)) {
            return false;
        }
        return true;
    }
}
