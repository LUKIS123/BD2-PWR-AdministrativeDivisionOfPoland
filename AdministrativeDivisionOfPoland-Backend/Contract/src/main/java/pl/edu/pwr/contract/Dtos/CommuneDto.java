package pl.edu.pwr.contract.Dtos;

public class CommuneDto {
    public int id;
    public int countyId;
    public String countyName;
    public String name;
    public int population;
    public double area;
    public String communeType;
    public String TERYTCode;

    public CommuneDto(int id, int countyId, String countyName, String name, int population, double area, String communeType, String TERYTCode) {
        this.id = id;
        this.countyId = countyId;
        this.countyName = countyName;
        this.name = name;
        this.population = population;
        this.area = area;
        this.communeType = communeType;
        this.TERYTCode = TERYTCode;
    }
}
