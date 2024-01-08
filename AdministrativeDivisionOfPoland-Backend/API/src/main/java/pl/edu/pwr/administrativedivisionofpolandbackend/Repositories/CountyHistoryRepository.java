package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CountyHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountyHistoryRepository extends JpaRepository<CountyHistory, Integer> {

    Optional<CountyHistory> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from historia_powiatow\s
            where id_pow = ?1\s
            order by nazwa_powiatu\s
            """)
    List<CountyHistory> getCountyHistoryByCountyId(Integer countyId);

    @Query(nativeQuery = true, value = """
            select * from historia_powiatow\s
            where id_woj = ?1\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyHistory> getCountyHistoryByVoivodeshipId(Integer voivodeshipId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from historia_powiatow\s
            order by nazwa_powiatu\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CountyHistory> getAllCountyHistory(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_powiatow\s
            """)
    Integer getCountyHistoryCount();

    @Query(nativeQuery = true, value = """
            select * from historia_powiatow\s
            where lower(historia_powiatow.nazwa_powiatu) like(?1)\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyHistory> searchCountyHistoryByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_powiatow\s
            where lower(historia_powiatow.nazwa_powiatu) like(?1)\s
            """)
    Integer getCountFromSearchCountyHistory(String searchPhrase);
}
