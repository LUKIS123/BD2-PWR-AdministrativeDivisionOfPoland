package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.County;

public interface CommuneProjection {
    public int getId();

    public County getCounty();

    public String getName();

    public int getPopulation();

    public double getArea();

    public String getCommuneType();

    public String getTERYTCode();
}
