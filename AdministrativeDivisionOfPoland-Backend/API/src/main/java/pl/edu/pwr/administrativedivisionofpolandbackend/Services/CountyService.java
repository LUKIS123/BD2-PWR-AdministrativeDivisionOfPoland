package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.County;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.CountyRegisteredOffice;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.RegisteredOfficeAddresses;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.AuthorizationException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CountyAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CountyExtendedProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CountyProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CountyRegisteredOfficeRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CountyRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.RegisteredOfficeAddressesRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Validation.UserValidationService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.County.CountyRequest;
import pl.edu.pwr.contract.Dtos.CountyAddressData;
import pl.edu.pwr.contract.Dtos.CountyDto;
import pl.edu.pwr.contract.Dtos.CountyExtended;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;
    private final VoivodeshipRepository voivodeshipRepository;
    private final CountyRegisteredOfficeRepository countyRegisteredOfficeRepository;
    private final RegisteredOfficeAddressesRepository addressesRepository;
    private final UserValidationService userValidationService;

    public PageResult<CountyDto> getAll(int page, int size) {
        List<CountyProjection> all = countyRepository.getAll(size * (page - 1), size);
        Integer count = countyRepository.getCount();
        return getCountyDtoPageResult(page, size, all, count);
    }

    public CountyDto get(int id) {
        County county = countyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("County not found"));
        return mapToCountyDto(county);
    }

    public PageResult<CountyDto> getByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<CountyProjection> countiesByVoivodeshipId = countyRepository.getCountiesByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer countiesByVoivodeshipIdCount = countyRepository.getCountiesByVoivodeshipIdCount(voivodeshipId);
        return getCountyDtoPageResult(page, size, countiesByVoivodeshipId, countiesByVoivodeshipIdCount);
    }

    public PageResult<CountyDto> searchByName(String searchPhrase, int page, int size) {
        String phrase = "%" + searchPhrase.toLowerCase() + "%";
        List<CountyProjection> results = countyRepository.searchByName(phrase, size * (page - 1), size);
        Integer countFromSearch = countyRepository.getCountFromSearch(phrase);
        return getCountyDtoPageResult(page, size, results, countFromSearch);
    }

    private CountyDto mapToCountyDto(County county) {
        var voivodeshipId = county.getVoivodeship() == null ? null : county.getVoivodeship().getId();
        var voivodeshipName = county.getVoivodeship() == null ? null : county.getVoivodeship().getName();
        return new CountyDto(
                county.getId(),
                voivodeshipId,
                voivodeshipName,
                county.getName(),
                county.getIsCityWithCountyRights(),
                county.getLicensePlateDifferentiator(),
                county.getTERYTCode());
    }

    private PageResult<CountyDto> getCountyDtoPageResult(int page, int size, List<CountyProjection> all, int count) {
        List<CountyDto> countyDtos = all.stream()
                .map(this::mapToCountyDto)
                .toList();
        return new PageResult<>(countyDtos, count, size, page);
    }

    private CountyDto mapToCountyDto(CountyProjection county) {
        return new CountyDto(
                county.getId(),
                county.getVoivodeshipId(),
                county.getVoivodeshipName(),
                county.getName(),
                county.getCityWithCountyRights(),
                county.getLicensePlateDifferentiator(),
                county.getTerytCode());
    }

    public PageResult<CountyAddressData> getAllWithAddressData(int page, int size) {
        List<CountyAddressDataProjection> allCountyAddressData = countyRepository.getAllCountyAddressData(size * (page - 1), size);
        Integer count = countyRepository.getCount();
        return getCountyWithAddressDataDtoPageResult(page, size, allCountyAddressData, count);
    }

    public CountyAddressData getWithAddressData(int id) {
        CountyAddressDataProjection countyWithAddressData = countyRepository.getCountyAddressDataById(id).orElseThrow(() -> new EntityNotFoundException("County not found"));
        return new CountyAddressData(
                countyWithAddressData.getId(),
                countyWithAddressData.getVoivodeshipId(),
                countyWithAddressData.getName(),
                countyWithAddressData.getOfficeLocalityName(),
                countyWithAddressData.getPostalCode(),
                countyWithAddressData.getLocality(),
                countyWithAddressData.getStreet(),
                countyWithAddressData.getBuildingNumber(),
                countyWithAddressData.getApartmentNumber()
        );
    }

    public PageResult<CountyAddressData> getWithAddressDataByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<CountyAddressDataProjection> countyAddressDataByVoivodeshipId = countyRepository.getCountyAddressDataByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer countiesByVoivodeshipIdCount = countyRepository.getCountiesByVoivodeshipIdCount(voivodeshipId);
        return getCountyWithAddressDataDtoPageResult(page, size, countyAddressDataByVoivodeshipId, countiesByVoivodeshipIdCount);
    }

    private PageResult<CountyAddressData> getCountyWithAddressDataDtoPageResult(int page, int size, List<CountyAddressDataProjection> all, int count) {
        List<CountyAddressData> list = all.stream()
                .map(x -> new CountyAddressData(
                        x.getId(),
                        x.getVoivodeshipId(),
                        x.getName(),
                        x.getOfficeLocalityName(),
                        x.getPostalCode(),
                        x.getLocality(),
                        x.getStreet(),
                        x.getBuildingNumber(),
                        x.getApartmentNumber()
                ))
                .toList();
        return new PageResult<>(list, count, size, page);
    }

    public PageResult<CountyExtended> getAllExtended(int page, int size) {
        List<CountyExtendedProjection> allCountyExtended = countyRepository.getAllCountyExtended(size * (page - 1), size);
        Integer count = countyRepository.getCount();
        return getCountyExtendedPageResult(page, size, allCountyExtended, count);
    }

    public CountyExtended getExtended(int id) {
        CountyExtendedProjection county = countyRepository.getCountyExtendedById(id).orElseThrow(() -> new EntityNotFoundException("County not found"));
        return new CountyExtended(
                county.getId(),
                county.getVoivodeshipId(),
                county.getVoivodeshipName(),
                county.getName(),
                county.getCityWithCountyRights(),
                county.getLicensePlateDifferentiator(),
                county.getTerytCode(),
                county.getPopulation(),
                county.getArea() == null ? null : BigDecimal.valueOf(county.getArea()).setScale(2, RoundingMode.HALF_UP).doubleValue()
        );
    }

    public PageResult<CountyExtended> getExtendedByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<CountyExtendedProjection> countyExtendedVoivodeshipId = countyRepository.getCountyExtendedByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer countiesByVoivodeshipIdCount = countyRepository.getCountiesByVoivodeshipIdCount(voivodeshipId);
        return getCountyExtendedPageResult(page, size, countyExtendedVoivodeshipId, countiesByVoivodeshipIdCount);
    }

    private PageResult<CountyExtended> getCountyExtendedPageResult(int page, int size, List<CountyExtendedProjection> all, int count) {
        List<CountyExtended> list = all.stream()
                .map(x -> new CountyExtended(
                        x.getId(),
                        x.getVoivodeshipId(),
                        x.getVoivodeshipName(),
                        x.getName(),
                        x.getCityWithCountyRights(),
                        x.getLicensePlateDifferentiator(),
                        x.getTerytCode(),
                        x.getPopulation(),
                        x.getArea() == null ? null : BigDecimal.valueOf(x.getArea()).setScale(2, RoundingMode.HALF_UP).doubleValue()
                ))
                .toList();
        return new PageResult<>(list, count, size, page);
    }

    @SneakyThrows
    public Integer addCounty(CountyRequest countyRequest, String login) {
        if (!userValidationService.validateUserVoivodeshipEligibility(login, countyRequest.voivodeshipId)) {
            throw new AuthorizationException("User is not eligible to add county in this voivodeship");
        }

        Voivodeship voivodeship = voivodeshipRepository
                .findById(countyRequest.voivodeshipId)
                .orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));

        County county = County.builder()
                .name(countyRequest.name)
                .voivodeship(voivodeship)
                .isCityWithCountyRights(countyRequest.isCityWithCountyRights)
                .licensePlateDifferentiator(countyRequest.licensePlateDifferentiator)
                .TERYTCode(countyRequest.terytCode)
                .build();
        County save = countyRepository.save(county);

        if (countyRequest.registeredOfficeAddressesId == null) {
            throw new AuthorizationException("Registered office address id is required");
        }

        RegisteredOfficeAddresses registeredOfficeAddress = addressesRepository
                .findById(countyRequest.registeredOfficeAddressesId)
                .orElseThrow(() -> new EntityNotFoundException("Address with id: " + countyRequest.registeredOfficeAddressesId + " not found"));

        CountyRegisteredOffice countyRegisteredOffice = CountyRegisteredOffice.builder()
                .county(save)
                .locality(countyRequest.locality)
                .registeredOfficeAddresses(registeredOfficeAddress)
                .build();

        countyRegisteredOfficeRepository.save(countyRegisteredOffice);

        userValidationService.addUserEligibility(login, voivodeship.getId(), save.getId());

        return save.getId();
    }

    @SneakyThrows
    public void updateCounty(int id, CountyRequest countyRequest, String login) {
        County county = countyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("County not found"));
        // Validation
        if (!userValidationService.validateUserCountyEligibility(login, id, county.getVoivodeship().getId())) {
            throw new AuthorizationException("User is not eligible to update county with id: " + id);
        }

        if (countyRequest.voivodeshipId != null) {
            Voivodeship voivodeship = voivodeshipRepository.findById(countyRequest.voivodeshipId)
                    .orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));

            county.setVoivodeship(voivodeship);
        }
        if (countyRequest.name != null && !countyRequest.name.isBlank()) {
            county.setName(countyRequest.name);
        }
        if (countyRequest.isCityWithCountyRights != null) {
            county.setIsCityWithCountyRights(countyRequest.isCityWithCountyRights);
        }
        if (countyRequest.licensePlateDifferentiator != null) {
            county.setLicensePlateDifferentiator(countyRequest.licensePlateDifferentiator);
        }
        if (countyRequest.terytCode != null && !countyRequest.terytCode.isBlank()) {
            county.setTERYTCode(countyRequest.terytCode);
        }
        countyRepository.save(county);


        if (countyRequest.registeredOfficeAddressesId == null && countyRequest.locality == null) {
            return;
        }

        CountyRegisteredOffice countyRegisteredOffice = countyRegisteredOfficeRepository
                .getCountyOfficeByCountyId(county.getId())
                .stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("County registered office not found"));

        if (countyRequest.locality != null && !countyRequest.locality.isBlank()) {
            countyRegisteredOffice.setLocality(countyRequest.locality);
        }

        if (countyRequest.registeredOfficeAddressesId != null) {
            RegisteredOfficeAddresses registeredOfficeAddress = addressesRepository
                    .findById(countyRequest.registeredOfficeAddressesId)
                    .orElseThrow(() -> new EntityNotFoundException("Address with id: " + countyRequest.registeredOfficeAddressesId + " not found"));

            countyRegisteredOffice.setRegisteredOfficeAddresses(registeredOfficeAddress);
        }

        countyRegisteredOfficeRepository.save(countyRegisteredOffice);
    }

    @SneakyThrows
    public void deleteCounty(int id, String login) {
        County county = countyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("County not found"));

        // Validation
        if (!userValidationService.validateUserCountyEligibility(login, id, county.getVoivodeship().getId())) {
            throw new AuthorizationException("User is not eligible to delete county with id: " + id);
        }

        countyRepository.deleteById(id);
    }

    public String getMaxTERYTCodeByVoivodeshipId(int voivodeshipId) {
        return countyRepository.getMaxTerytCodeByVoivodeshipId(voivodeshipId);
    }
}
