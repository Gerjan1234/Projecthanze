package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class Controller {



        // YOUR CODE HERE :-)
        @CrossOrigin(origins = "*")
        @PostMapping("/") //site invullen
        public ResponseEntity post(@RequestBody Pojo newData) {  //naam invullen voor een post
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

        @GetMapping("/") //site invullen
        public ResponseEntity get(@RequestParam(name="") int aantal) {  //naam invullen voor get
            //requestparameter als test gevuld met aantal van type int
            try {
                ArrayList<Pojo> persoon = new ArrayList<>();
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


}
