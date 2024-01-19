package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gmina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Commune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gm", nullable = false, unique = true, updatable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pow")
    private County county;
    @Column(name = "nazwa_gminy")
    private String name;
    @Column(name = "liczba_ludnosci")
    private Integer population;
    @Column(name = "powierzchnia")
    private Double area;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rodzaj_gminy")
    private CommuneType communeType;
    @Column(name = "kod_teryt")
    private String TERYTCode;
}
