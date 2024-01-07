package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Report;

import java.time.LocalDateTime;
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
            select count(*) from zgloszenia\s
            where id_woj = ?1\s
            """)
    Integer getByVoivodeshipCount(Integer voivodeshipId);

    @Query(nativeQuery = true, value = """
            select * from zgloszenia\s
            where id_pow = ?1\s
            order by temat\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Report> findByCountyId(Integer countyId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from zgloszenia\s
            where id_pow = ?1\s
            """)
    Integer getByCountyCount(Integer countyId);

    @Query(nativeQuery = true, value = """
            select * from zgloszenia\s
            where id_gm = ?1\s
            order by temat\s
            offset ?2 rows\s
            fetch first ?3 row only\s
            """)
    List<Report> findByCommuneId(Integer communeId, Integer offsetRows, Integer fetchRows);

    @Query(nativeQuery = true, value = """
            select count(*) from zgloszenia\s
            where id_gm = ?1\s
            """)
    Integer getByCommuneCount(Integer communeId);

    @Query(nativeQuery = true, value = """
            insert into zgloszenia(id_woj, id_pow, id_gm, temat, tresc, data_zgloszenia)\s
            values(?1, ?2, ?3, ?4, ?5, ?6)\s
            RETURNING id_zgl\s
            """)
    Integer insertIntoReportTable(Integer voivodeshipId, Integer countyId, Integer communeId, String topic, String content, LocalDateTime date);
}
