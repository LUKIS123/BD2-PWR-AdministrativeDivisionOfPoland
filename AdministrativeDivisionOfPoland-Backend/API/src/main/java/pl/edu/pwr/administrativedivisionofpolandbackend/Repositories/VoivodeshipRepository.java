package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {

    Optional<Voivodeship> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from wojewodztwo\s
            order by nazwa_wojewodztwa\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<Voivodeship> getAll(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from wojewodztwo\s
            where wojewodztwo.nazwa_wojewodztwa like(?1)
            order by nazwa_wojewodztwa\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Voivodeship> searchByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

}
