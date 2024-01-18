package pl.edu.pwr.administrativedivisionofpolandbackend.Validation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.UserEligibilityDataProjection;
import pl.edu.pwr.administrativedivisionofpolandbackend.Model.UserEligibilityModel;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User.UserEligibilityRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.User.UserRepository;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserValidationService {
    private final UserEligibilityRepository userEligibilityRepository;
    private final UserRepository userRepository;

    public boolean validateUser(String login) {
        List<UserEligibilityDataProjection> byUserLogin = userEligibilityRepository.getByUserLogin(login);
        if (byUserLogin.isEmpty()) {
            log.error("User with login: {} is not eligible to make this request", login);
            return false;
        }
        return true;
    }


    public boolean validateUserVoivodeshipEligibility(String login, int voivodeshipId) {
        List<UserEligibilityDataProjection> byUserLogin = userEligibilityRepository.getByUserLogin(login);
        if (byUserLogin.isEmpty()) {
            log.error("User with login: {} is not eligible to make this request", login);
            return false;
        }

        List<UserEligibilityModel> eligibilityList = byUserLogin.stream()
                .map(this::getUserEligibilityModel).toList();

        return eligibilityList.stream()
                .anyMatch(eligibilityModel -> eligibilityModel.voivodeshipId == voivodeshipId && eligibilityModel.validityEndingDate == null);
    }

    public boolean validateUserCountyEligibility(String login, int countyId) {
        List<UserEligibilityDataProjection> byUserLogin = userEligibilityRepository.getByUserLogin(login);
        if (byUserLogin.isEmpty()) {
            log.error("User with login: {} is not eligible to make this request", login);
            return false;
        }

        List<UserEligibilityModel> eligibilityList = byUserLogin.stream()
                .map(this::getUserEligibilityModel).toList();

        return eligibilityList.stream()
                .anyMatch(eligibilityModel -> eligibilityModel.countyId == countyId && eligibilityModel.validityEndingDate == null);
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
