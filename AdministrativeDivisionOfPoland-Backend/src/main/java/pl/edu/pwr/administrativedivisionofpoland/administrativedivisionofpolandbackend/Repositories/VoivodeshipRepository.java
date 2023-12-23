package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities.Voivodeship;

import java.util.Optional;

@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {

    Optional<Voivodeship> findById(int id);
}
