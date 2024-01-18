package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.UserEligibility;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.UserEligibilityDataProjection;

import java.util.List;

@Repository
public interface UserEligibilityRepository extends JpaRepository<UserEligibility, Integer> {
    @Query(nativeQuery = true, value = """
            select\s
            up.id_upr as id,\s
            id_woj as voivodeshipId,\s
            id_pow as countyId,\s
            ad.id_admin as userId,\s
            data_poczatkowa as validityStartingDate,\s
            data_koncowa as validityEndingDate,\s
            login as login\s
            from uprawnienia up\s
            inner join lista_uprawnien lu on up.id_upr = lu.id_upr\s
            inner join administratorzy ad on lu.id_admin = ad.id_admin\s
            where login = ?1\s
            """)
    List<UserEligibilityDataProjection> getByUserLogin(String login);
}
