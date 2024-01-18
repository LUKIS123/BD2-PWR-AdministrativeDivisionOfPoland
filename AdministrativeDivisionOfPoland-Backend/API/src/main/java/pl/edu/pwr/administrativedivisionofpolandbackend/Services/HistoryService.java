package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.*;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.CountyRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.History.CommuneHistoryRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.History.CountyHistoryRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.History.VoivodeshipHistoryRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.History.CommuneHistoryDto;
import pl.edu.pwr.contract.History.CountyHistoryDto;
import pl.edu.pwr.contract.History.VoivodeshipHistoryDto;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class HistoryService {
    private final CommuneHistoryRepository communeHistoryRepository;
    private final CountyHistoryRepository countyHistoryRepository;
    private final VoivodeshipHistoryRepository voivodeshipHistoryRepository;

    private final CountyRepository countyRepository;
    private final VoivodeshipRepository voivodeshipRepository;

    public PageResult<CommuneHistoryDto> getAllCommuneHistory(int page, int size) {
        List<CommuneHistory> communeHistory = communeHistoryRepository.getAllCommuneHistory(size * (page - 1), size);
        Integer communeHistoryCount = communeHistoryRepository.getCommuneHistoryCount();

        return new PageResult<>(
                communeHistory.stream().map(this::mapToCommuneHistoryDto).toList(),
                communeHistoryCount,
                size,
                page
        );
    }

    private CommuneHistoryDto mapToCommuneHistoryDto(CommuneHistory communeHistory) {
        String countyName;
        Optional<County> county = countyRepository.findById(communeHistory.getCountyId());
        if (county.isPresent()) {
            countyName = county.get().getName();
        } else {
            Optional<CountyHistory> countyHistory = countyHistoryRepository.findById(communeHistory.getCountyId());
            countyName = countyHistory.isPresent() ? countyHistory.get().getName() : "Nieznane";
        }
        return CommuneHistoryDto.builder()
                .id(communeHistory.getId())
                .countyId(communeHistory.getCountyId())
                .countyName(countyName)
                .communeId(communeHistory.getCommuneId())
                .communeName(communeHistory.getName())
                .address(communeHistory.getLegalAddress())
                .population(communeHistory.getPopulation())
                .area(communeHistory.getArea())
                .communeType(communeHistory.getCommuneType().getTypeName())
                .startDate(communeHistory.getValidityStartDate())
                .endDate(communeHistory.getValidityEndDate())
                .build();
    }

    public PageResult<CountyHistoryDto> getAllCountyHistory(int page, int size) {
        List<CountyHistory> countyHistory = countyHistoryRepository.getAllCountyHistory(size * (page - 1), size);
        Integer countyHistoryCount = countyHistoryRepository.getCountyHistoryCount();
        return new PageResult<>(
                countyHistory.stream().map(this::mapToCountyHistoryDto).toList(),
                countyHistoryCount,
                size,
                page
        );
    }

    private CountyHistoryDto mapToCountyHistoryDto(CountyHistory countyHistory) {
        String voivodeshipName;
        Optional<Voivodeship> voivodeship = voivodeshipRepository.findById(countyHistory.getVoivodeshipId());
        if (voivodeship.isPresent()) {
            voivodeshipName = voivodeship.get().getName();
        } else {
            Optional<VoivodeshipHistory> voivodeshipHistory = voivodeshipHistoryRepository.findById(countyHistory.getVoivodeshipId());
            voivodeshipName = voivodeshipHistory.isPresent() ? voivodeshipHistory.get().getName() : "Nieznane";
        }
        return CountyHistoryDto.builder()
                .id(countyHistory.getId())
                .voivodeshipId(countyHistory.getVoivodeshipId())
                .voivodeshipName(voivodeshipName)
                .countyId(countyHistory.getCountyId())
                .countyName(countyHistory.getName())
                .address(countyHistory.getLegalAddress())
                .population(countyHistory.getPopulation())
                .area(countyHistory.getAre())
                .isCityWithCountyRights(countyHistory.getIsCityWithCountyRights())
                .licensePlateDifferentiator(countyHistory.getLicensePlateDifferentiator())
                .startDate(countyHistory.getValidityStartDate())
                .endDate(countyHistory.getValidityEndDate())
                .build();
    }

    public PageResult<VoivodeshipHistoryDto> getAllVoivodeshipHistory(int page, int size) {
        List<VoivodeshipHistory> voivodeshipHistory = voivodeshipHistoryRepository.getAllVoivodeshipHistory(size * (page - 1), size);
        Integer voivodeshipHistoryCount = voivodeshipHistoryRepository.getVoivodeshipHistoryCount();
        return new PageResult<>(
                voivodeshipHistory.stream().map(this::mapToVoivodeshipHistoryDto).toList(),
                voivodeshipHistoryCount,
                size,
                page
        );
    }

    private VoivodeshipHistoryDto mapToVoivodeshipHistoryDto(VoivodeshipHistory voivodeshipHistory) {
        return VoivodeshipHistoryDto.builder()
                .id(voivodeshipHistory.getId())
                .voivodeshipId(voivodeshipHistory.getVoivodeshipId())
                .voivodeshipName(voivodeshipHistory.getName())
                .address(voivodeshipHistory.getLegalAddress())
                .population(voivodeshipHistory.getPopulation())
                .area(voivodeshipHistory.getArea())
                .licensePlateDifferentiator(voivodeshipHistory.getLicensePlateDifferentiator())
                .startDate(voivodeshipHistory.getValidityStartDate())
                .endDate(voivodeshipHistory.getValidityEndDate())
                .build();
    }

}
