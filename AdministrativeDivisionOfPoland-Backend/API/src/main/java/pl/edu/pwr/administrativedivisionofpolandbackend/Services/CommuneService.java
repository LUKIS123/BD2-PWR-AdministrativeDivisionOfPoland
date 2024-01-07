package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Commune;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CommuneRepository;
import pl.edu.pwr.contract.Dtos.CommuneDto;
import pl.edu.pwr.contract.Common.PageResult;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CommuneService {
    private final CommuneRepository communeRepository;

    public PageResult<CommuneDto> getAll(int page, int size) {
        List<Commune> all = communeRepository.getAll(size * (page - 1), size);
        Integer count = communeRepository.getCount();
        return getCommuneDtoPageResult(page, size, all, count);
    }

    public CommuneDto get(int id) {
        Commune commune = communeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commune not found"));
        return mapToCommuneDto(commune);
    }

    public PageResult<CommuneDto> getByCountyId(int countyId, int page, int size) {
        List<Commune> communesByCountyId = communeRepository.getCommunesByCountyId(countyId, size * (page - 1), size);
        Integer communesByCountyIdCount = communeRepository.getCommunesByCountyIdCount(countyId);
        return getCommuneDtoPageResult(page, size, communesByCountyId, communesByCountyIdCount);
    }

    public PageResult<CommuneDto> searchByName(String searchPhrase, int page, int size) {
        String phrase = "%" + searchPhrase.toLowerCase() + "%";
        List<Commune> results = communeRepository.searchByName(phrase, size * (page - 1), size);
        Integer countFromSearch = communeRepository.getCountFromSearch(phrase);
        return getCommuneDtoPageResult(page, size, results, countFromSearch);
    }

    private PageResult<CommuneDto> getCommuneDtoPageResult(int page, int size, List<Commune> all, int count) {
        List<CommuneDto> communeDtos = all.stream()
                .map(this::mapToCommuneDto)
                .toList();
        return new PageResult<>(communeDtos, count, size, page);
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
}
