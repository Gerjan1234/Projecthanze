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
public class ControllerWerknemers {


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/werknemers", method = RequestMethod.GET)

    public ResponseEntity WerknemerGegevens(
            @RequestParam(name ="usr") Double usr) throws SQLException {

        System.out.println("ControllerWerknemers Ontvangen User: " + usr);
        ArrayList<werknemer> returndata = new ArrayList();

        returndata = Database.getWerknemers(usr);

        System.out.println("ControllerWerknemers Ontvangen returndata: " + returndata);

        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
        }

}