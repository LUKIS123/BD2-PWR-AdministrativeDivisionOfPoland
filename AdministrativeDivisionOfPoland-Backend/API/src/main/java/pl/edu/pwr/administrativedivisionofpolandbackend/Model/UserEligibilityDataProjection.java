package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

import java.time.LocalDateTime;

public interface UserEligibilityDataProjection {
    Integer getId();

    Integer getVoivodeshipId();

    Integer getCountyId();

    Integer getUserId();

    LocalDateTime getValidityStartingDate();

    LocalDateTime getValidityEndingDate();

    String getLogin();
}
