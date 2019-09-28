package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * class Controller
 * @author (Teo)
 * @version (28-09-2019)
 */

@CrossOrigin(origins = "*")
@RestController
public class ControllerPremies {


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/premies", method = RequestMethod.GET)

    public ResponseEntity AanspraakGegevens(
            @RequestParam(name ="usr") Double usr) throws SQLException {

        System.out.println("ControllerPremies Ontvangen User: " + usr);
        ArrayList<premie> returndata = new ArrayList();

        returndata = Database.getPremies(usr);

        System.out.println("ControllerPremies Ontvangen returndata: " + returndata);

        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
        }

}