package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Commune;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CommuneProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Integer> {
    Optional<Commune> findById(int id);

    @Query(nativeQuery = true, value = """
            select\s
            id_gm as id,\s
            gmina.id_pow as countyId,\s
            nazwa_powiatu as countyName,\s
            nazwa_gminy as name,\s
            liczba_ludnosci as population,\s
            powierzchnia as area,\s
            nazwa_rodzaju as communeType,\s
            gmina.kod_teryt as terytCode\s
            from gmina\s
            inner join powiat on (gmina.id_pow = powiat.id_pow)\s
            inner join rodzaj_gminy on (gmina.id_rodzaj_gminy = rodzaj_gminy.id_rodzaju_gminy)\s
            where gmina.id_pow = ?1\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneProjection> getCommunesByCountyId(Integer countyId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select  count(*) from gmina\s
            where id_pow = ?1\s
            """)
    Integer getCommunesByCountyIdCount(Integer countyId);

    @Query(nativeQuery = true, value = """
            select\s
            id_gm as id,\s
            gmina.id_pow as countyId,\s
            nazwa_powiatu as countyName,\s
            nazwa_gminy as name,\s
            liczba_ludnosci as population,\s
            powierzchnia as area,\s
            nazwa_rodzaju as communeType,\s
            gmina.kod_teryt as terytCode\s
            from gmina\s
            inner join powiat on (gmina.id_pow = powiat.id_pow)\s
            inner join rodzaj_gminy on (gmina.id_rodzaj_gminy = rodzaj_gminy.id_rodzaju_gminy)\s
            order by nazwa_gminy\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CommuneProjection> getAll(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from gmina\s
            """)
    Integer getCount();

    @Query(nativeQuery = true, value = """
            select\s
            id_gm as id,\s
            gmina.id_pow as countyId,\s
            nazwa_powiatu as countyName,\s
            nazwa_gminy as name,\s
            liczba_ludnosci as population,\s
            powierzchnia as area,\s
            nazwa_rodzaju as communeType,\s
            gmina.kod_teryt as terytCode\s
            from gmina\s
            inner join powiat on (gmina.id_pow = powiat.id_pow)\s
            inner join rodzaj_gminy on (gmina.id_rodzaj_gminy = rodzaj_gminy.id_rodzaju_gminy)\s
            where lower(gmina.nazwa_gminy) like(?1)\s
            order by nazwa_gminy\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneProjection> searchByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from gmina\s
            where lower(gmina.nazwa_gminy) like(?1)\s
            """)
    Integer getCountFromSearch(String searchPhrase);
}
