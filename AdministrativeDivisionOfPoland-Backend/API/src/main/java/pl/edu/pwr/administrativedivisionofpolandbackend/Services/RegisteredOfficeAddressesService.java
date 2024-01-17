package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.RegisteredOfficeAddresses;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.RegisteredOfficeAddressesRepository;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.OfficeAddressDto;
import pl.edu.pwr.contract.OfficeAdres.OfficeAddressRequest;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class RegisteredOfficeAddressesService {
    private final RegisteredOfficeAddressesRepository officeAddressesRepository;

    public OfficeAddressDto getById(int id) {
        RegisteredOfficeAddresses byId = officeAddressesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
        return new OfficeAddressDto(
                byId.getId(),
                byId.getPostalCode(),
                byId.getLocality(),
                byId.getStreet(),
                byId.getNumberOfBuilding(),
                byId.getApartmentNumber()
        );
    }

    public PageResult<OfficeAddressDto> getAllAddresses(int page, int size) {
        List<RegisteredOfficeAddresses> all = officeAddressesRepository.getAll(size * (page - 1), size);
        List<OfficeAddressDto> officeAddressDtos = all.stream()
                .map(x -> new OfficeAddressDto(
                        x.getId(),
                        x.getPostalCode(),
                        x.getLocality(),
                        x.getStreet(),
                        x.getNumberOfBuilding(),
                        x.getApartmentNumber()
                ))
                .toList();
        return new PageResult<>(officeAddressDtos, (int) officeAddressesRepository.count(), size, page);
    }

    public Integer addAddress(OfficeAddressRequest addOfficeAddressRequest) {
        RegisteredOfficeAddresses newAddress = RegisteredOfficeAddresses.builder()
                .postalCode(addOfficeAddressRequest.postalCode)
                .locality(addOfficeAddressRequest.locality)
                .street(addOfficeAddressRequest.street)
                .numberOfBuilding(addOfficeAddressRequest.numberOfBuilding)
                .apartmentNumber(addOfficeAddressRequest.apartmentNumber)
                .build();

        RegisteredOfficeAddresses saved = officeAddressesRepository.save(newAddress);
        return saved.getId();
    }

}
