package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.CountyService;
import pl.edu.pwr.contract.Dtos.CountyDto;
import pl.edu.pwr.contract.Common.PageResult;

@RestController
@RequestMapping("api/county")
@RequiredArgsConstructor
public class CountyController {
    private final CountyService countyService;

    @GetMapping("/{id}")
    public ResponseEntity<CountyDto> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(countyService.get(id));
    }

    @GetMapping("/byVoivodeship")
    public ResponseEntity<PageResult<CountyDto>> getByVoivodeshipId(
            @RequestParam(value = "voivodeshipId") int voivodeshipId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getByVoivodeshipId(voivodeshipId, page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<CountyDto>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getAll(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResult<CountyDto>> getBySearch(
            @RequestParam(value = "searchPhrase") String searchPhrase,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.searchByName(searchPhrase, page, size));
    }

}
