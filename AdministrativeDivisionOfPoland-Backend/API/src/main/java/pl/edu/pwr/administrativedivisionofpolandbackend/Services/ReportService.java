package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Report;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.InvalidReportRequestException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.ReportRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Validation.ReportValidator;
import pl.edu.pwr.contract.Dtos.ReportDto;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Reports.AddReportRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final ReportValidator reportValidator;
    private final VoivodeshipRepository voivodeshipRepository;
    
    public PageResult<ReportDto> getAll(int page, int size) {
        List<Report> all = reportRepository.findAll(size * (page - 1), size);
        return getReportDtoPageResult(page, size, all, (int) reportRepository.count());
    }

    public ReportDto getById(int id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Report not found"));
        return mapToReportDto(report);
    }

    public PageResult<ReportDto> getAllByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<Report> results = reportRepository.findByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer byVoivodeshipCount = reportRepository.getByVoivodeshipCount(voivodeshipId);
        return getReportDtoPageResult(page, size, results, byVoivodeshipCount);
    }

    public PageResult<ReportDto> getAllByCountyId(int countyId, int page, int size) {
        List<Report> results = reportRepository.findByCountyId(countyId, size * (page - 1), size);
        Integer byCountyCount = reportRepository.getByCountyCount(countyId);
        return getReportDtoPageResult(page, size, results, byCountyCount);
    }

    public PageResult<ReportDto> getAllByCommuneId(int communeId, int page, int size) {
        List<Report> results = reportRepository.findByCommuneId(communeId, size * (page - 1), size);
        Integer byCommuneCount = reportRepository.getByCommuneCount(communeId);
        return getReportDtoPageResult(page, size, results, byCommuneCount);
    }

    public Integer createReport(AddReportRequest addReportRequest) throws InvalidReportRequestException {
        if (!reportValidator.validateAddReportRequest(addReportRequest)) {
            throw new InvalidReportRequestException("Content and topic must not be null");
        }
        Optional<Voivodeship> byId = voivodeshipRepository.findById(addReportRequest.voivodeshipId);
        if (byId.isEmpty()) {
            throw new InvalidReportRequestException("Voivodeship does not exist");
        }
        return reportRepository.addReport(
                addReportRequest.voivodeshipId,
                addReportRequest.countyId,
                addReportRequest.communeId,
                addReportRequest.topic,
                addReportRequest.content,
                LocalDateTime.now()
        );
    }

    private PageResult<ReportDto> getReportDtoPageResult(int page, int size, List<Report> all, int count) {
        List<ReportDto> reportDtos = all.stream()
                .map(this::mapToReportDto)
                .toList();
        return new PageResult<>(reportDtos, count, size, page);
    }

    private ReportDto mapToReportDto(Report report) {
        var countyId = report.getCounty() == null ? null : report.getCounty().getId();
        var countyName = report.getCounty() == null ? null : report.getCounty().getName();
        var communeId = report.getCommune() == null ? null : report.getCommune().getId();
        var communeName = report.getCommune() == null ? null : report.getCommune().getName();
        var communeType = report.getCommune() == null ? null : report.getCommune().getCommuneType().getTypeName();
        return new ReportDto(
                report.getId(),
                report.getVoivodeship().getId(),
                report.getVoivodeship().getName(),
                countyId,
                countyName,
                communeId,
                communeName,
                communeType,
                report.getTopic(),
                report.getContent(),
                report.getReportingDate()
        );
    }

    public void deleteReport(int id) {
        if (!reportRepository.existsById(id)) {
            throw new EntityNotFoundException("Report not found");
        }

        reportRepository.deleteById(id);
    }

}
