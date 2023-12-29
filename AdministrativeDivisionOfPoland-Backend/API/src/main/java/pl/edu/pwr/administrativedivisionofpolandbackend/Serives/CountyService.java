package pl.edu.pwr.administrativedivisionofpolandbackend.Serives;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.County;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CountyRepository;
import pl.edu.pwr.contract.Dtos.CountyDto;
import pl.edu.pwr.contract.PageResult;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CountyService {
    private final CountyRepository countyRepository;

    public PageResult<CountyDto> getAll(int page, int size) {
        List<County> all = countyRepository.getAll(size * (page - 1), size);
        return getCountyDtoPageResult(page, size, all);
    }

    public CountyDto get(int id) {
        County county = countyRepository.findById(id).orElseThrow(() -> new RuntimeException("County not found"));
        return new CountyDto(
                county.getId(),
                county.getVoivodeship().getId(),
                county.getVoivodeship().getName(),
                county.getName(),
                county.isCityWithCountyRights(),
                county.getLicensePlateDifferentiator(),
                county.getTERYTCode()
        );
    }

    public PageResult<CountyDto> getByVoivodeshipId(int voivodeshipId, int page, int size) {
        List<County> countiesByVoivodeshipId = countyRepository.getCountiesByVoivodeshipId(voivodeshipId, size * (page - 1), size);
        return getCountyDtoPageResult(page, size, countiesByVoivodeshipId);
    }

    public PageResult<CountyDto> searchByName(String searchPhrase, int page, int size) {
        List<County> results = countyRepository.searchByName("%" + searchPhrase + "%", size * (page - 1), size);
        return getCountyDtoPageResult(page, size, results);
    }

    private PageResult<CountyDto> getCountyDtoPageResult(int page, int size, List<County> all) {
        List<CountyDto> countyDtos = all.stream().map(county -> new CountyDto(
                        county.getId(),
                        county.getVoivodeship().getId(),
                        county.getVoivodeship().getName(),
                        county.getName(),
                        county.isCityWithCountyRights(),
                        county.getLicensePlateDifferentiator(),
                        county.getTERYTCode()))
                .toList();
        return new PageResult<>(countyDtos, countyDtos.size(), size, page);
    }

}
