package project.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.data.ResponseJson;
import project.service.CustomerService;

import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
public class GalacController {

    @Autowired
    private CustomerService galacService;

    @GetMapping(value = "/api/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseJson> getJsonMethod(
            @NotBlank @RequestHeader(name = "secret") String secret,
            @NotBlank @PathVariable(name = "id") String id) {
        try {
            return new ResponseEntity<>(galacService.getAip(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
