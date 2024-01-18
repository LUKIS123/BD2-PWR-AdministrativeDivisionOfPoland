package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.VoivodeshipService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.VoivodeshipAddressData;
import pl.edu.pwr.contract.Dtos.VoivodeshipDto;
import pl.edu.pwr.contract.Dtos.VoivodeshipExtended;
import pl.edu.pwr.contract.Voivodeship.VoivodeshipRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.URI;

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

    @PostMapping("/add")
    public Integer addVoivodeship(
            @RequestBody VoivodeshipRequest addVoivodeshipRequest
    ) {
        return voivodeshipService.addVoivodeship(addVoivodeshipRequest, getLogin());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<URI> updateVoivodeship(
            @PathVariable(value = "id") int id,
            @RequestBody VoivodeshipRequest voivodeshipRequest
    ) {
        voivodeshipService.updateVoivodeship(id, voivodeshipRequest, getLogin());
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/voivodeship/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVoivodeship(
            @PathVariable(value = "id") int id
    ) {
        voivodeshipService.deleteVoivodeship(id, getLogin());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teryt")
    public String getTERYTCode() {
        return voivodeshipService.getMaxTERYTCode();
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
