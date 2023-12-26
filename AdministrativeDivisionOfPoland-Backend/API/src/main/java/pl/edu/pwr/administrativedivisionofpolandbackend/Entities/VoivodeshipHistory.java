package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

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
@Table(name = "historia_wojewodztw")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoivodeshipHistory {
    @Id
    @Column(name = "id_zm", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "id_woj")
    private int voivodeshipId;
    @Column(name = "nazwa_wojewodztwa")
    private String name;
    @Column(name = "adres_siedziby")
    private String legalAddress;
    @Column(name = "liczba_ludnosci")
    private int population;
    @Column(name = "powierzchnia")
    private double are;
    @Column(name = "wyroznik_tab_rej")
    private String licensePlateDifferentiator;
    @Column(name = "data_poczatkowa")
    private LocalDateTime validityStartDate;
    @Column(name = "data_koncowa")
    private LocalDateTime validityEndDate;
}
