package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CountyRegisteredOffice;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountyRegisteredOfficeRepository extends JpaRepository<CountyRegisteredOffice, Integer> {

    Optional<CountyRegisteredOffice> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from siedziby_powiatow\s
            where id_pow = ?1\s
            order by miejscowosc_siedziby\s
            """)
    List<CountyRegisteredOffice> getCountyOfficeByCountyId(Integer countyId);

    @Query(nativeQuery = true, value = """
            select * from siedziby_powiatow\s
            order by miejscowosc_siedziby\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CountyRegisteredOffice> getAllCountyOffice(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from siedziby_powiatow\s
            """)
    Integer getCountyOfficeCount();

    @Query(nativeQuery = true, value = """
            select * from siedziby_powiatow\s
            where lower(siedziby_powiatow.miejscowosc_siedziby) like(?1)\s
            order by miejscowosc_siedziby\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CountyRegisteredOffice> searchCountyOfficeByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from siedziby_powiatow\s
            where lower(siedziby_powiatow.miejscowosc_siedziby) like(?1)\s
            """)
    Integer getCountFromSearchCountyOffice(String searchPhrase);

    List<CountyRegisteredOffice> findAllByRegisteredOfficeAddressesId(Integer registeredOfficeAddressesId);
}
