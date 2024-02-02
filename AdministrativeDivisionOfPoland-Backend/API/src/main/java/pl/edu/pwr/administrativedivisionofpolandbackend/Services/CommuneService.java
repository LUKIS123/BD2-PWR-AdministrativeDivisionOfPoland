package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.AuthorizationException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.InvalidRequestException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CommuneAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CommuneProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Validation.UserValidationService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Commune.CommuneRequest;
import pl.edu.pwr.contract.Dtos.CommuneAddressData;
import pl.edu.pwr.contract.Dtos.CommuneDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CommuneService {
    private final CommuneRepository communeRepository;
    private final CommuneRegisteredOfficeRepository communeRegisteredOfficeRepository;
    private final CommuneTypeRepository communeTypeRepository;
    private final CountyRepository countyRepository;
    private final RegisteredOfficeAddressesRepository addressesRepository;
    private final UserValidationService userValidationService;


    public PageResult<CommuneDto> getAll(int page, int size) {
        List<CommuneProjection> all = communeRepository.getAll(size * (page - 1), size);
        Integer count = communeRepository.getCount();
        return getCommuneDtoPageResultFromProjection(page, size, all, count);
    }

    public CommuneDto get(int id) {
        Commune commune = communeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Commune not found"));
        return mapToCommuneDto(commune);
    }

    public PageResult<CommuneDto> getByCountyId(int countyId, int page, int size) {
        List<CommuneProjection> communesByCountyId = communeRepository.getCommunesByCountyId(countyId, size * (page - 1), size);
        Integer communesByCountyIdCount = communeRepository.getCommunesByCountyIdCount(countyId);
        return getCommuneDtoPageResultFromProjection(page, size, communesByCountyId, communesByCountyIdCount);
    }

    public PageResult<CommuneDto> searchByName(String searchPhrase, int page, int size) {
        String phrase = "%" + searchPhrase.toLowerCase() + "%";
        List<CommuneProjection> results = communeRepository.searchByName(phrase, size * (page - 1), size);
        Integer countFromSearch = communeRepository.getCountFromSearch(phrase);
        return getCommuneDtoPageResultFromProjection(page, size, results, countFromSearch);
    }

    private CommuneDto mapToCommuneDto(Commune commune) {
        var countyId = commune.getCounty() == null ? null : commune.getCounty().getId();
        var countyName = commune.getCounty() == null ? null : commune.getCounty().getName();
        var communeType = commune.getCommuneType() == null ? null : commune.getCommuneType().getTypeName();
        return new CommuneDto(
                commune.getId(),
                countyId,
                countyName,
                commune.getName(),
                commune.getPopulation(),
                BigDecimal.valueOf(commune.getArea()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
                communeType,
                commune.getTERYTCode());
    }

    private PageResult<CommuneDto> getCommuneDtoPageResultFromProjection(int page, int size, List<CommuneProjection> list, int count) {
        List<CommuneDto> communeDtos = list.stream()
                .map(this::mapToCommuneDto)
                .toList();
        return new PageResult<>(communeDtos, count, size, page);
    }

    private CommuneDto mapToCommuneDto(CommuneProjection commune) {
        return new CommuneDto(
                commune.getId(),
                commune.getCountyId(),
                commune.getCountyName(),
                commune.getName(),
                commune.getPopulation(),
                BigDecimal.valueOf(commune.getArea()).setScale(2, RoundingMode.HALF_UP).doubleValue(),
                commune.getCommuneType(),
                commune.getTERYTCode());
    }

    public PageResult<CommuneAddressData> getAllWithAddressData(int page, int size) {
        List<CommuneAddressDataProjection> allCommuneAddressData = communeRepository.getAllCommuneAddressData(size * (page - 1), size);
        Integer count = communeRepository.getCount();
        return getCommuneAddressDataFromProjection(page, size, allCommuneAddressData, count);
    }

    public CommuneAddressData getWithAddressData(int id) {
        CommuneAddressDataProjection communeNotFound = communeRepository.getCommuneAddressDataById(id).orElseThrow(() -> new EntityNotFoundException("Commune not found"));
        return new CommuneAddressData(
                communeNotFound.getId(),
                communeNotFound.getCountyId(),
                communeNotFound.getName(),
                communeNotFound.getOfficeLocalityName(),
                communeNotFound.getPostalCode(),
                communeNotFound.getLocality(),
                communeNotFound.getStreet(),
                communeNotFound.getBuildingNumber(),
                communeNotFound.getApartmentNumber());
    }

    public PageResult<CommuneAddressData> getWithAddressDataByCountyId(int countyId, int page, int size) {
        List<CommuneAddressDataProjection> communeAddressDataByCountyId = communeRepository.getCommuneAddressDataByCountyId(countyId, size * (page - 1), size);
        Integer communesByCountyIdCount = communeRepository.getCommunesByCountyIdCount(countyId);
        return getCommuneAddressDataFromProjection(page, size, communeAddressDataByCountyId, communesByCountyIdCount);
    }

    public PageResult<CommuneAddressData> getCommuneAddressDataFromProjection(int page, int size, List<CommuneAddressDataProjection> list, int count) {
        List<CommuneAddressData> dtos = list.stream()
                .map(x -> new CommuneAddressData(
                        x.getId(),
                        x.getCountyId(),
                        x.getName(),
                        x.getOfficeLocalityName(),
                        x.getPostalCode(),
                        x.getLocality(),
                        x.getStreet(),
                        x.getBuildingNumber(),
                        x.getApartmentNumber()))
                .toList();
        return new PageResult<>(dtos, count, size, page);
    }

    @SneakyThrows
    public Integer addCommune(CommuneRequest communeRequest, String login) {
        County superiorUnit = countyRepository
                .findById(communeRequest.countyId)
                .orElseThrow(() -> new EntityNotFoundException("County not found"));
        // Validation
        if (!userValidationService.validateUserCountyEligibility(login, communeRequest.countyId, superiorUnit.getVoivodeship().getId())) {
            throw new AuthorizationException("User is not eligible to add commune");
        }

        CommuneType communeType = communeTypeRepository
                .findById(communeRequest.communeTypeId)
                .orElseThrow(() -> new EntityNotFoundException("Commune Type not found"));

        Commune commune = Commune.builder()
                .county(superiorUnit)
                .name(communeRequest.name)
                .population(communeRequest.population)
                .area(communeRequest.area)
                .communeType(communeType)
                .TERYTCode(communeRequest.terytCode)
                .build();
        Commune save = communeRepository.save(commune);

        if (communeRequest.registeredOfficeAddressesId == null) {
            throw new InvalidRequestException("Registered office address id is required");
        }

        RegisteredOfficeAddresses registeredOfficeAddress = addressesRepository
                .findById(communeRequest.registeredOfficeAddressesId)
                .orElseThrow(() -> new EntityNotFoundException("Address with id: " + communeRequest.registeredOfficeAddressesId + " not found"));

        CommuneRegisteredOffice communeRegisteredOffice = CommuneRegisteredOffice.builder()
                .commune(save)
                .locality(communeRequest.locality)
                .registeredOfficeAddresses(registeredOfficeAddress)
                .build();

        communeRegisteredOfficeRepository.save(communeRegisteredOffice);

        return save.getId();
    }

    @SneakyThrows
    public void updateCommune(int id, CommuneRequest communeRequest, String login) {
        County superiorUnit = countyRepository
                .findById(communeRequest.countyId)
                .orElseThrow(() -> new EntityNotFoundException("County not found"));
        // Validation
        if (!userValidationService.validateUserCountyEligibility(login, communeRequest.countyId, superiorUnit.getVoivodeship().getId())) {
            throw new AuthorizationException("User is not eligible to update commune");
        }

        Commune commune = communeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commune not found"));

        if (communeRequest.countyId != null) {
            County county = countyRepository
                    .findById(communeRequest.countyId)
                    .orElseThrow(() -> new EntityNotFoundException("County not found"));

            commune.setCounty(county);
        }
        if (communeRequest.name != null && !communeRequest.name.isBlank()) {
            commune.setName(communeRequest.name);
        }
        if (communeRequest.population != null) {
            commune.setPopulation(communeRequest.population);
        }
        if (communeRequest.area != null) {
            commune.setArea(communeRequest.area);
        }
        if (communeRequest.communeTypeId != null) {
            CommuneType communeType = communeTypeRepository
                    .findById(communeRequest.communeTypeId)
                    .orElseThrow(() -> new EntityNotFoundException("Commune Type not found"));

            commune.setCommuneType(communeType);
        }
        if (communeRequest.terytCode != null && !communeRequest.terytCode.isBlank()) {
            commune.setTERYTCode(communeRequest.terytCode);
        }

        communeRepository.save(commune);

        if (communeRequest.registeredOfficeAddressesId == null && communeRequest.locality == null) {
            return;
        }

        CommuneRegisteredOffice communeRegisteredOffice = communeRegisteredOfficeRepository
                .getCommuneOfficeByCommuneId(id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Commune registered office not found"));

        if (communeRequest.locality != null && !communeRequest.locality.isBlank()) {
            communeRegisteredOffice.setLocality(communeRequest.locality);
        }

        if (communeRequest.registeredOfficeAddressesId != null) {
            RegisteredOfficeAddresses registeredOfficeAddress = addressesRepository
                    .findById(communeRequest.registeredOfficeAddressesId)
                    .orElseThrow(() -> new EntityNotFoundException("Address with id: " + communeRequest.registeredOfficeAddressesId + " not found"));

            communeRegisteredOffice.setRegisteredOfficeAddresses(registeredOfficeAddress);
        }

        communeRegisteredOfficeRepository.save(communeRegisteredOffice);
    }

    @SneakyThrows
    public void deleteCommune(int id, String login) {
        // Validation
        Commune commune = communeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Commune not found"));
        County superiorUnit = commune.getCounty();
        if (superiorUnit == null) {
            throw new EntityNotFoundException("County not found");
        }
        if (!userValidationService.validateUserCountyEligibility(login, superiorUnit.getId(), superiorUnit.getVoivodeship().getId())) {
            throw new AuthorizationException("User is not eligible to delete commune");
        }

        communeRepository.deleteById(id);
    }

    public String getMaxTERYTCodeByCountyId(int countyId) {
        return communeRepository.getMaxTerytCodeByCountyId(countyId);
    }

    public PageResult<CommuneAddressData> getWithAddressDataByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<CommuneAddressDataProjection> allCommuneAddressDataByVoivodeshipId = communeRepository.getAllCommuneAddressDataByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer count = communeRepository.getCommuneAddressDataCountByVoivodeshipId(voivodeshipId);
        return getCommuneAddressDataFromProjection(page, size, allCommuneAddressDataByVoivodeshipId, count);
    }

    public PageResult<CommuneDto> getByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<CommuneProjection> allByVoivodeshipId = communeRepository.getAllByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer count = communeRepository.getCountByVoivodeshipId(voivodeshipId);
        return getCommuneDtoPageResultFromProjection(page, size, allByVoivodeshipId, count);
    }

}
