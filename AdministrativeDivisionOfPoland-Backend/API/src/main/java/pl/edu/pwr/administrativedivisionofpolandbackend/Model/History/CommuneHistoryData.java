package pl.edu.pwr.administrativedivisionofpolandbackend.Model.History;

import java.time.LocalDateTime;

public interface CommuneHistoryData {
    Integer getId();

    Integer getCountyId();

    String getCountyName();

    Integer getCommuneId();

    String getCommuneName();

    String getAddress();

    Integer getPopulation();

    Double getArea();

    String getCommuneType();

    LocalDateTime getStartDate();

    LocalDateTime getEndDate();
}
