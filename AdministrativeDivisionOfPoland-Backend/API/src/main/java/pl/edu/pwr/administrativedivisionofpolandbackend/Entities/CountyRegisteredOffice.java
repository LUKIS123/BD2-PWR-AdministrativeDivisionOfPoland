package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "siedziby_powiatow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CountyRegisteredOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_siedz", nullable = false, unique = true, updatable = false)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pow")
    private County county;
    @Column(name = "miejscowosc_siedziby")
    private String locality;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_adresu_siedziby")
    private RegisteredOfficeAddresses registeredOfficeAddresses;
}
