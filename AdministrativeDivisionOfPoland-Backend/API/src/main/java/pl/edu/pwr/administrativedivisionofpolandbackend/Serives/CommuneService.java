package pl.edu.pwr.administrativedivisionofpolandbackend.Serives;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Commune;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CommuneRepository;
import pl.edu.pwr.contract.Dtos.CommuneDto;
import pl.edu.pwr.contract.PageResult;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class CommuneService {
    private final CommuneRepository communeRepository;

    public PageResult<CommuneDto> getAll(int page, int size) {
        List<Commune> all = communeRepository.getAll(size * (page - 1), size);
        return getCommuneDtoPageResult(page, size, all);
    }

    public CommuneDto get(int id) {
        Commune commune = communeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commune not found"));
        return new CommuneDto(
                commune.getId(),
                commune.getCounty().getId(),
                commune.getCounty().getName(),
                commune.getName(),
                commune.getPopulation(),
                commune.getAre(),
                commune.getCounty().getName(),
                commune.getTERYTCode()
        );
    }

    public PageResult<CommuneDto> getByCountyId(int countyId, int page, int size) {
        List<Commune> communesByCountyId = communeRepository.getCommunesByCountyId(countyId, size * (page - 1), size);
        return getCommuneDtoPageResult(page, size, communesByCountyId);
    }

    public PageResult<CommuneDto> searchByName(String searchPhrase, int page, int size) {
        List<Commune> results = communeRepository.searchByName("%" + searchPhrase + "%", size * (page - 1), size);
        return getCommuneDtoPageResult(page, size, results);
    }

    private PageResult<CommuneDto> getCommuneDtoPageResult(int page, int size, List<Commune> all) {
        List<CommuneDto> communeDtos = all.stream().map(commune -> new CommuneDto(
                        commune.getId(),
                        commune.getCounty().getId(),
                        commune.getCounty().getName(),
                        commune.getName(),
                        commune.getPopulation(),
                        commune.getAre(),
                        commune.getCounty().getName(),
                        commune.getTERYTCode()))
                .toList();
        return new PageResult<>(communeDtos, communeDtos.size(), size, page);
    }
}
