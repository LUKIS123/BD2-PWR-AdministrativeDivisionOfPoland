package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.RegisteredOfficeAddresses;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.EntityInUseException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CommuneRegisteredOfficeRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CountyRegisteredOfficeRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.RegisteredOfficeAddressesRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRegisteredOfficeRepository;
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
    private final VoivodeshipRegisteredOfficeRepository voivodeshipRegisteredOfficeRepository;
    private final CountyRegisteredOfficeRepository countyRegisteredOfficeRepository;
    private final CommuneRegisteredOfficeRepository communeRegisteredOfficeRepository;

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

    public void update(int id, OfficeAddressRequest officeAddressRequest) {
        RegisteredOfficeAddresses byId = officeAddressesRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        if (officeAddressRequest.postalCode != null) {
            byId.setPostalCode(officeAddressRequest.postalCode);
        }
        if (officeAddressRequest.locality != null) {
            byId.setLocality(officeAddressRequest.locality);
        }
        if (officeAddressRequest.street != null) {
            byId.setStreet(officeAddressRequest.street);
        }
        if (officeAddressRequest.numberOfBuilding != null) {
            byId.setNumberOfBuilding(officeAddressRequest.numberOfBuilding);
        }
        if (officeAddressRequest.apartmentNumber != null) {
            byId.setApartmentNumber(officeAddressRequest.apartmentNumber);
        }

        officeAddressesRepository.save(byId);
    }

    public void delete(int id) throws EntityInUseException {
        if (!officeAddressesRepository.existsById(id)) {
            throw new EntityNotFoundException("Address not found");
        }
        int voivodeshipOfficeUseCount = voivodeshipRegisteredOfficeRepository.findAllByRegisteredOfficeAddressesId(id).size();
        int countyOfficeUseCount = countyRegisteredOfficeRepository.findAllByRegisteredOfficeAddressesId(id).size();
        int communeOfficeUseCount = communeRegisteredOfficeRepository.findAllByRegisteredOfficeAddressesId(id).size();
        if (voivodeshipOfficeUseCount > 0 || countyOfficeUseCount > 0 || communeOfficeUseCount > 0) {
            throw new EntityInUseException("Address is used by some office");
        }

        officeAddressesRepository.deleteById(id);
    }

}
