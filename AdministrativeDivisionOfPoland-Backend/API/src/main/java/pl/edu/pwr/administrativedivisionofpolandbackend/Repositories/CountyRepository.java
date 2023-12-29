package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.County;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> {
    Optional<County> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from powiat\s
            where id_woj = ?1\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<County> getCountiesByVoivodeshipId(Integer voivodeshipId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from powiat\s
            order by nazwa_powiatu\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    public List<County> getAll(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from powiat\s
            where nazwa_powiatu like(?1)
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<County> searchByName(String searchPhrase, Integer offsetRows, Integer fetchRows);
}
