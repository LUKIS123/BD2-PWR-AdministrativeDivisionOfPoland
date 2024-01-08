package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CommuneHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommuneHistoryRepository extends JpaRepository<CommuneHistory, Integer> {

    Optional<CommuneHistory> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            where id_gm = ?1\s
            order by nazwa_gminy\s
            """)
    List<CommuneHistory> getCommuneHistoryByCommuneId(Integer communeId);

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            where id_pow = ?1\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneHistory> getCommuneHistoryByCountyId(Integer countyId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            order by nazwa_gminy\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CommuneHistory> getAllCommuneHistory(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_gmin\s
            """)
    Integer getCommuneHistoryCount();

    @Query(nativeQuery = true, value = """
            select * from historia_gmin\s
            where lower(historia_gmin.nazwa_gminy) like(?1)\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneHistory> searchCommuneHistoryByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from historia_gmin\s
            where lower(historia_gmin.nazwa_gminy) like(?1)\s
            """)
    Integer getCountFromSearchCommuneHistory(String searchPhrase);
}
