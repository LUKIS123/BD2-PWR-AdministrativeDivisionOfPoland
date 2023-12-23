package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
