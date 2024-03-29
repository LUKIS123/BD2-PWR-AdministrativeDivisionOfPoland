package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CountyProjection {
    Integer getId();

    Integer getVoivodeshipId();

    String getVoivodeshipName();

    String getName();

    Boolean getCityWithCountyRights();

    String getLicensePlateDifferentiator();

    String getTerytCode();
}
