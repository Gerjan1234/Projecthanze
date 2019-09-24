package backend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@CrossOrigin(origins = "*")
public class HomeController {

    @RequestMapping("/")
    public String getHomepage() {
        return "index";
    }
}