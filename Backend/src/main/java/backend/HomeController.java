package backend;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class HomeController {

    @RequestMapping("/")
    public String getHomepage() {
        return "homepage";
    }
}