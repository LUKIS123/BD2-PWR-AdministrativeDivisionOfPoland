package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Serives.ReportService;
import pl.edu.pwr.contract.Dtos.ReportDto;
import pl.edu.pwr.contract.PageResult;

@RestController
@RequestMapping("api/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/{id}")
    public ResponseEntity<ReportDto> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(reportService.getById(id));
    }

    @GetMapping("/byVoivodeship")
    public ResponseEntity<PageResult<ReportDto>> getByVoivodeship(
            @RequestParam(value = "voivodeshipId") int voivodeshipId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(reportService.getAllByVoivodeshipId(voivodeshipId, page, size));
    }

    @GetMapping("/byCounty")
    public ResponseEntity<PageResult<ReportDto>> getByCounty(
            @RequestParam(value = "countyId") int countyId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        return ResponseEntity.ok().body(reportService.getAllByCountyId(countyId, page, size));
    }

    @GetMapping("/byCommune")
    public ResponseEntity<PageResult<ReportDto>> getByCommune(
            @RequestParam(value = "communeId") int communeId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(reportService.getAllByCommuneId(communeId, page, size));
    }

}
