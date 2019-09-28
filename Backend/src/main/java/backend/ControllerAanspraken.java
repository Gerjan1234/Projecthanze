package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * class Controller
 * @author (Teo)
 * @version (27-09-2019)
 */

@CrossOrigin(origins = "*")
@RestController
public class ControllerAanspraken {


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/aanspraken", method = RequestMethod.GET)

    public ResponseEntity AanspraakGegevens(
            @RequestParam(name ="usr") Double usr) throws SQLException {

        System.out.println("ControllerAanspraken Ontvangen User: " + usr);
        ArrayList<aanspraak> returndata = new ArrayList();

        returndata = Database.getAanspraken(usr);

        System.out.println("ControllerAanspraken Ontvangen returndata: " + returndata);

        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
        }

}