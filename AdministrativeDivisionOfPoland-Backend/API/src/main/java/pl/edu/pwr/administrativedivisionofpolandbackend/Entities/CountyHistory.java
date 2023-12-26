package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historia_powiatow")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CountyHistory {
    @Id
    @Column(name = "id_zm", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "id_pow")
    private int countyId;
    @Column(name = "id_woj")
    private int voivodeshipId;
    @Column(name = "nazwa_powiatu")
    private String name;
    @Column(name = "adres_siedziby")
    private String legalAddress;
    @Column(name = "liczba_ludnosci")
    private int population;
    @Column(name = "powierzchnia")
    private double are;
    @Column(name = "miasto_na_pr_pow")
    private boolean isCityWithCountyRights;
    @Column(name = "wyroznik_tab_rej")
    private String licensePlateDifferentiator;
    @Column(name = "data_poczatkowa")
    private LocalDateTime validityStartDate;
    @Column(name = "data_koncowa")
    private LocalDateTime validityEndDate;
}
