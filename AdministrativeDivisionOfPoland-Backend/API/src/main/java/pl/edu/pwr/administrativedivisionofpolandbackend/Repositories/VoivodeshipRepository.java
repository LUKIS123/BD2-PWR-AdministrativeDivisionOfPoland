package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.VoivodeshipAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.VoivodeshipExtendedProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {
    @Query(nativeQuery = true, value = """
            select count(*) from wojewodztwo\s
            """)
    Integer getCount();

    @Query(nativeQuery = true, value = """
            select count(*) from wojewodztwo\s
            where lower(wojewodztwo.nazwa_wojewodztwa) like(?1)\s
            """)
    Integer getCountFromSearch(String searchPhrase);

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
            where lower(wojewodztwo.nazwa_wojewodztwa) like(?1)
            order by nazwa_wojewodztwa\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Voivodeship> searchByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select\s
            id_woj as id,\s
            nazwa_wojewodztwa as name,\s
            miejscowosc_siedziby as officeLocalityName,\s
            siedziba_wojewody as seatOfVoivode,\s
            siedziba_sejmiku as seatOfCouncil,\s
            kod_pocztowy as postalCode,\s
            miejscowość as locality,\s
            ulica as street,\s
            numer_budynku as buildingNumber,\s
            numer_lokalu as apartmentNumber\s
            from wojewodztwadaneadresowe\s
            where id_woj = ?1\s
            limit 1\s
            """)
    Optional<VoivodeshipAddressDataProjection> getWithAddressDataById(Integer id);

    @Query(nativeQuery = true, value = """
            select\s
            id_woj as id,\s
            nazwa_wojewodztwa as name,\s
            miejscowosc_siedziby as officeLocalityName,\s
            siedziba_wojewody as seatOfVoivode,\s
            siedziba_sejmiku as seatOfCouncil,\s
            kod_pocztowy as postalCode,\s
            miejscowość as locality,\s
            ulica as street,\s
            numer_budynku as buildingNumber,\s
            numer_lokalu as apartmentNumber\s
            from wojewodztwadaneadresowe\s
            order by nazwa_wojewodztwa\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<VoivodeshipAddressDataProjection> getAllWithAddressData(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select\s
            id_woj as id,\s
            nazwa_wojewodztwa as name,\s
            wyroznik_tab_rej as licensePlateDifferentiator,\s
            kod_teryt as terytCode,\s
            liczba_ludnosci as population,\s
            powierzchnia as area\s
            from wojewodztwaposzerzone\s
            where id_woj = ?1\s
            limit 1\s
            """)
    Optional<VoivodeshipExtendedProjection> getExtendedById(Integer id);

    @Query(nativeQuery = true, value = """
            select\s
            id_woj as id,\s
            nazwa_wojewodztwa as name,\s
            wyroznik_tab_rej as licensePlateDifferentiator,\s
            kod_teryt as terytCode,\s
            liczba_ludnosci as population,\s
            powierzchnia as area\s
            from wojewodztwaposzerzone\s
            order by nazwa_wojewodztwa\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<VoivodeshipExtendedProjection> getAllExtended(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select max(kod_teryt) from wojewodztwo\s
            where length(kod_teryt) = (select max(length(kod_teryt)) from wojewodztwo)\s
            """)
    String getMaxTerytCode();

}
