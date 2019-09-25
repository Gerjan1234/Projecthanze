package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * class Controller
 * @author (Gerjan)
 * @version (13-09-2019)
 * class voor de post voor uploaders
 */
@CrossOrigin(origins = "*")
@RestController

public class ControllerInputs {

    /**
     * Methode voor een de file uploader
     * * @author (Gerjan)
     * * @version (09-08-2019)
     * file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity FileUpload(@RequestParam("file") MultipartFile file,
                                     @RequestParam(name = "filename") String filename,
                                     @RequestParam(name = "Scheidingsteken") int scheidingsteken) {
        System.out.println(scheidingsteken);
        Filereader object = new Filereader();
        ArrayList<responsfile> returndata = new ArrayList<>();
        returndata = object.FileUpload(file, scheidingsteken, filename);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return ResponseEntity.ok()
                .headers(head)
                .body(returndata);
    }

    /**
     * Methode voor een de file uploader data controle
     * * @author (Gerjan)
     * * @version (13-09-2019)
     * file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/checkdata", method = RequestMethod.POST)
    public ResponseEntity checkdata(@RequestBody String datalist) {
        List<String> line = asList(datalist.split(","));
        Filereader object = new Filereader();
        ArrayList<responsfile> returndata = new ArrayList<>();
        returndata = object.checkscheider(line, 5);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return ResponseEntity.ok()
                .headers(head)
                .body(returndata);
    }

    /**
     * Methode voor een de file uploader data verzenden naar database
     * * @author (Gerjan)
     * * @version (13-09-2019)
     * file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/senddata", method = RequestMethod.POST)
    public ResponseEntity senddata(@RequestBody String datalist) {
        int resultaantal = 0;
        List<String> line = asList(datalist.split(","));
        Database Database = new Database();
        ArrayList<responsfile> returndata = new ArrayList<>();
        try {
            resultaantal = Database.addsenddata(line);
        } catch (Exception e) {
            System.out.println(e);
        }
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "202 Ok");
        return ResponseEntity.ok()
                .headers(head)
                .body("202" + resultaantal);
    }



    /**
     * Methode voor een de file uploader data controle salarismutatie
     * * @author (Gerjan)
     * * @version (18-09-2019)
     * file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/salarismutatiecheck", method = RequestMethod.POST)
    public ResponseEntity salarismutatiecheck(@RequestBody String datalist) {
        List<String> line = asList(datalist.split(","));
        Filereader object = new Filereader();
        ArrayList<responsfile> returndata = new ArrayList<>();
        returndata = object.salarismutatiecheckcontrole(line, 5);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return ResponseEntity.ok()
                .headers(head)
                .body(returndata);
    }

    /**
     * Methode voor een de file uploader data verzenden naar database salarismutatie
     * * @author (Gerjan)
     * * @version (18-09-2019)
     * file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/verstuursalarismutatiecheck", method = RequestMethod.POST)
    public ResponseEntity verstuursalarismutatiecheck(@RequestBody String datalist) {
        int resultaantal = 0;
        List<String> line = asList(datalist.split(","));
        Database Database = new Database();
        ArrayList<responsfile> returndata = new ArrayList<>();
        try {
            resultaantal = Database.addsalarismutatie(line);
        } catch (Exception e) {
            System.out.println(e);
        }
        //returndata = object.checkscheider(line, 5);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "202 Ok");
        return ResponseEntity.ok()
                .headers(head)
                .body("202" + resultaantal);
    }

}



