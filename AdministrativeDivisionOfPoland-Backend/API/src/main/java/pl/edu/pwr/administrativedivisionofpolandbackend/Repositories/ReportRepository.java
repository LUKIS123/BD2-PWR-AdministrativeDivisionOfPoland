package pl.edu.pwr.administrativedivisionofpolandbackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Report;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    Optional<Report> findById(int id);

    List<Report> findByVoivodeshipId(int voivodeshipId);

    List<Report> findByCountyId(int countyId);

    List<Report> findByCommuneId(int communeId);
}
