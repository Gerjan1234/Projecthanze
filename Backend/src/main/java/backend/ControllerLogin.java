package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.sql.SQLException;
import static backend.Database.chkInlog;

/**
 * class Controller
 * @author (Gerjan)
 * @version (09-08-2019)
 * class voor de post en getters
 */
@CrossOrigin(origins = "*")
@RestController
public class ControllerLogin {


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login", method = RequestMethod.GET)

    public ResponseEntity InlogGegevens(
            @RequestParam(name ="usr") Double usr,
            @RequestParam(name="psw") String psw) throws SQLException {
        System.out.println("Ontvangen User: " + usr);
        System.out.println("Ontvangen Wachtwoord: " + psw);
        String returndata = chkInlog(usr, psw);
        System.out.println("Ontvangen chk: " + returndata);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
        //return new ResponseEntity<>(returndata, HttpStatus.OK);
    }

    /**
     * Voorbeeld methode voor een get
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     */
//        @GetMapping("/") //site invullen
//        public ResponseEntity get(@RequestParam(name="") int aantal) {  //naam invullen voor get
//            //requestparameter als test gevuld met aantal van type int
//            try {
//                ArrayList<employees> persoon = new ArrayList<>();
//                persoon = Database.getdata(aantal);
//                HttpHeaders head = new HttpHeaders();
//                head.set("status-code", "200 Ok");
//                return ResponseEntity.ok()
//                        .headers(head)
//                        .body(persoon);
//            } catch(SQLException e) {
//                e.printStackTrace();
//                return null;
//            }
//
//        }

}