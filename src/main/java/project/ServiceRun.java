package project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = IntegrationAutoConfiguration.class)
@EnableSwagger2
public class ServiceRun {
    public static void  main(String[] args) {
        SpringApplication.run(ServiceRun.class, args); }
}
