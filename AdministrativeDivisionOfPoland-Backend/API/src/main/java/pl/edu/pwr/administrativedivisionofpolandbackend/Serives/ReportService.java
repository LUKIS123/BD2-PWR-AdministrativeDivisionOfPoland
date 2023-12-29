package pl.edu.pwr.administrativedivisionofpolandbackend.Serives;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Report;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.ReportRepository;
import pl.edu.pwr.contract.Dtos.ReportDto;
import pl.edu.pwr.contract.PageResult;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportDto getById(int id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found"));
        return new ReportDto(
                report.getId(),
                report.getVoivodeship().getId(),
                report.getVoivodeship().getName(),
                report.getCounty().getId(),
                report.getCounty().getName(),
                report.getCommune().getId(),
                report.getCommune().getName(),
                report.getTopic(),
                report.getContent(),
                report.getReportingDate()
        );
    }

    public PageResult<ReportDto> getAllByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<Report> results = reportRepository.findByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        return getReportDtoPageResult(page, size, results);
    }

    public PageResult<ReportDto> getAllByCountyId(int countyId, int page, int size) {
        List<Report> results = reportRepository.findByCountyId(countyId, size * (page - 1), size);
        return getReportDtoPageResult(page, size, results);
    }

    public PageResult<ReportDto> getAllByCommuneId(int voivodeshipId, int page, int size) {
        List<Report> results = reportRepository.findByCommuneId(voivodeshipId, size * (page - 1), size);
        return getReportDtoPageResult(page, size, results);
    }

    private PageResult<ReportDto> getReportDtoPageResult(int page, int size, List<Report> all) {
        List<ReportDto> reportDtos = all.stream().map(report -> new ReportDto(
                        report.getId(),
                        report.getVoivodeship().getId(),
                        report.getVoivodeship().getName(),
                        report.getCounty().getId(),
                        report.getCounty().getName(),
                        report.getCommune().getId(),
                        report.getCommune().getName(),
                        report.getTopic(),
                        report.getContent(),
                        report.getReportingDate()))
                .toList();
        return new PageResult<>(reportDtos, reportDtos.size(), size, page);
    }

}
