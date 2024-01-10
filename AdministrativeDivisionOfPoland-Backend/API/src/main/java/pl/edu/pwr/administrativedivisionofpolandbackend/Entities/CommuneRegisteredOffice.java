package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "siedziby_gmin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommuneRegisteredOffice {
    @Id
    @Column(name = "id_siedz", nullable = false, unique = true, updatable = false)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gm")
    private Commune commune;
    @Column(name = "miejscowosc_siedziby")
    private String locality;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adresu_siedziby")
    private RegisteredOfficeAddresses registeredOfficeAddresses;
}
