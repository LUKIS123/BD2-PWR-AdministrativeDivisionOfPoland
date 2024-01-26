package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "siedziby_wojewodztw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VoivodeshipRegisteredOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siedz", nullable = false, unique = true, updatable = false)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_woj")
    private Voivodeship voivodeship;
    @Column(name = "miejscowosc_siedziby")
    private String locality;
    @Column(name = "siedziba_wojewody")
    private Boolean isSeatOfVoivode;
    @Column(name = "siedziba_sejmiku")
    private Boolean isSeatOfCouncil;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adresu_siedziby")
    private RegisteredOfficeAddresses registeredOfficeAddresses;
}
