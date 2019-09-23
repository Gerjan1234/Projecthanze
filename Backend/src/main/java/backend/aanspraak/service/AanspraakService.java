package backend.aanspraak.service;

import backend.aanspraak.model.Aanspraak;
import backend.aanspraak.repository.AanspraakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AanspraakService {

    @Autowired
    private AanspraakRepository aanspraakRepository;

    public int getAantalWerknemersPerWerkgever(String employerId) {
       return aanspraakRepository.getAantalWerknemersPerWerkgever(employerId);
    }

    public List<Aanspraak> getAanspraken() {
        return aanspraakRepository.getAanspraken();
    }
}
