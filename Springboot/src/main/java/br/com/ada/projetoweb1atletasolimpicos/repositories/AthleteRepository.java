package br.com.ada.projetoweb1atletasolimpicos.repositories;

import br.com.ada.projetoweb1atletasolimpicos.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByNameStartingWith(String name);

    List<Athlete> findByFederation(String federation);

    List<Athlete> findByModality(String modality);
}
