package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Commune;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Integer> {
    Optional<Commune> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from gmina\s
            where id_pow = ?1\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Commune> getCommunesByCountyId(Integer countyId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select  count(*) from gmina\s
            where id_pow = ?1\s
            """)
    Integer getCommunesByCountyIdCount(Integer countyId);

    @Query(nativeQuery = true, value = """
            select * from gmina\s
            order by nazwa_gminy\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<Commune> getAll(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from gmina\s
            """)
    Integer getCount();

    @Query(nativeQuery = true, value = """
            select * from gmina\s
            where lower(gmina.nazwa_gminy) like(?1)\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Commune> searchByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from gmina\s
            where lower(gmina.nazwa_gminy) like(?1)\s
            """)
    Integer getCountFromSearch(String searchPhrase);
}
