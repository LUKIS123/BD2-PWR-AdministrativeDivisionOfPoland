package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CommuneRegisteredOffice;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommuneRegisteredOfficeRepository extends JpaRepository<CommuneRegisteredOffice, Integer> {

    Optional<CommuneRegisteredOffice> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from siedziby_gmin\s
            where id_gm = ?1\s
            order by miejscowosc_siedziby\s
            """)
    List<CommuneRegisteredOffice> getCommuneOfficeByCommuneId(Integer communeId);

    @Query(nativeQuery = true, value = """
            select * from siedziby_gmin\s
            order by miejscowosc_siedziby\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<CommuneRegisteredOffice> getAllCommuneOffice(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from siedziby_gmin\s
            """)
    Integer getCommuneOfficeCount();

    @Query(nativeQuery = true, value = """
            select * from siedziby_gmin\s
            where lower(siedziby_gmin.miejscowosc_siedziby) like(?1)\s
            order by miejscowosc_siedziby\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<CommuneRegisteredOffice> searchCommuneOfficeByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from siedziby_gmin\s
            where lower(siedziby_gmin.miejscowosc_siedziby) like(?1)\s
            """)
    Integer getCountFromSearchCommuneOffice(String searchPhrase);

    List<CommuneRegisteredOffice> findAllByRegisteredOfficeAddressesId(Integer registeredOfficeAddressesId);
}
