package project.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.client.ClientServiceHttp;
import project.client.DatabaseHttp;
import project.client.OrderServiceHttp;
import project.data.*;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPatch;
import javax.json.JsonValue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    private final static String OP = "op";
    private final static String PATH = "path";
    private final static String VALUE = "value";
    private final static String TELEPHONE = "/telephone";
    private final static String COUNTRY = "/country";
    private final static String CITY = "/city";

    public List<ResponseJson> getCustomers(String status) {
        List<Petstore> petstore;
        petstore = clientServiceHttp.fetchCustomers(status);
        if (petstore == null || petstore.isEmpty()) {
            petstore = Collections.EMPTY_LIST;
        }
        List<ResponseJson> responseJsonList = new LinkedList<>();
        HashMap<String, String> customerName = nameFamilyMaker();

        CostumerInformation costumerInformation;
        if (fixBirthDate(BIRTHDATE)) {
            for (Map.Entry name : customerName.entrySet()) {

                ResponseJson responseJson = new ResponseJson();

                costumerInformation = new CostumerInformation.MyBuilder()
                        .name(name.getKey().toString())
                        .family(name.getValue().toString())
                        .personalId(petstore.stream()
                                .map(c -> c.getId())
                                .findFirst().orElse(CONSTANT_NUMBER))
                        .myBuild();

                responseJson.setCompleteName(costumerInformation.getName() + "  "
                        + costumerInformation.getFamily());
                responseJson.setCostumerInformation(costumerInformation);

                CustomerAddress customerAddress = new CustomerAddress.Builder()
                        .city("stockholm")
                        .postCode("1456")
                        .country("sweden")
                        .build();

                responseJson.setCustomerAddress(customerAddress);
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

    public String changeInfo(JsonPatch jsonPatch) {
        ValidateUrl(jsonPatch.toJsonArray());
        return null;
    }

    private String ValidateUrl(JsonArray jsonList) {
        for (JsonValue jsonValue : jsonList) {
            JsonObject jsonObject = jsonValue.asJsonObject();

            if(jsonObject.entrySet().stream().filter(op -> OP.equals(op.getKey()))
                .anyMatch(opValue -> Attribute.REPLACE.toString().equals(opValue.getValue().toString()
                        .substring(1, opValue.getValue().toString().length() - 1)))){

                CheckPath(jsonObject.entrySet().stream()
                        .filter(c -> PATH.equals(c.getKey()))
                        .map(m -> m.getValue().toString().substring(1, m.getValue().toString().length()-1))
                        .collect(Collectors.joining()));

            }
            else{
                throw new IllegalArgumentException("json patch path request is not match");
            }
        }
            //System.out.println(jsonObject.getJsonArray("op"));
            return null;
    }

    private boolean CheckPath(String path) {
        List<String> pathList = Arrays.asList(TELEPHONE, COUNTRY, CITY);
        return  pathList.stream()
                .anyMatch(c->path.equals(c));
    }
}
