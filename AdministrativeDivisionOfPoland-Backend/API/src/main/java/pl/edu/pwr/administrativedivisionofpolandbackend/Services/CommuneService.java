package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Commune;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CommuneAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.CommuneProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CommuneRepository;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.CommuneAddressData;
import pl.edu.pwr.contract.Dtos.CommuneDto;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CommuneService {
    private final CommuneRepository communeRepository;

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
                commune.getAre(),
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
                commune.getArea(),
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

}
