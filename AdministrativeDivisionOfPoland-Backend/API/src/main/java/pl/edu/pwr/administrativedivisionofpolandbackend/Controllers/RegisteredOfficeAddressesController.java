package pl.edu.pwr.administrativedivisionofpolandbackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Services.RegisteredOfficeAddressesService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.OfficeAddressDto;
import pl.edu.pwr.contract.OfficeAdres.OfficeAddressRequest;

@RestController
@RequestMapping("api/address")
@RequiredArgsConstructor
public class RegisteredOfficeAddressesController {
    private final RegisteredOfficeAddressesService officeAddressesService;

    @GetMapping("/{id}")
    public ResponseEntity<OfficeAddressDto> findById(@PathVariable(value = "id") int id) {
        return ResponseEntity.ok().body(officeAddressesService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<PageResult<OfficeAddressDto>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.ok().body(officeAddressesService.getAllAddresses(page, size));
    }

    @PostMapping("/add")
    public Integer addOfficeAddress(
            @RequestBody OfficeAddressRequest addOfficeAddressRequest
    ) {
        return officeAddressesService.addAddress(addOfficeAddressRequest);
    }

}
