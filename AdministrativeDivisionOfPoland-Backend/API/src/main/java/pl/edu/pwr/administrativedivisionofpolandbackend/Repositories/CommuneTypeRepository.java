package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CommuneType;

import java.util.Optional;

@Repository
public interface CommuneTypeRepository extends JpaRepository<CommuneType, Integer> {
    Optional<CommuneType> findByTypeName(String typeName);

    Optional<CommuneType> findById(int id);
}
