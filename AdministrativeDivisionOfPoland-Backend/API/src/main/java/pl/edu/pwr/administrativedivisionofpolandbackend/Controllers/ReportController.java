package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.ReportService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.ReportDto;
import pl.edu.pwr.contract.Reports.AddReportRequest;

@RestController
@RequestMapping("api/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/all")
    public ResponseEntity<PageResult<ReportDto>> findAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(reportService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(reportService.getById(id));
    }

    @GetMapping("/byVoivodeship")
    public ResponseEntity<PageResult<ReportDto>> getByVoivodeship(
            @RequestParam(value = "voivodeshipId") int voivodeshipId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(reportService.getAllByVoivodeshipId(voivodeshipId, page, size));
    }

    @GetMapping("/byCounty")
    public ResponseEntity<PageResult<ReportDto>> getByCounty(
            @RequestParam(value = "countyId") int countyId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        return ResponseEntity.ok().body(reportService.getAllByCountyId(countyId, page, size));
    }

    @GetMapping("/byCommune")
    public ResponseEntity<PageResult<ReportDto>> getByCommune(
            @RequestParam(value = "communeId") int communeId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(reportService.getAllByCommuneId(communeId, page, size));
    }

    @SneakyThrows
    @PostMapping("/add")
    public Integer createReport(
            @RequestBody AddReportRequest addReportRequest
    ) {
        return reportService.createReport(addReportRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReport(
            @PathVariable(value = "id") int id
    ) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }

}
