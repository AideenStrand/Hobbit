package project.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.data.CustomerAddress;
import project.data.CustomerStatus;
import project.data.Person;

@Slf4j
@Controller
public class WebController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                                       String name, Model model) {

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/customer")
    public String getStatus(Model model){
        model.addAttribute("status", new CustomerStatus());
        return "StatusForm";
    }

    @PostMapping("/customer")
    public String showCustomersInfo(@ModelAttribute("status"
    )CustomerStatus status){
        return "result";
    }

    //todo ***************
    @GetMapping("/friends")
    public String friendForm(Model model) {
        model.addAttribute("personForm", new Person());
        return "friendsForm";
    }

    @PostMapping("/friends")
    public String submissionResult(@ModelAttribute("personForm") Person person) {
        return "result";
    }
}
