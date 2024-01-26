package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "powiat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class County {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pow", nullable = false, unique = true, updatable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_woj")
    private Voivodeship voivodeship;
    @Column(name = "nazwa_powiatu")
    private String name;
    @Column(name = "miasto_na_pr_pow")
    private Boolean isCityWithCountyRights;
    @Column(name = "wyroznik_tab_rej")
    private String licensePlateDifferentiator;
    @Column(name = "kod_teryt")
    private String TERYTCode;
}
