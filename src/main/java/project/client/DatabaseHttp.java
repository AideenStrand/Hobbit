package project.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
@Service
public class DatabaseHttp {

    @Autowired
    private RestTemplate restTemplate;

    public Object getDatafromW3() throws SQLException {
        ResponseEntity<Object> objectResponseEntity = null;

        String username = "foo";
        String password = "bar";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbname", username, password);
        return objectResponseEntity.getBody();
    }

}
