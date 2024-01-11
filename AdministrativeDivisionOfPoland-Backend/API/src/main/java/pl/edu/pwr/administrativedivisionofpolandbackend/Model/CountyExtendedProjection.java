package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CountyExtendedProjection {
    Integer getId();

    Integer getVoivodeshipId();

    String getVoivodeshipName();

    String getName();

    Boolean getCityWithCountyRights();

    String getLicensePlateDifferentiator();

    String getTerytCode();

    Integer getPopulation();

    Double getArea();
}
