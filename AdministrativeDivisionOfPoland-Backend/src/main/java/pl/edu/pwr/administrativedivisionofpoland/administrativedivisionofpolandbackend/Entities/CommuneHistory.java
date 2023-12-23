package pl.edu.pwr.administrativedivisionofpoland.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historia_gmin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommuneHistory {
    @Id
    @Column(name = "id_zm", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "id_gm")
    private int communeId;
    @Column(name = "id_pow")
    private int countyId;
    @Column(name = "nazwa_gminy")
    private String name;
    @Column(name = "adres_siedziby")
    private String legalAddress;
    @Column(name = "liczba_ludnosci")
    private int population;
    @Column(name = "powierzchnia")
    private double are;
    @Column(name = "id_rodzaj_gminy")
    private int communeTypeId;
    @Column(name = "data_poczatkowa")
    private LocalDateTime validityStartDate;
    @Column(name = "data_koncowa")
    private LocalDateTime validityEndDate;
}
