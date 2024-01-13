package pl.edu.pwr.contract.Voivodeship;

public class AddVoivodeshipRequest {
    public String name;
    public String licensePlateDifferentiator;
    public String TERYTCode;

    // First Office
    public String localityFirst;
    public Boolean isSeatOfVoivodeFirst;
    public Boolean isSeatOfCouncilFirst;
    public Integer registeredOfficeAddressesIdFirst;

    // Second Office
    public String localitySecond;
    public Boolean isSeatOfVoivodeSecond;
    public Boolean isSeatOfCouncilSecond;
    public Integer registeredOfficeAddressesIdSecond;
}
