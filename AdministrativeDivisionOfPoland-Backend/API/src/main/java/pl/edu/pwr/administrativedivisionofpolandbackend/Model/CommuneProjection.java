package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

public interface CommuneProjection {
    public int getId();

    public Integer getCountyId();

    public String getCountyName();

    public String getName();

    public int getPopulation();

    public double getArea();

    public String getCommuneType();

    public String getTERYTCode();
}
