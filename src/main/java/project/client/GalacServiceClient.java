package project.client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import project.data.Petstore;

import java.util.List;

@Service
@Slf4j
public class GalacServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${client.petstore.url}")
    private String petstoreUrl;

    private final static String STATUS = "status";

    public List<Petstore> getResponseFromDatabase(String status) {
        ResponseEntity<List<Petstore>> responseEntity;
        try {
            responseEntity = restTemplate.exchange(petstoreUrl + "?"
                            + STATUS + "=" + status,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Petstore>>() {
                    });
        } catch (RestClientException e) {
            throw new RestClientException("error happend calling endpoint");
        }
        return responseEntity.getBody();
    }
}
