package pl.edu.pwr.administrativedivisionofpolandbackend.Services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.RegisteredOfficeAddresses;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Voivodeship;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.VoivodeshipRegisteredOffice;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.AuthorizationException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.InvalidRequestException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.VoivodeshipAddressDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.VoivodeshipExtendedProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.RegisteredOfficeAddressesRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRegisteredOfficeRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.VoivodeshipRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Validation.UserValidationService;
import pl.edu.pwr.contract.Common.PageResult;
import pl.edu.pwr.contract.Dtos.VoivodeshipAddressData;
import pl.edu.pwr.contract.Dtos.VoivodeshipDto;
import pl.edu.pwr.contract.Dtos.VoivodeshipExtended;
import pl.edu.pwr.contract.Voivodeship.VoivodeshipRequest;

import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class VoivodeshipService {
    private final VoivodeshipRepository voivodeshipRepository;
    private final VoivodeshipRegisteredOfficeRepository voivodeshipRegisteredOfficeRepository;
    private final RegisteredOfficeAddressesRepository registeredOfficeAddressesRepository;
    private final UserValidationService userValidationService;

    public PageResult<VoivodeshipDto> getAll(int page, int size) {
        List<Voivodeship> all = voivodeshipRepository.getAll(size * (page - 1), size);
        Integer count = voivodeshipRepository.getCount();
        return getCountyDtoPageResult(page, size, all, count);
    }

    public VoivodeshipDto get(int id) {
        Voivodeship voivodeship = voivodeshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));
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

    public VoivodeshipAddressData getWithAddressData(int id) {
        VoivodeshipAddressDataProjection voivodeship = voivodeshipRepository.getWithAddressDataById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));
        return new VoivodeshipAddressData(
                voivodeship.getId(),
                voivodeship.getName(),
                voivodeship.getOfficeLocalityName(),
                voivodeship.getSeatOfVoivode(),
                voivodeship.getSeatOfCouncil(),
                voivodeship.getPostalCode(),
                voivodeship.getLocality(),
                voivodeship.getStreet(),
                voivodeship.getBuildingNumber(),
                voivodeship.getApartmentNumber()
        );
    }

    public PageResult<VoivodeshipAddressData> getAllWithAddressData(int page, int size) {
        List<VoivodeshipAddressData> list = voivodeshipRepository.getAllWithAddressData(size * (page - 1), size)
                .stream()
                .map(x -> new VoivodeshipAddressData(
                        x.getId(),
                        x.getName(),
                        x.getOfficeLocalityName(),
                        x.getSeatOfVoivode(),
                        x.getSeatOfCouncil(),
                        x.getPostalCode(),
                        x.getLocality(),
                        x.getStreet(),
                        x.getBuildingNumber(),
                        x.getApartmentNumber()
                )).toList();
        Integer count = voivodeshipRepository.getCount();
        return new PageResult<>(list, count, size, page);
    }

    public VoivodeshipExtended getExtended(int id) {
        VoivodeshipExtendedProjection voivodeship = voivodeshipRepository.getExtendedById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));
        return new VoivodeshipExtended(
                voivodeship.getId(),
                voivodeship.getName(),
                voivodeship.getLicensePlateDifferentiator(),
                voivodeship.getTerytCode(),
                voivodeship.getPopulation(),
                voivodeship.getArea()
        );
    }

    public PageResult<VoivodeshipExtended> getAllExtended(int page, int size) {
        List<VoivodeshipExtended> list = voivodeshipRepository.getAllExtended(size * (page - 1), size)
                .stream()
                .map(x -> new VoivodeshipExtended(
                        x.getId(),
                        x.getName(),
                        x.getLicensePlateDifferentiator(),
                        x.getTerytCode(),
                        x.getPopulation(),
                        x.getArea()
                )).toList();
        Integer count = voivodeshipRepository.getCount();
        return new PageResult<>(list, count, size, page);
    }

    public Integer addVoivodeship(VoivodeshipRequest addVoivodeshipRequest, String login) {
        Voivodeship voivodeship = Voivodeship.builder()
                .name(addVoivodeshipRequest.name)
                .licensePlateDifferentiator(addVoivodeshipRequest.licensePlateDifferentiator)
                .TERYTCode(addVoivodeshipRequest.terytCode)
                .build();
        Voivodeship saved = voivodeshipRepository.save(voivodeship);

        // Adding first of the offices
        if (addVoivodeshipRequest.registeredOfficeAddressesIdFirst == null) {
            throw new InvalidRequestException("One office is required");
        }

        RegisteredOfficeAddresses registeredOfficeAddressFirst = registeredOfficeAddressesRepository
                .findById(addVoivodeshipRequest.registeredOfficeAddressesIdFirst)
                .orElseThrow(() -> new EntityNotFoundException("Address with id: " + addVoivodeshipRequest.registeredOfficeAddressesIdFirst + " not found"));

        VoivodeshipRegisteredOffice first = VoivodeshipRegisteredOffice.builder()
                .voivodeship(saved)
                .locality(addVoivodeshipRequest.localityFirst)
                .isSeatOfCouncil(addVoivodeshipRequest.isSeatOfCouncilFirst)
                .isSeatOfVoivode(addVoivodeshipRequest.isSeatOfVoivodeFirst)
                .registeredOfficeAddresses(registeredOfficeAddressFirst)
                .build();

        voivodeshipRegisteredOfficeRepository.save(first);

        // Adding second office if separate
        if (addVoivodeshipRequest.registeredOfficeAddressesIdSecond != null) {
            RegisteredOfficeAddresses registeredOfficeAddressSecond = registeredOfficeAddressesRepository
                    .findById(addVoivodeshipRequest.registeredOfficeAddressesIdSecond)
                    .orElseThrow(() -> new EntityNotFoundException("Address with id: " + addVoivodeshipRequest.registeredOfficeAddressesIdSecond + " not found"));

            VoivodeshipRegisteredOffice second = VoivodeshipRegisteredOffice.builder()
                    .voivodeship(saved)
                    .locality(addVoivodeshipRequest.localitySecond)
                    .isSeatOfCouncil(addVoivodeshipRequest.isSeatOfCouncilSecond)
                    .isSeatOfVoivode(addVoivodeshipRequest.isSeatOfVoivodeSecond)
                    .registeredOfficeAddresses(registeredOfficeAddressSecond)
                    .build();

            voivodeshipRegisteredOfficeRepository.save(second);
        }

        userValidationService.addUserEligibility(login, saved.getId(), null);

        return saved.getId();
    }

    @SneakyThrows
    public void updateVoivodeship(int id, VoivodeshipRequest voivodeshipRequest, String login) {
        if (!userValidationService.validateUserVoivodeshipEligibility(login, id)) {
            throw new AuthorizationException("User not eligible to make this request");
        }

        Voivodeship voivodeship = voivodeshipRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Voivodeship not found"));

        if (voivodeshipRequest.name != null && !voivodeshipRequest.name.isBlank()) {
            voivodeship.setName(voivodeshipRequest.name);
        }
        if (voivodeshipRequest.licensePlateDifferentiator != null && !voivodeshipRequest.licensePlateDifferentiator.isBlank()) {
            voivodeship.setLicensePlateDifferentiator(voivodeshipRequest.licensePlateDifferentiator);
        }
        if (voivodeshipRequest.terytCode != null && !voivodeshipRequest.terytCode.isBlank()) {
            voivodeship.setTERYTCode(voivodeshipRequest.terytCode);
        }

        voivodeshipRepository.save(voivodeship);

        if (voivodeshipRequest.registeredOfficeAddressesIdFirst == null &&
            (voivodeshipRequest.localityFirst == null || voivodeshipRequest.localityFirst.isBlank()) &&
            voivodeshipRequest.isSeatOfCouncilFirst == null &&
            voivodeshipRequest.isSeatOfVoivodeFirst == null) {
            return;
        }

        List<VoivodeshipRegisteredOffice> voivodeshipOfficeByVoivodeshipId = voivodeshipRegisteredOfficeRepository.getVoivodeshipOfficeByVoivodeshipId(id);

        if (voivodeshipOfficeByVoivodeshipId.size() > 1) {
            updateVoivodeshipBothSeats(voivodeshipRequest, voivodeshipOfficeByVoivodeshipId);
        } else {
            updateVoivodeshipSeat(voivodeshipRequest, voivodeshipOfficeByVoivodeshipId);
        }
    }

    private void updateVoivodeshipSeat(VoivodeshipRequest request, List<VoivodeshipRegisteredOffice> voivodeshipOfficeByVoivodeshipId) {
        VoivodeshipRegisteredOffice seat = voivodeshipOfficeByVoivodeshipId.stream()
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Seat not found"));
        if (request.registeredOfficeAddressesIdFirst != null) {
            RegisteredOfficeAddresses registeredOfficeAddressFirst = registeredOfficeAddressesRepository
                    .findById(request.registeredOfficeAddressesIdFirst)
                    .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdFirst + " not found"));
            seat.setRegisteredOfficeAddresses(registeredOfficeAddressFirst);
        }
        if (request.localityFirst != null) {
            seat.setLocality(request.localityFirst);
        }
        if (request.isSeatOfCouncilFirst != null) {
            seat.setSeatOfCouncil(request.isSeatOfCouncilFirst);
        }
        if (request.isSeatOfVoivodeFirst != null) {
            seat.setSeatOfVoivode(request.isSeatOfVoivodeFirst);
        }

        if (request.registeredOfficeAddressesIdSecond != null) {
            RegisteredOfficeAddresses registeredOfficeAddressSecond = registeredOfficeAddressesRepository
                    .findById(request.registeredOfficeAddressesIdSecond)
                    .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdSecond + " not found"));
            seat.setRegisteredOfficeAddresses(registeredOfficeAddressSecond);

            new VoivodeshipRegisteredOffice();
            VoivodeshipRegisteredOffice voivodeshipRegisteredOffice = VoivodeshipRegisteredOffice.builder()
                    .voivodeship(seat.getVoivodeship())
                    .registeredOfficeAddresses(registeredOfficeAddressSecond)
                    .locality(request.localitySecond)
                    .isSeatOfCouncil(request.isSeatOfCouncilSecond)
                    .isSeatOfVoivode(request.isSeatOfVoivodeSecond)
                    .build();

            voivodeshipRegisteredOfficeRepository.save(voivodeshipRegisteredOffice);
        }

        voivodeshipRegisteredOfficeRepository.save(seat);
    }

    private void updateVoivodeshipBothSeats(VoivodeshipRequest request, List<VoivodeshipRegisteredOffice> voivodeshipOfficeByVoivodeshipId) {
        VoivodeshipRegisteredOffice seatOfCouncil = voivodeshipOfficeByVoivodeshipId.stream()
                .filter(VoivodeshipRegisteredOffice::isSeatOfCouncil)
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Seat of council not found"));

        VoivodeshipRegisteredOffice seatOfVoivode = voivodeshipOfficeByVoivodeshipId.stream()
                .filter(VoivodeshipRegisteredOffice::isSeatOfVoivode)
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Seat of voivode not found"));

        // Z 2 siedzib zmiana na 1 wspolna
        if (request.isSeatOfCouncilFirst != null && request.isSeatOfCouncilFirst && request.isSeatOfVoivodeFirst != null && request.isSeatOfVoivodeFirst) {

            seatOfCouncil.setSeatOfCouncil(true);
            seatOfCouncil.setSeatOfVoivode(true);

            if (request.localityFirst != null) {
                seatOfCouncil.setLocality(request.localityFirst);
            }
            if (request.registeredOfficeAddressesIdFirst != null) {
                RegisteredOfficeAddresses registeredOfficeAddressFirst = registeredOfficeAddressesRepository
                        .findById(request.registeredOfficeAddressesIdFirst)
                        .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdFirst + " not found"));

                seatOfCouncil.setRegisteredOfficeAddresses(registeredOfficeAddressFirst);
            }
            voivodeshipRegisteredOfficeRepository.save(seatOfCouncil);
            voivodeshipRegisteredOfficeRepository.delete(seatOfVoivode);
            return;
        }

        // Pozostaja 2 siedziby
        if (request.registeredOfficeAddressesIdFirst != null) {
            if (request.isSeatOfCouncilFirst != null && request.isSeatOfCouncilFirst) {
                RegisteredOfficeAddresses registeredOfficeAddressFirst = registeredOfficeAddressesRepository
                        .findById(request.registeredOfficeAddressesIdFirst)
                        .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdFirst + " not found"));

                seatOfCouncil.setRegisteredOfficeAddresses(registeredOfficeAddressFirst);

                if (request.localityFirst != null) {
                    seatOfCouncil.setLocality(request.localityFirst);
                }

            } else if (request.isSeatOfCouncilFirst != null && request.isSeatOfVoivodeFirst) {
                RegisteredOfficeAddresses registeredOfficeAddressFirst = registeredOfficeAddressesRepository
                        .findById(request.registeredOfficeAddressesIdFirst)
                        .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdFirst + " not found"));

                seatOfVoivode.setRegisteredOfficeAddresses(registeredOfficeAddressFirst);

                if (request.localityFirst != null) {
                    seatOfVoivode.setLocality(request.localityFirst);
                }
            }
        }

        if (request.registeredOfficeAddressesIdSecond != null) {
            if (request.isSeatOfCouncilSecond != null && request.isSeatOfCouncilSecond) {
                RegisteredOfficeAddresses registeredOfficeAddressSecond = registeredOfficeAddressesRepository
                        .findById(request.registeredOfficeAddressesIdSecond)
                        .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdSecond + " not found"));

                seatOfCouncil.setRegisteredOfficeAddresses(registeredOfficeAddressSecond);

                if (request.localitySecond != null) {
                    seatOfCouncil.setLocality(request.localitySecond);
                }

            } else if (request.isSeatOfCouncilSecond != null && request.isSeatOfVoivodeSecond) {
                RegisteredOfficeAddresses registeredOfficeAddressSecond = registeredOfficeAddressesRepository
                        .findById(request.registeredOfficeAddressesIdSecond)
                        .orElseThrow(() -> new EntityNotFoundException("Address with id: " + request.registeredOfficeAddressesIdSecond + " not found"));

                seatOfVoivode.setRegisteredOfficeAddresses(registeredOfficeAddressSecond);

                if (request.localitySecond != null) {
                    seatOfVoivode.setLocality(request.localitySecond);
                }
            }
        }

        voivodeshipRegisteredOfficeRepository.save(seatOfCouncil);
        voivodeshipRegisteredOfficeRepository.save(seatOfVoivode);
    }

    @SneakyThrows
    public void deleteVoivodeship(int id, String login) {
        if (!userValidationService.validateUserVoivodeshipEligibility(login, id)) {
            throw new AuthorizationException("User not eligible to make this request");
        }

        if (!voivodeshipRepository.existsById(id)) {
            throw new EntityNotFoundException("Voivodeship with ID " + id + " not found");
        }
        voivodeshipRepository.deleteById(id);
    }

    public String getMaxTERYTCode() {
        return voivodeshipRepository.getMaxTerytCode();
    }

}
