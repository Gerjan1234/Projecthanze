package backend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Controllershowdata {


    /**
     * Methode voor ophalen data
     * * @author (Henk)
     * * @version (18-09-2019)
     * file en scheidingsteken in een post.
     */
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/aanspraken", method = RequestMethod.POST)
    public ResponseEntity aanspraken(@RequestBody String datalist) {
        List<String> line = asList(datalist.split(","));
        Filereader object = new Filereader();
        ArrayList<responsfile> returndata = new ArrayList<>();
        //returndata = object.checkscheider(line, 5);
        HttpHeaders head = new HttpHeaders();
        head.set("status-code", "202 Ok");
        return ResponseEntity.ok()
                .headers(head)
                .body("202");
    }

}
