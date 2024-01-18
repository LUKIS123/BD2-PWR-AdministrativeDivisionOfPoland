package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.County;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CountyAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CountyExtendedProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CountyProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountyRepository extends JpaRepository<County, Integer> {
    Optional<County> findById(int id);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            powiat.id_woj as voivodeshipId,\s
            nazwa_wojewodztwa as voivodeshipName,\s
            nazwa_powiatu as name,\s
            miasto_na_pr_pow as cityWithCountyRights,\s
            powiat.wyroznik_tab_rej as licensePlateDifferentiator,\s
            powiat.kod_teryt as terytCode\s
            from powiat\s
            inner join wojewodztwo on (powiat.id_woj = wojewodztwo.id_woj)\s
            where powiat.id_woj = ?1\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyProjection> getCountiesByVoivodeshipId(Integer voivodeshipId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from powiat\s
            where id_woj = ?1\s
            """)
    Integer getCountiesByVoivodeshipIdCount(Integer voivodeshipId);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            powiat.id_woj as voivodeshipId,\s
            nazwa_wojewodztwa as voivodeshipName,\s
            nazwa_powiatu as name,\s
            miasto_na_pr_pow as cityWithCountyRights,\s
            powiat.wyroznik_tab_rej as licensePlateDifferentiator,\s
            powiat.kod_teryt as terytCode\s
            from powiat\s
            inner join wojewodztwo on (powiat.id_woj = wojewodztwo.id_woj)\s
            order by nazwa_powiatu\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CountyProjection> getAll(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from powiat\s
            """)
    Integer getCount();

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            powiat.id_woj as voivodeshipId,\s
            nazwa_wojewodztwa as voivodeshipName,\s
            nazwa_powiatu as name,\s
            miasto_na_pr_pow as cityWithCountyRights,\s
            powiat.wyroznik_tab_rej as licensePlateDifferentiator,\s
            powiat.kod_teryt as terytCode\s
            from powiat\s
            inner join wojewodztwo on (powiat.id_woj = wojewodztwo.id_woj)\s
            where lower(nazwa_powiatu) like(?1)\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyProjection> searchByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from powiat\s
            where lower(nazwa_powiatu) like(?1)\s
            """)
    Integer getCountFromSearch(String searchPhrase);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            id_woj as voivodeshipId,\s
            nazwa_powiatu as name,\s
            miejscowosc_siedziby as officeLocalityName,\s
            kod_pocztowy as postalCode,\s
            miejscowość as locality,\s
            ulica as street,\s
            numer_budynku as buildingNumber,\s
            numer_lokalu as apartmentNumber\s
            from powiatydaneadresowe\s
            where id_pow = ?1\s
            limit 1\s
            """)
    Optional<CountyAddressDataProjection> getCountyAddressDataById(Integer id);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            id_woj as voivodeshipId,\s
            nazwa_powiatu as name,\s
            miejscowosc_siedziby as officeLocalityName,\s
            kod_pocztowy as postalCode,\s
            miejscowość as locality,\s
            ulica as street,\s
            numer_budynku as buildingNumber,\s
            numer_lokalu as apartmentNumber\s
            from powiatydaneadresowe\s
            order by nazwa_powiatu\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CountyAddressDataProjection> getAllCountyAddressData(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            id_woj as voivodeshipId,\s
            nazwa_powiatu as name,\s
            miejscowosc_siedziby as officeLocalityName,\s
            kod_pocztowy as postalCode,\s
            miejscowość as locality,\s
            ulica as street,\s
            numer_budynku as buildingNumber,\s
            numer_lokalu as apartmentNumber\s
            from powiatydaneadresowe\s
            where id_woj = ?1\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyAddressDataProjection> getCountyAddressDataByVoivodeshipId(Integer voivodeshipId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            id_woj as voivodeshipId,\s
            nazwa_wojewodztwa as voivodeshipName,\s
            nazwa_powiatu as name,\s
            miasto_na_pr_pow as cityWithCountyRights,\s
            rejestracja as licensePlateDifferentiator,\s
            kod_teryt as terytCode,\s
            liczba_ludnosci as population,\s
            powierzchnia as area\s
            from powiatyposzerzone\s
            where id_pow = ?1\s
            limit 1\s
            """)
    Optional<CountyExtendedProjection> getCountyExtendedById(Integer id);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            id_woj as voivodeshipId,\s
            nazwa_wojewodztwa as voivodeshipName,\s
            nazwa_powiatu as name,\s
            miasto_na_pr_pow as cityWithCountyRights,\s
            rejestracja as licensePlateDifferentiator,\s
            kod_teryt as terytCode,\s
            liczba_ludnosci as population,\s
            powierzchnia as area\s
            from powiatyposzerzone\s
            order by nazwa_powiatu\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CountyExtendedProjection> getAllCountyExtended(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select\s
            id_pow as id,\s
            id_woj as voivodeshipId,\s
            nazwa_wojewodztwa as voivodeshipName,\s
            nazwa_powiatu as name,\s
            miasto_na_pr_pow as cityWithCountyRights,\s
            rejestracja as licensePlateDifferentiator,\s
            kod_teryt as terytCode,\s
            liczba_ludnosci as population,\s
            powierzchnia as area\s
            from powiatyposzerzone\s
            where id_woj = ?1\s
            order by nazwa_powiatu\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyExtendedProjection> getCountyExtendedByVoivodeshipId(Integer voivodeshipId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select max(kod_teryt) from powiat
            where id_woj = ?1\s
            """)
    String getMaxTerytCodeByVoivodeshipId(Integer voivodeshipId);
}
