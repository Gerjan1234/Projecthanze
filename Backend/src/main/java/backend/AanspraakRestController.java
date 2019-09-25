package backend;

import backend.aanspraak.model.Aanspraak;
import backend.aanspraak.model.Werkgever;
import backend.aanspraak.service.AanspraakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@RestController
public class AanspraakRestController {

    @Autowired
    private AanspraakService aanspraakService;

    /**
     * Methode voor ophalen data
     * * @author (Henk)
     * * @version (18-09-2019)
     *
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/aanspraken")
    public ResponseEntity<List<Aanspraak>> aanspraken() {

//        List<String> line = asList(datalist.split(","));
        Filereader object = new Filereader();
        List<Aanspraak> aanspraken = aanspraakService.getAanspraken();
        //returndata = object.checkscheider(line, 5);
//        HttpHeaders head = new HttpHeaders();
//        head.set("status-code", "202 Ok");
        return ResponseEntity.ok()
//                .headers(head)
                .body(aanspraken);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/werkgevers")
    public ResponseEntity<List<Werkgever>> werkgevers() {
        List<Werkgever> werkgevers = aanspraakService.getWerkgeversZonderPersoneel();
        return ResponseEntity.ok()
                .body(werkgevers);
    }

}
