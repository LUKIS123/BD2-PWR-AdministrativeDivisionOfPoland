package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.CommuneService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Commune.CommuneRequest;
import pl.edu.pwr.contract.Dtos.CommuneAddressData;
import pl.edu.pwr.contract.Dtos.CommuneDto;

import java.net.URI;

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
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.getByCountyId(countyId, page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<CommuneDto>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.getAll(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResult<CommuneDto>> getBySearch(
            @RequestParam(value = "searchPhrase") String searchPhrase,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.searchByName(searchPhrase, page, size));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<CommuneAddressData> getWithAddressDataById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(communeService.getWithAddressData(id));
    }

    @GetMapping("/address/all")
    public ResponseEntity<PageResult<CommuneAddressData>> getAllWithAddressData(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.getAllWithAddressData(page, size));
    }

    @GetMapping("/address/byCounty")
    public ResponseEntity<PageResult<CommuneAddressData>> getWithAddressDataByCountyId(
            @RequestParam(value = "countyId") int countyId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(communeService.getWithAddressDataByCountyId(countyId, page, size));
    }

    @PostMapping("/add")
    public Integer addCommune(
            @RequestBody CommuneRequest communeRequest
    ) {
        return communeService.addCommune(communeRequest);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<URI> updateCommune(
            @PathVariable(value = "id") int id,
            @RequestBody CommuneRequest communeRequest
    ) {
        communeService.updateCounty(id, communeRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/commune/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommune(
            @PathVariable(value = "id") int id
    ) {
        communeService.deleteCommune(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teryt")
    public String getTERYTCodeByCountyId(
            @RequestParam(value = "countyId") int countyId) {
        return communeService.getMaxTERYTCodeByCountyId(countyId);
    }

}
