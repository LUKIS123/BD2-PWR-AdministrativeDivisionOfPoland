package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CountyAddressDataProjection {
    Integer getId();

    Integer getVoivodeshipId();

    String getName();

    String getOfficeLocalityName();

    String getPostalCode();

    String getLocality();

    String getStreet();

    String getBuildingNumber();

    String getApartmentNumber();
}
