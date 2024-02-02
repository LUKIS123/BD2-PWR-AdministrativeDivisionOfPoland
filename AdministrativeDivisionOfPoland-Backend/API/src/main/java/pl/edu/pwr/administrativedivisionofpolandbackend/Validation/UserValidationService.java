package pl.edu.pwr.administrativedivisionofpolandbackend.Validation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.Eligibility;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.User;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.UserEligibility;
import pl.edu.pwr.administrativedivisionofpolandbackend.Exceptions.AuthorizationException;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.UserEligibilityDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.UserEligibilityModel;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User.EligibilityRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User.UserEligibilityRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserValidationService {
    private final UserEligibilityRepository userEligibilityRepository;
    private final UserRepository userRepository;
    private final EligibilityRepository eligibilityRepository;

    public boolean validateUserVoivodeshipEligibility(String login, int voivodeshipId) {
        if (login == null || login.isBlank()) return false;

        List<UserEligibilityDataProjection> byUserLogin = userEligibilityRepository.getByUserLogin(login);
        if (byUserLogin.isEmpty()) {
            log.error("User with login: {} is not eligible to make this request", login);
            return false;
        }

        List<UserEligibilityModel> eligibilityList = byUserLogin.stream()
                .map(this::getUserEligibilityModel).toList();

        return eligibilityList.stream()
                .anyMatch(eligibilityModel -> {
                    if (eligibilityModel.voivodeshipId == null) return false;
                    if (eligibilityModel.voivodeshipId == -1) return true;
                    return eligibilityModel.voivodeshipId == voivodeshipId && eligibilityModel.validityEndingDate == null;
                });
    }

    public boolean validateUserCountyEligibility(String login, int countyId, int voivodeshipId) {
        if (login == null || login.isBlank()) return false;

        List<UserEligibilityDataProjection> byUserLogin = userEligibilityRepository.getByUserLogin(login);
        if (byUserLogin.isEmpty()) {
            log.error("User with login: {} is not eligible to make this request", login);
            return false;
        }

        List<UserEligibilityModel> eligibilityList = byUserLogin.stream()
                .map(this::getUserEligibilityModel).toList();

        boolean matchByCounty = eligibilityList.stream()
                .anyMatch(eligibilityModel -> {
                    if (eligibilityModel.countyId == null) return false;
                    return eligibilityModel.countyId == countyId && eligibilityModel.validityEndingDate == null;
                });

        if (matchByCounty) return true;

        return eligibilityList.stream()
                .anyMatch(eligibilityModel -> {
                    if (eligibilityModel.voivodeshipId == null) return false;
                    if (eligibilityModel.voivodeshipId == -1) return true;
                    return eligibilityModel.voivodeshipId == voivodeshipId && eligibilityModel.validityEndingDate == null;
                });
    }

    public boolean validateUserCommuneEligibility(String login, int voivodeshipId, int countyId) {
        if (login == null || login.isBlank()) return false;

        List<UserEligibilityDataProjection> byUserLogin = userEligibilityRepository.getByUserLogin(login);
        if (byUserLogin.isEmpty()) {
            log.error("User with login: {} is not eligible to make this request", login);
            return false;
        }

        List<UserEligibilityModel> eligibilityList = byUserLogin.stream()
                .map(this::getUserEligibilityModel).toList();

        boolean matchByCounty = eligibilityList.stream()
                .anyMatch(eligibilityModel -> {
                    if (eligibilityModel.countyId == null) return false;
                    return eligibilityModel.countyId == countyId && eligibilityModel.validityEndingDate == null;
                });

        if (matchByCounty) return true;

        return eligibilityList.stream()
                .anyMatch(eligibilityModel -> {
                    if (eligibilityModel.voivodeshipId == null) return false;
                    if (eligibilityModel.voivodeshipId == -1) return true;
                    return eligibilityModel.voivodeshipId == voivodeshipId && eligibilityModel.validityEndingDate == null;
                });
    }

    @SneakyThrows
    public void addUserEligibility(String login, Integer voivodeshipId, Integer countyId) {
        if (login == null || login.isBlank()) {
            throw new AuthorizationException("Could not add eligibility");
        }

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AuthorizationException("User not found"));

        if (voivodeshipId != null) {
            Eligibility e = Eligibility.builder()
                    .voivodeshipId(voivodeshipId)
                    .countyId(null)
                    .build();
            Eligibility save = eligibilityRepository.save(e);

            UserEligibility userEligibility = UserEligibility.builder()
                    .user(user)
                    .id(save.getId())
                    .validityStartingDate(LocalDateTime.now())
                    .validityEndingDate(null)
                    .build();

            userEligibilityRepository.save(userEligibility);
        }

        if (countyId != null) {
            Eligibility e = Eligibility.builder()
                    .voivodeshipId(null)
                    .countyId(countyId)
                    .build();
            Eligibility save = eligibilityRepository.save(e);

            UserEligibility userEligibility = UserEligibility.builder()
                    .user(user)
                    .id(save.getId())
                    .validityStartingDate(LocalDateTime.now())
                    .validityEndingDate(null)
                    .build();

            userEligibilityRepository.save(userEligibility);
        }
    }

    private UserEligibilityModel getUserEligibilityModel(UserEligibilityDataProjection projection) {
        return UserEligibilityModel.builder()
                .id(projection.getId())
                .userId(projection.getUserId())
                .voivodeshipId(projection.getVoivodeshipId())
                .countyId(projection.getCountyId())
                .validityStartingDate(projection.getValidityStartingDate())
                .validityEndingDate(projection.getValidityEndingDate())
                .login(projection.getLogin())
                .build();
    }

}
