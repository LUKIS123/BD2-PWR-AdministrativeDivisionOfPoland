package pl.edu.pwr.contract.Dtos;

public class VoivodeshipDto {
    public int id;
    public String name;
    public String licensePlateDifferentiator;
    public String TERYTCode;

    public VoivodeshipDto(int id, String name, String licensePlateDifferentiator, String TERYTCode) {
        this.id = id;
        this.name = name;
        this.licensePlateDifferentiator = licensePlateDifferentiator;
        this.TERYTCode = TERYTCode;
    }
}
