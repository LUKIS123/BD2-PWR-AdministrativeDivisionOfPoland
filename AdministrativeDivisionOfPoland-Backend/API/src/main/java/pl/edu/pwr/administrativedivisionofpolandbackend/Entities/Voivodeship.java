package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wojewodztwo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Voivodeship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_woj", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "nazwa_wojewodztwa")
    private String name;
    @Column(name = "wyroznik_tab_rej")
    private String licensePlateDifferentiator;
    @Column(name = "kod_teryt")
    private String TERYTCode;
}
