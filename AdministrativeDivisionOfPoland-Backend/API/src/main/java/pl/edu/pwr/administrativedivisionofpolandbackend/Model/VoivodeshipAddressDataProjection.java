package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface VoivodeshipAddressDataProjection {
    Integer getId();

    String getName();

    String getOfficeLocalityName();

    Boolean getSeatOfVoivode();

    Boolean getSeatOfCouncil();

    String getPostalCode();

    String getLocality();

    String getStreet();

    String getBuildingNumber();

    String getApartmentNumber();
}
