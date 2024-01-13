package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.RegisteredOfficeAddresses;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisteredOfficeAddressesRepository extends JpaRepository<RegisteredOfficeAddresses, Integer> {

    Optional<RegisteredOfficeAddresses> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from adresy_siedzib\s
            order by miejscowosc\s
            offset ?1 rows\s
            fetch first ?2 row only\s
            """)
    List<RegisteredOfficeAddresses> getAll(Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from adresy_siedzib ad\s
            inner join siedziby_wojewodztw sw on ad.id_adresu_siedziby = sw.id_adresu_siedziby\s
            where sw.id_woj = ?1\s
            order by miejscowosc\s
            """)
    List<RegisteredOfficeAddresses> getRegisteredOfficeAddressByVoivodeshipId(Integer voivodeshipId);

    @Query(nativeQuery = true, value = """
            select * from adresy_siedzib ad\s
            inner join siedziby_powiatow sp on ad.id_adresu_siedziby = sp.id_adresu_siedziby\s
            where sp.id_pow = ?1\s
            order by miejscowosc\s
            """)
    List<RegisteredOfficeAddresses> getRegisteredOfficeAddressByCountyId(Integer countyId);

    @Query(nativeQuery = true, value = """
            select * from adresy_siedzib ad\s
            inner join siedziby_gmin sg on ad.id_adresu_siedziby = sg.id_adresu_siedziby\s
            where sg.id_gm = ?1\s
            order by miejscowosc\s
            """)
    List<RegisteredOfficeAddresses> getRegisteredOfficeAddressByCommuneId(Integer communeId);
}
