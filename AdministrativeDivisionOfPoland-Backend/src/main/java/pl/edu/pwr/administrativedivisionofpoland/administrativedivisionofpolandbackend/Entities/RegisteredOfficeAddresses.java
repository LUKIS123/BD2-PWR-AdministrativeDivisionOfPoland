package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "adresy_siedzib")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class RegisteredOfficeAddresses {
    @Id
    @Column(name = "id_adresu_siedziby", nullable = false, unique = true, updatable = false)
    private int id;
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
