package project.client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import project.data.Petstore;

@Service
@Slf4j
public class GalacServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public Petstore getResponseFromDatabase(String petId) {
        ResponseEntity<Petstore> responseEntity;
        try {
            responseEntity = restTemplate.exchange("https://petstore.swagger.io/v2/pet/"  + petId,
                    HttpMethod.GET,
                    null,
                    Petstore.class);
        } catch (RestClientException e) {
            throw new RestClientException("error happend calling endpoint");
        }
        return responseEntity.getBody();
    }
}
