package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.VoivodeshipDto;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class VoivodeshipService {
    private final VoivodeshipRepository voivodeshipRepository;

    public PageResult<VoivodeshipDto> getAll(int page, int size) {
        List<Voivodeship> all = voivodeshipRepository.getAll(size * (page - 1), size);
        Integer count = voivodeshipRepository.getCount();
        return getCountyDtoPageResult(page, size, all, count);
    }

    public VoivodeshipDto get(int id) {
        Voivodeship voivodeship = voivodeshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Voivodeship not found"));
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

}
