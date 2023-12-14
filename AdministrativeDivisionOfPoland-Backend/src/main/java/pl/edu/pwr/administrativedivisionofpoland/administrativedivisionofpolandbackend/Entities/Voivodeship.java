package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "wojewodztwo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Voivodeship {
    @Id
    @Column(name = "id_woj", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "nazwa_wojewodztwa")
    private String name;
    @Column(name = "wyroznik_tab_rej")
    private String licensePlateDifferentiator;
}
