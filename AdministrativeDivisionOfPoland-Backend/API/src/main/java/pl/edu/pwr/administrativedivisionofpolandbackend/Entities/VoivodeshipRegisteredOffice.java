package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "siedziby_wojewodztw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VoivodeshipRegisteredOffice {
    @Id
    @Column(name = "id_siedz", nullable = false, unique = true, updatable = false)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_woj")
    private Voivodeship voivodeship;
    @Column(name = "miejscowosc_siedziby")
    private String locality;
    @Column(name = "siedziba_wojewody")
    private boolean isSeatOfVoivode;
    @Column(name = "siedziba_sejmiku")
    private boolean isSeatOfCouncil;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adresu_siedziby")
    private RegisteredOfficeAddresses registeredOfficeAddresses;
}
