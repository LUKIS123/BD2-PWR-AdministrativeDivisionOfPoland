package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CommuneAddressDataProjection {
    int getId();

    Integer getCountyId();

    String getName();

    String getOfficeLocalityName();

    String getPostalCode();

    String getLocality();

    String getStreet();

    String getBuildingNumber();

    String getApartmentNumber();
}
