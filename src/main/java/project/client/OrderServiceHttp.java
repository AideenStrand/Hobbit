package project.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import project.data.OrderRequest;

@Slf4j
@Service
public class OrderServiceHttp {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${client.order.url}")
    private String orderUrl;

    public <T> ResponseEntity<T> registerOrder(OrderRequest request, Class<T> clazz) {
        ResponseEntity<T> responseEntity;

        HttpEntity httpEntity = new HttpEntity(request);

        try {
            responseEntity = restTemplate.exchange(orderUrl,
                    HttpMethod.POST,
                    httpEntity,
                     clazz);
        }catch (RestClientException e){
            throw new RestClientException("error happend store order");
        }
        return responseEntity;
    }
}
