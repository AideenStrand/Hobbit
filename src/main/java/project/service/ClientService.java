package project.service;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.client.ClientServiceHttp;
import project.client.DatabaseHttp;
import project.client.OrderServiceHttp;
import project.data.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class ClientService implements TimeRegister {

    @Autowired
    private ClientServiceHttp clientServiceHttp;

    @Autowired
    private OrderServiceHttp orderServiceHttp;

    @Autowired
    private DatabaseHttp databaseHttp;

    private final static String BIRTHDATE = "12/03/1980";
    private final static String ZERO = "0";
    private final static String AVAILABLE = "available";
    private final static String CONSTANT_NUMBER = "9222968140491051141";

    public List<ResponseJson> getCustomers(String status) {
        List<Petstore> petstore = null;
       // if(status != null || AVAILABLE.equals(status)){
        petstore = clientServiceHttp.fetchCustomers(status);
        //  }
        List<ResponseJson> responseJsonList = new LinkedList<>();
        HashMap<String, String> customerName = nameFamilyMaker();
        CostumerInformation costumerInformation;
        if (fixBirthDate(BIRTHDATE)) {
            for (Map.Entry name : customerName.entrySet()) {
                ResponseJson responseJson = new ResponseJson();
                costumerInformation = new CostumerInformation.MyBuilder()
                        .name(name.getKey().toString())
                        .family(name.getValue().toString())
                        .personalId(petstore.stream().map(c -> c.getId())
                                .findFirst().orElse(CONSTANT_NUMBER))
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

    public OrderRequest storeOrder(OrderRequest orderRequest) {
        List<Petstore> petStoreList = clientServiceHttp.fetchCustomers(AVAILABLE);
        OrderRequest request = petStoreList.stream()
                .filter(petStore -> petStore.getCategory() != null && petStore.getCategory().getId() != null &&
                        !ZERO.equals(petStore.getCategory().getId()))
                .map(petStore -> mapRequest(petStore, orderRequest))
                .findFirst().orElse(null);
        ResponseEntity<OrderRequest> response = orderServiceHttp.registerOrder(request, OrderRequest.class);
        return response.getBody();

    }

    private OrderRequest mapRequest(Petstore petStore, OrderRequest request) {
        request.setPetId(petStore.getCategory().getId());
        request.setStatus(petStore.getStatus());
        return request;
    }

    public String modifyInfo(JsonPatch jsonPatch) {
        return null;
    }

    public Map<String, String> ValidateUrl() {
        Map<String, String> pathUrl = new HashMap<>();
        return pathUrl;

    }
}
