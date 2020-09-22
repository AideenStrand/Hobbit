package project.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.data.ResponseJson;
import project.service.ClientService;

import javax.validation.constraints.NotBlank;

@Slf4j
@RestController
public class ClinetController {

    @Autowired
    private ClientService galacService;

    @GetMapping(value = "/api/client/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseJson> getJsonMethod(
            @NotBlank @RequestHeader(name = "secret") String secret,
            @NotBlank @PathVariable(name = "status") String status) {
        try {
            return new ResponseEntity<>(galacService.getAip(status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
