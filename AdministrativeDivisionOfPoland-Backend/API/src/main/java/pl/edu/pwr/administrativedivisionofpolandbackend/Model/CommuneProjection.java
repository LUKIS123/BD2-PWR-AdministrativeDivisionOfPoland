package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CommuneProjection {
    Integer getId();

    Integer getCountyId();

    String getCountyName();

    String getName();

    Integer getPopulation();

    Double getArea();

    String getCommuneType();

    String getTERYTCode();
}
