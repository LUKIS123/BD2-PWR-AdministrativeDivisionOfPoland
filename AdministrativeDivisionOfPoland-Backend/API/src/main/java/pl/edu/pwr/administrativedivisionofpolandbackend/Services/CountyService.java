package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.County;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CountyRepository;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.CountyDto;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;

    public PageResult<CountyDto> getAll(int page, int size) {
        List<County> all = countyRepository.getAll(size * (page - 1), size);
        Integer count = countyRepository.getCount();
        return getCountyDtoPageResult(page, size, all, count);
    }

    public CountyDto get(int id) {
        County county = countyRepository.findById(id).orElseThrow(() -> new RuntimeException("County not found"));
        return mapToCountyDto(county);
    }

    public PageResult<CountyDto> getByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<County> countiesByVoivodeshipId = countyRepository.getCountiesByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        Integer countiesByVoivodeshipIdCount = countyRepository.getCountiesByVoivodeshipIdCount(voivodeshipId);
        return getCountyDtoPageResult(page, size, countiesByVoivodeshipId, countiesByVoivodeshipIdCount);
    }

    public PageResult<CountyDto> searchByName(String searchPhrase, int page, int size) {
        String phrase = "%" + searchPhrase.toLowerCase() + "%";
        List<County> results = countyRepository.searchByName(phrase, size * (page - 1), size);
        Integer countFromSearch = countyRepository.getCountFromSearch(phrase);
        return getCountyDtoPageResult(page, size, results, countFromSearch);
    }

    private PageResult<CountyDto> getCountyDtoPageResult(int page, int size, List<County> all, int count) {
        List<CountyDto> countyDtos = all.stream()
                .map(this::mapToCountyDto)
                .toList();
        return new PageResult<>(countyDtos, count, size, page);
    }

    private CountyDto mapToCountyDto(County county) {
        var voivodeshipId = county.getVoivodeship() == null ? null : county.getVoivodeship().getId();
        var voivodeshipName = county.getVoivodeship() == null ? null : county.getVoivodeship().getName();
        return new CountyDto(
                county.getId(),
                voivodeshipId,
                voivodeshipName,
                county.getName(),
                county.isCityWithCountyRights(),
                county.getLicensePlateDifferentiator(),
                county.getTERYTCode());
    }

}
