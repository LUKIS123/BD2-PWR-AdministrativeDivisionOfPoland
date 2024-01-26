package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresy_siedzib")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RegisteredOfficeAddresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_adresu_siedziby", nullable = false, unique = true, updatable = false)
    private Integer id;
    @Column(name = "kod_pocztowy")
    private String postalCode;
    @Column(name = "miejscowosc")
    private String locality;
    @Column(name = "ulica")
    private String street;
    @Column(name = "numer_budynku")
    private String numberOfBuilding;
    @Column(name = "numer_lokalu")
    private String apartmentNumber;
}
