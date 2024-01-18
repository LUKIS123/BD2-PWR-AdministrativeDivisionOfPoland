package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Eligibility;

@Repository
public interface EligibilityRepository extends JpaRepository<Eligibility, Integer> {
}
