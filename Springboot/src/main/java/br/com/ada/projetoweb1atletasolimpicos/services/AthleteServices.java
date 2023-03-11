package br.com.ada.projetoweb1atletasolimpicos.services;
import br.com.ada.projetoweb1atletasolimpicos.model.Athlete;
import br.com.ada.projetoweb1atletasolimpicos.repositories.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AthleteServices {

    @Autowired
    private AthleteRepository athleteRepository;

    public Athlete save(Athlete newAthlete) {
        if (findAll().stream().anyMatch(athlete -> (athlete.getName().equals(newAthlete.getName())
        && ( athlete.getBirthDate().equals(newAthlete.getBirthDate()))))){
            return null;
        }
        return athleteRepository.save(newAthlete);


    }

    public List<Athlete> findAll() {
        return  athleteRepository.findAll();
    }

    public Athlete findById(Long id) {
        return athleteRepository.findById(id).orElse(null);
    }

    public List<Athlete> findByNameStartingWith(String name) {
        return athleteRepository.findByNameStartingWith(name);
    }

    public List<Athlete> findByFederation(String federation) {
        return athleteRepository.findByFederation(federation);
    }

    public List<Athlete> findByModality(String modality) {
        return athleteRepository.findByModality(modality);
    }

    public void deleteById(Long id) {
        athleteRepository.deleteById(id);
    }

    public Athlete update(Long id, Athlete athlete) {
        athlete.setId(id);
        return athleteRepository.save(athlete);
    }
}
