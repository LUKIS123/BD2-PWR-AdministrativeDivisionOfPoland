package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.VoivodeshipService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.VoivodeshipAddressData;
import pl.edu.pwr.contract.Dtos.VoivodeshipDto;
import pl.edu.pwr.contract.Dtos.VoivodeshipExtended;

@RestController
@RequestMapping("api/voivodeship")
@RequiredArgsConstructor
public class VoivodeshipController {
    private final VoivodeshipService voivodeshipService;

    @GetMapping("/{id}")
    public ResponseEntity<VoivodeshipDto> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(voivodeshipService.get(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<VoivodeshipDto>> getAll(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(voivodeshipService.getAll(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResult<VoivodeshipDto>> getBySearch(
            @RequestParam(value = "searchPhrase") String searchPhrase,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(voivodeshipService.searchByName(searchPhrase, page, size));
    }

    @GetMapping("extended/{id}")
    public ResponseEntity<VoivodeshipExtended> getExtendedById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(voivodeshipService.getExtended(id));
    }

    @GetMapping("extended/all")
    public ResponseEntity<PageResult<VoivodeshipExtended>> getAllExtended(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(voivodeshipService.getAllExtended(page, size));
    }

    @GetMapping("address/{id}")
    public ResponseEntity<VoivodeshipAddressData> getWithAddressDataById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(voivodeshipService.getWithAddressData(id));
    }

    @GetMapping("address/all")
    public ResponseEntity<PageResult<VoivodeshipAddressData>> getAllWithAddressDat(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(voivodeshipService.getAllWithAddressData(page, size));
    }
}
