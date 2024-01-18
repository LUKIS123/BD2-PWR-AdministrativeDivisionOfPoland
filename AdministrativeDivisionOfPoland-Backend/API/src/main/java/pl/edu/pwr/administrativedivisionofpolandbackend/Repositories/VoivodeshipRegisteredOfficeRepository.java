package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.VoivodeshipRegisteredOffice;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoivodeshipRegisteredOfficeRepository extends JpaRepository<VoivodeshipRegisteredOffice, Integer> {

    Optional<VoivodeshipRegisteredOffice> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from siedziby_wojewodztw\s
            where id_woj = ?1\s
            order by miejscowosc_siedziby\s
            """)
    List<VoivodeshipRegisteredOffice> getVoivodeshipOfficeByVoivodeshipId(Integer VoivodeshipId);

    @Query(nativeQuery = true, value = """
            select * from siedziby_wojewodztw\s
            order by miejscowosc_siedziby\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<VoivodeshipRegisteredOffice> getAllVoivodeshipOffice(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from siedziby_wojewodztw\s
            """)
    Integer getVoivodeshipOfficeCount();

    @Query(nativeQuery = true, value = """
            select * from siedziby_wojewodztw\s
            where lower(siedziby_wojewodztw.miejscowosc_siedziby) like(?1)\s
            order by miejscowosc_siedziby\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<VoivodeshipRegisteredOffice> searchVoivodeshipOfficeByName(String searchPhrase, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from siedziby_wojewodztw\s
            where lower(siedziby_wojewodztw.miejscowosc_siedziby) like(?1)\s
            """)
    Integer getCountFromSearchVoivodeshipOffice(String searchPhrase);

    List<VoivodeshipRegisteredOffice> findAllByRegisteredOfficeAddressesId(Integer registeredOfficeAddressesId);
}
