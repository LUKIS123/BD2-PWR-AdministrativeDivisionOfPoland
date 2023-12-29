package pl.edu.pwr.contract.Dtos;

public class CountyDto {
    public int id;
    public int voivodeshipId;
    public String voivodeshipName;
    public String name;
    public boolean isCityWithCountyRights;
    public String licensePlateDifferentiator;
    public String TERYTCode;

    public CountyDto(int id, int voivodeshipId, String voivodeshipName, String name, boolean isCityWithCountyRights, String licensePlateDifferentiator, String TERYTCode) {
        this.id = id;
        this.voivodeshipId = voivodeshipId;
        this.voivodeshipName = voivodeshipName;
        this.name = name;
        this.isCityWithCountyRights = isCityWithCountyRights;
        this.licensePlateDifferentiator = licensePlateDifferentiator;
        this.TERYTCode = TERYTCode;
    }
}
