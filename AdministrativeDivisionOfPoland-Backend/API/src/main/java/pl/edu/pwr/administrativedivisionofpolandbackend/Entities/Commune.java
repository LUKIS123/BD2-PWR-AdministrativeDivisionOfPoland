package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "id_pow")
    private County county;
    @Column(name = "nazwa_gminy")
    private String name;
    @Column(name = "liczba_ludnosci")
    private int population;
    @Column(name = "powierzchnia")
    private double are;
    @ManyToOne
    @JoinColumn(name = "id_rodzaj_gminy")
    private CommuneType communeType;
    @Column(name = "kod_teryt")
    private String TERYTCode;
}
