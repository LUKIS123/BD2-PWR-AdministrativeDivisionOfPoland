package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Report;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    Optional<Report> findById(int id);

    @Query(nativeQuery = true, value = """
            select * from zgloszenia\s
            where id_woj = ?1\s
            order by temat\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Report> findByVoivodeshipId(Integer voivodeshipId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from zgloszenia\s
            where id_pow = ?1\s
            order by temat\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Report> findByCountyId(Integer countyId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select * from zgloszenia\s
            where id_gm = ?1\s
            order by temat\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Report> findByCommuneId(Integer communeId, Integer offsetRows, Integer fetchRows);
}
