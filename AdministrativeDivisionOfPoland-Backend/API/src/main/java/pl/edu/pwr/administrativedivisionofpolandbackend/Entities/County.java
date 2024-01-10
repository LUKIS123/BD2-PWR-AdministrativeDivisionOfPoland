package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "powiat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class County {
    @Id
    @Column(name = "id_pow", nullable = false, unique = true, updatable = false)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_woj")
    private Voivodeship voivodeship;
    @Column(name = "nazwa_powiatu")
    private String name;
    @Column(name = "miasto_na_pr_pow")
    private boolean isCityWithCountyRights;
    @Column(name = "wyroznik_tab_rej")
    private String licensePlateDifferentiator;
    @Column(name = "kod_teryt")
    private String TERYTCode;
}
