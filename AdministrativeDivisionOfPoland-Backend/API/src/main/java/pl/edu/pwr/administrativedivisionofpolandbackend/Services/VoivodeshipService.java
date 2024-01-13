package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.RegisteredOfficeAddresses;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.VoivodeshipRegisteredOffice;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.VoivodeshipAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.VoivodeshipExtendedProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.RegisteredOfficeAddressesRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRegisteredOfficeRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.VoivodeshipAddressData;
import pl.edu.pwr.contract.Dtos.VoivodeshipDto;
import pl.edu.pwr.contract.Dtos.VoivodeshipExtended;
import pl.edu.pwr.contract.Voivodeship.AddVoivodeshipRequest;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class VoivodeshipService {
    private final VoivodeshipRepository voivodeshipRepository;
    private final VoivodeshipRegisteredOfficeRepository voivodeshipRegisteredOfficeRepository;
    private final RegisteredOfficeAddressesRepository registeredOfficeAddressesRepository;

    public PageResult<VoivodeshipDto> getAll(int page, int size) {
        List<Voivodeship> all = voivodeshipRepository.getAll(size * (page - 1), size);
        Integer count = voivodeshipRepository.getCount();
        return getCountyDtoPageResult(page, size, all, count);
    }

    public VoivodeshipDto get(int id) {
        Voivodeship voivodeship = voivodeshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));
        return new VoivodeshipDto(
                voivodeship.getId(),
                voivodeship.getName(),
                voivodeship.getLicensePlateDifferentiator(),
                voivodeship.getTERYTCode()
        );
    }

    public PageResult<VoivodeshipDto> searchByName(String searchPhrase, int page, int size) {
        String phrase = "%" + searchPhrase.toLowerCase() + "%";
        List<Voivodeship> results = voivodeshipRepository.searchByName(phrase, size * (page - 1), size);
        Integer countFromSearch = voivodeshipRepository.getCountFromSearch(phrase);
        return getCountyDtoPageResult(page, size, results, countFromSearch);
    }

    private PageResult<VoivodeshipDto> getCountyDtoPageResult(int page, int size, List<Voivodeship> all, int count) {
        List<VoivodeshipDto> voivodeshipDtos = all.stream().map(voivodeship -> new VoivodeshipDto(
                        voivodeship.getId(),
                        voivodeship.getName(),
                        voivodeship.getLicensePlateDifferentiator(),
                        voivodeship.getTERYTCode()))
                .toList();
        return new PageResult<>(voivodeshipDtos, count, size, page);
    }

    public VoivodeshipAddressData getWithAddressData(int id) {
        VoivodeshipAddressDataProjection voivodeship = voivodeshipRepository.getWithAddressDataById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));
        return new VoivodeshipAddressData(
                voivodeship.getId(),
                voivodeship.getName(),
                voivodeship.getOfficeLocalityName(),
                voivodeship.getSeatOfVoivode(),
                voivodeship.getSeatOfCouncil(),
                voivodeship.getPostalCode(),
                voivodeship.getLocality(),
                voivodeship.getStreet(),
                voivodeship.getBuildingNumber(),
                voivodeship.getApartmentNumber()
        );
    }

    public PageResult<VoivodeshipAddressData> getAllWithAddressData(int page, int size) {
        List<VoivodeshipAddressData> list = voivodeshipRepository.getAllWithAddressData(size * (page - 1), size)
                .stream()
                .map(x -> new VoivodeshipAddressData(
                        x.getId(),
                        x.getName(),
                        x.getOfficeLocalityName(),
                        x.getSeatOfVoivode(),
                        x.getSeatOfCouncil(),
                        x.getPostalCode(),
                        x.getLocality(),
                        x.getStreet(),
                        x.getBuildingNumber(),
                        x.getApartmentNumber()
                )).toList();
        Integer count = voivodeshipRepository.getCount();
        return new PageResult<>(list, count, size, page);
    }

    public VoivodeshipExtended getExtended(int id) {
        VoivodeshipExtendedProjection voivodeship = voivodeshipRepository.getExtendedById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));
        return new VoivodeshipExtended(
                voivodeship.getId(),
                voivodeship.getName(),
                voivodeship.getLicensePlateDifferentiator(),
                voivodeship.getTerytCode(),
                voivodeship.getPopulation(),
                voivodeship.getArea()
        );
    }

    public PageResult<VoivodeshipExtended> getAllExtended(int page, int size) {
        List<VoivodeshipExtended> list = voivodeshipRepository.getAllExtended(size * (page - 1), size)
                .stream()
                .map(x -> new VoivodeshipExtended(
                        x.getId(),
                        x.getName(),
                        x.getLicensePlateDifferentiator(),
                        x.getTerytCode(),
                        x.getPopulation(),
                        x.getArea()
                )).toList();
        Integer count = voivodeshipRepository.getCount();
        return new PageResult<>(list, count, size, page);
    }

    public Integer addVoivodeship(AddVoivodeshipRequest addVoivodeshipRequest) {
        Voivodeship voivodeship = Voivodeship.builder()
                .name(addVoivodeshipRequest.name)
                .licensePlateDifferentiator(addVoivodeshipRequest.licensePlateDifferentiator)
                .TERYTCode(addVoivodeshipRequest.TERYTCode)
                .build();
        Voivodeship saved = voivodeshipRepository.save(voivodeship);

        // Adding first of the offices
        RegisteredOfficeAddresses registeredOfficeAddressFirst = registeredOfficeAddressesRepository
                .findById(addVoivodeshipRequest.registeredOfficeAddressesIdFirst)
                .orElseThrow(() -> new EntityNotFoundException("Address with id: " + addVoivodeshipRequest.registeredOfficeAddressesIdFirst + " not found"));

        VoivodeshipRegisteredOffice first = VoivodeshipRegisteredOffice.builder()
                .voivodeship(saved)
                .locality(addVoivodeshipRequest.localityFirst)
                .isSeatOfCouncil(addVoivodeshipRequest.isSeatOfCouncilFirst)
                .isSeatOfVoivode(addVoivodeshipRequest.isSeatOfVoivodeFirst)
                .registeredOfficeAddresses(registeredOfficeAddressFirst)
                .build();

        voivodeshipRegisteredOfficeRepository.save(first);

        // Adding second office if separate
        if (addVoivodeshipRequest.registeredOfficeAddressesIdSecond != null) {
            RegisteredOfficeAddresses registeredOfficeAddressSecond = registeredOfficeAddressesRepository
                    .findById(addVoivodeshipRequest.registeredOfficeAddressesIdSecond)
                    .orElseThrow(() -> new EntityNotFoundException("Address with id: " + addVoivodeshipRequest.registeredOfficeAddressesIdSecond + " not found"));

            VoivodeshipRegisteredOffice second = VoivodeshipRegisteredOffice.builder()
                    .voivodeship(saved)
                    .locality(addVoivodeshipRequest.localitySecond)
                    .isSeatOfCouncil(addVoivodeshipRequest.isSeatOfCouncilSecond)
                    .isSeatOfVoivode(addVoivodeshipRequest.isSeatOfVoivodeSecond)
                    .registeredOfficeAddresses(registeredOfficeAddressSecond)
                    .build();

            voivodeshipRegisteredOfficeRepository.save(second);
        }
        return saved.getId();
    }

}
