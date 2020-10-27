package project.service;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.yetus.audience.InterfaceAudience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import project.client.ClientServiceHttp;
import project.client.DatabaseHttp;
import project.data.CostumerInformation;
import project.data.Petstore;
import project.data.ResponseJson;
import project.data.TimeRegister;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class ClientService implements TimeRegister {

    @Autowired
    private ClientServiceHttp clientServiceHttp;

    @Autowired
    private DatabaseHttp databaseHttp;

    private final static String BIRTHDATE = "12/03/1980";

    public List<ResponseJson> getAip(String status) {

        List<ResponseJson> responseJsonList = new LinkedList<>();
        List<Petstore> petstore = clientServiceHttp.makeRequest(status,
                new ParameterizedTypeReference<List<Petstore>>() {
                });

        HashMap<String, String> customerName = nameFamilyMaker();
        CostumerInformation costumerInformation = null;

        if (fixBirthDate(BIRTHDATE)) {
            for (Map.Entry name : customerName.entrySet()) {
                ResponseJson responseJson = new ResponseJson();
                costumerInformation = new CostumerInformation.MyBuilder()
                        .name(name.getKey().toString())
                        .family(name.getValue().toString())
                        .personalId(petstore.stream().map(c -> c.getId()).findFirst().orElse(null))
                        .myBuild();
                responseJson.setCompleteName(costumerInformation.getName() + "  " + costumerInformation.getFamily());
                responseJson.setCostumerInformation(costumerInformation);
                responseJsonList.add(responseJson);
            }
        }
        return responseJsonList;
    }

    public Object getData() throws SQLException {
        databaseHttp.getDatafromW3();
        return null;
    }

    private <T> T nameFamilyMaker() {
        HashMap<String, String> customerName = new HashMap<>();
        customerName.put("cai", "berger");
        customerName.put("narges", "amiri");
        return (T) customerName;
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

    public String modifyInfo(JsonPatch jsonPatch) {
        return null;
    }


    public  Map<String, String> ValidateUrl() {

    Map<String, String> pathUrl = new HashMap<>();

    
    return pathUrl;

}
}
