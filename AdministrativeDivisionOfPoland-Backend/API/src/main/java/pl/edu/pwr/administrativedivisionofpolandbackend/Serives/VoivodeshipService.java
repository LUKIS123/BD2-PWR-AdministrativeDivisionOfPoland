package pl.edu.pwr.administrativedivisionofpolandbackend.Serives;

import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class VoivodeshipService {
    private final VoivodeshipRepository voivodeshipRepository;

    public Page<Voivodeship> getAll(int page, int size) {
        return voivodeshipRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    public Voivodeship get(int id) {
        return voivodeshipRepository.findById(id).orElseThrow(() -> new RuntimeException("Voivodeship not found"));
    }
}
