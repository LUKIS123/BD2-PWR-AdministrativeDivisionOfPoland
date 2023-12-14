package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Serives.VoivodeshipService;

@RestController
@RequestMapping("api/voivodeship")
@RequiredArgsConstructor
public class VoivodeshipController {
    private final VoivodeshipService voivodeshipService;

    @GetMapping("/{id}")
    public ResponseEntity<Voivodeship> getById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(voivodeshipService.get(id));
    }


    @GetMapping
    public ResponseEntity<Page<Voivodeship>> getVoivodeships(@RequestParam(value = "page", defaultValue = "0") int page,
                                                             @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(voivodeshipService.getAll(page, size));
    }

}
