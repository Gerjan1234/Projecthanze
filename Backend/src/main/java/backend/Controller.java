package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * class Controller
 * @author (Gerjan)
 * @version (09-08-2019)
 * class voor de post en getters
 */

@RestController
public class Controller {

    /**

    /**
     * Methode voor een post
     *  * @author (Gerjan)
     *  * @version (09-08-2019)

        @CrossOrigin(origins = "*")
        @PostMapping("/") //site invullen
        public ResponseEntity post(@RequestBody employers newData) {  //naam invullen voor een post
            try {
                int id = Database.putdata(newData);
                HttpHeaders head = new HttpHeaders();
                head.set("status-code", "201 Created");
                head.set("Server", "data verwerkt");
                return ResponseEntity.ok()
                        .headers(head)
                        .body(newData);
            } catch(SQLException e) {
                e.printStackTrace();
                return null;
            }

        }
    */

    /**
     * Voorbeeld methode voor een get
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     */
        @GetMapping("/") //site invullen
        public ResponseEntity get(@RequestParam(name="") int aantal) {  //naam invullen voor get
            //requestparameter als test gevuld met aantal van type int
            try {
                ArrayList<employees> persoon = new ArrayList<>();
                persoon = Database.getdata(aantal);
                HttpHeaders head = new HttpHeaders();
                head.set("status-code", "200 Ok");
                return ResponseEntity.ok()
                        .headers(head)
                        .body(persoon);
            } catch(SQLException e) {
                e.printStackTrace();
                return null;
            }

        }

    /**
     * Methode voor een de file uploader
     *  * @author (Gerjan)
     *  * @version (09-08-2019)
     *  file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity FileUpload(@RequestParam("file") MultipartFile file,
                             @RequestParam(name ="filename") String filename,
                             @RequestParam(name="Scheidingsteken") int scheidingsteken) {
        System.out.println(scheidingsteken);
        Filereader object = new Filereader();
        ArrayList<responsfile> returndata = new ArrayList<>();
        returndata = object.FileUpload(file, scheidingsteken, filename);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "200 Ok");
        return  ResponseEntity.ok()
                .headers(head)
                .body(returndata);
        //return new ResponseEntity<>(returndata, HttpStatus.OK);
    }
}