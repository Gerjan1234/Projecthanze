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

    /**
     * Methode geef de ingelogde gebruiker retour
     *  @author (Teo)
     *  @version (28-09-2019)
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getlogin", method = RequestMethod.GET)

    public ResponseEntity IngelogdeGebruiker() throws SQLException {

        inlogResp returndata = new inlogResp();

        returndata.user = security.IngelogdID;
        returndata.username = security.IngelogdNaam;

        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
    }

    /**
     * Methode reset de ingelogde gebruiker
     *  @author (Teo)
     *  @version (28-09-2019)
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/resetlogin", method = RequestMethod.GET)

    public ResponseEntity resetNameIngelogdAls(){

                System.out.println("doormiddel van uitlogknop voor:" + security.IngelogdID + " " + security.IngelogdNaam);
                security.IngelogdNaam = "nietingelogd";
                security.IngelogdID = 9999.99;
                System.out.println("doormiddel van uitlogknop na:" + security.IngelogdID + " " + security.IngelogdNaam);
        return  ResponseEntity.ok()

                .body("uitgevoerd");
            }


}