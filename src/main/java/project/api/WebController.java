package project.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.data.CustomerStatus;
import project.service.ClientService;

@Slf4j
@Controller
public class WebController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/customer")
    public String getStatus(Model model) {
        model.addAttribute("status", new CustomerStatus());
        return "StatusForm";
    }

    @PostMapping("/customer")
    public String showCustomersInfo(@ModelAttribute CustomerStatus status, Model model) {
        model.addAttribute("customerList", clientService.getAip(status.getValue()));
        return "result";
    }
}
