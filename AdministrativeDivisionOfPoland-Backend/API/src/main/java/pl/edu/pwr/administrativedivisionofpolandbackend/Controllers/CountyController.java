package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.CountyService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.County.CountyRequest;
import pl.edu.pwr.contract.Dtos.CountyAddressData;
import pl.edu.pwr.contract.Dtos.CountyDto;
import pl.edu.pwr.contract.Dtos.CountyExtended;

import java.net.URI;

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
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getByVoivodeshipId(voivodeshipId, page, size));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<CountyDto>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getAll(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResult<CountyDto>> getBySearch(
            @RequestParam(value = "searchPhrase") String searchPhrase,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.searchByName(searchPhrase, page, size));
    }

    @GetMapping("extended/{id}")
    public ResponseEntity<CountyExtended> getExtendedById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(countyService.getExtended(id));
    }

    @GetMapping("extended/byVoivodeship")
    public ResponseEntity<PageResult<CountyExtended>> getExtendedByVoivodeshipId(
            @RequestParam(value = "voivodeshipId") int voivodeshipId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getExtendedByVoivodeshipId(voivodeshipId, page, size));
    }

    @GetMapping("extended/all")
    public ResponseEntity<PageResult<CountyExtended>> getAllExtended(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getAllExtended(page, size));
    }

    @GetMapping("address/{id}")
    public ResponseEntity<CountyAddressData> getWithAddressDataById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(countyService.getWithAddressData(id));
    }

    @GetMapping("address/byVoivodeship")
    public ResponseEntity<PageResult<CountyAddressData>> getWithAddressDataByVoivodeshipId(
            @RequestParam(value = "voivodeshipId") int voivodeshipId,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getWithAddressDataByVoivodeshipId(voivodeshipId, page, size));
    }

    @GetMapping("address/all")
    public ResponseEntity<PageResult<CountyAddressData>> getAllWithAddressData(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(countyService.getAllWithAddressData(page, size));
    }

    @PostMapping("/add")
    public Integer addCounty(
            @RequestBody CountyRequest countyRequest
    ) {
        return countyService.addCounty(countyRequest, getLogin());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<URI> updateCounty(
            @PathVariable(value = "id") int id,
            @RequestBody CountyRequest countyRequest
    ) {
        countyService.updateCounty(id, countyRequest, getLogin());
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/county/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCounty(
            @PathVariable(value = "id") int id
    ) {
        countyService.deleteCounty(id, getLogin());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teryt")
    public String getTERYTCodeByVoivodeshipId(
            @RequestParam(value = "voivodeshipId") int voivodeshipId) {
        return countyService.getMaxTERYTCodeByVoivodeshipId(voivodeshipId);
    }

    private String getLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username = null;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        }
        return username;
    }

}
