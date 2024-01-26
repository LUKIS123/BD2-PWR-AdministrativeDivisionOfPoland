package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "siedziby_gmin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommuneRegisteredOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siedz", nullable = false, unique = true, updatable = false)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gm")
    private Commune commune;
    @Column(name = "miejscowosc_siedziby")
    private String locality;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adresu_siedziby")
    private RegisteredOfficeAddresses registeredOfficeAddresses;
}
