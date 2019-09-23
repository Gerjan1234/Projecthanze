package backend.aanspraak.repository;

import backend.aanspraak.model.Aanspraak;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class AanspraakRepository {

    public List<Aanspraak> getAanspraken() {
        // FIXME SQL

        Aanspraak a = new Aanspraak("1");
        Aanspraak b = new Aanspraak("2");
        return Arrays.asList(a, b);

//        return Collections.emptyList();
    }

    public int getAantalWerknemersPerWerkgever(String employerId) {
        return -1;
    }
}
