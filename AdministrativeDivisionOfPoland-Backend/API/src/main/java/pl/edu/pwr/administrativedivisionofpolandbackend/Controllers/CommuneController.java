package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.CommuneService;
import pl.edu.pwr.contract.Dtos.CommuneDto;
import pl.edu.pwr.contract.Common.PageResult;

@RestController
@RequestMapping("api/commune")
@RequiredArgsConstructor
public class CommuneController {
    private final CommuneService communeService;

    @GetMapping("/{id}")
    public ResponseEntity<CommuneDto> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(communeService.get(id));
    }

    @GetMapping("/byCounty")
    public ResponseEntity<PageResult<CommuneDto>> getByCountyId(
            @RequestParam(value = "countyId") int countyId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.getByCountyId(countyId, page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<CommuneDto>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.getAll(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResult<CommuneDto>> getBySearch(
            @RequestParam(value = "searchPhrase") String searchPhrase,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.searchByName(searchPhrase, page, size));
    }
}
