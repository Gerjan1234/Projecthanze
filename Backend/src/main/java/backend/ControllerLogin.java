package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * class Controller
 * @author (Teo)
 * @version (24-09-2019)
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
        inlogResp returndata = new inlogResp();
        if (usr == null) {
           returndata.answer = "Gebruiker komt niet voor in database !!";
        } else {
            returndata.answer = Database.chkInlog(usr, psw);}
        System.out.println("Ontvangen returndata.answer: " + returndata.answer);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
        }

}