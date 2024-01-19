package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.HistoryService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.History.CommuneHistoryDto;
import pl.edu.pwr.contract.History.CountyHistoryDto;
import pl.edu.pwr.contract.History.VoivodeshipHistoryDto;

@RestController
@RequestMapping("api/history")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/voivodeships")
    public ResponseEntity<PageResult<VoivodeshipHistoryDto>> getAllVoivodeshipHistory(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(historyService.getAllVoivodeshipHistory(page, size));
    }

    @GetMapping("/counties")
    public ResponseEntity<PageResult<CountyHistoryDto>> getAllCountyHistory(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(historyService.getAllCountyHistory(page, size));
    }

    @GetMapping("/communes")
    public ResponseEntity<PageResult<CommuneHistoryDto>> getAllCommuneHistory(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(historyService.getAllCommuneHistory(page, size));
    }

}
