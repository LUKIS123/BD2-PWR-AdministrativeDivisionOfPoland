package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.VoivodeshipHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoivodeshipHistoryRepository extends JpaRepository<VoivodeshipHistory, Integer> {

    Optional<VoivodeshipHistory> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from historia_wojewodztw\s
            where id_woj = ?1\s
            order by nazwa_wojewodztwa\s
            """)
    List<VoivodeshipHistory> getVoivodeshipHistoryByVoivodeshipId(Integer voivodeshipId);

    @Query(nativeQuery = true, value = """
            select * from historia_wojewodztw\s
            order by nazwa_wojewodztwa\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<VoivodeshipHistory> getAllVoivodeshipHistory(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_wojewodztw\s
            """)
    Integer getVoivodeshipHistoryCount();

    @Query(nativeQuery = true, value = """
            select * from historia_wojewodztw\s
            where lower(historia_wojewodztw.nazwa_wojewodztwa) like(?1)\s
            order by nazwa_wojewodztwa\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<VoivodeshipHistory> searchVoivodeshipHistoryByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_wojewodztw\s
            where lower(historia_wojewodztw.nazwa_wojewodztwa) like(?1)\s
            """)
    Integer getVoivodeshipFromSearchVoivodeshipHistory(String searchPhrase);
}
