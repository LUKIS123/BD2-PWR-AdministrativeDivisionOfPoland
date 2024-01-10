package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CountyProjection {
    public Integer getId();

    public Integer getVoivodeshipId();

    public String getVoivodeshipName();

    public String getName();

    public Boolean getCityWithCountyRights();

    public String getLicensePlateDifferentiator();

    public String getTerytCode();
}
