package pl.edu.pwr.administrativedivisionofpolandbackend.Serives;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.contract.Dtos.VoivodeshipDto;
import pl.edu.pwr.contract.PageResult;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class VoivodeshipService {
    private final VoivodeshipRepository voivodeshipRepository;

    public PageResult<VoivodeshipDto> getAll(int page, int size) {
        List<Voivodeship> all = voivodeshipRepository.getAll(size * (page - 1), size);
        return getCountyDtoPageResult(page, size, all);
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
        List<Voivodeship> results = voivodeshipRepository.searchByName("%" + searchPhrase + "%", size * (page - 1), size);
        return getCountyDtoPageResult(page, size, results);
    }

    private PageResult<VoivodeshipDto> getCountyDtoPageResult(int page, int size, List<Voivodeship> all) {
        List<VoivodeshipDto> voivodeshipDtos = all.stream().map(voivodeship -> new VoivodeshipDto(
                        voivodeship.getId(),
                        voivodeship.getName(),
                        voivodeship.getLicensePlateDifferentiator(),
                        voivodeship.getTERYTCode()))
                .toList();
        return new PageResult<>(voivodeshipDtos, voivodeshipDtos.size(), size, page);
    }

}
