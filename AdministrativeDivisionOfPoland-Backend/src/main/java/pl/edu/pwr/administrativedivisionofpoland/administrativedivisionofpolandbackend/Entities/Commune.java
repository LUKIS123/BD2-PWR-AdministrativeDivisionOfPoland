package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gmina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Commune {
    @Id
    @Column(name = "id_gm", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "id_pow")
    private int countyId;
    @Column(name = "nazwa_gminy")
    private String name;
    @Column(name = "liczba_ludnosci")
    private int population;
    @Column(name = "powierzchnia")
    private double are;
    @Column(name = "id_rodzaj_gminy")
    private int communeTypeId;
    @Column(name = "kod_teryt")
    private String TERYTCode;
}
