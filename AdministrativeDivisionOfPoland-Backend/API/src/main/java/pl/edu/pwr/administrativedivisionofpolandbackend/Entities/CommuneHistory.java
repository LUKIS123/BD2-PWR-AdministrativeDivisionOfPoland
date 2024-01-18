package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zm", nullable = false, unique = true, updatable = false)
    private Integer id;
    @Column(name = "id_gm")
    private Integer communeId;
    @Column(name = "id_pow")
    private Integer countyId;
    @Column(name = "nazwa_gminy")
    private String name;
    @Column(name = "adres_siedziby")
    private String legalAddress;
    @Column(name = "liczba_ludnosci")
    private Integer population;
    @Column(name = "powierzchnia")
    private Double area;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rodzaj_gminy")
    private CommuneType communeType;
    @Column(name = "data_poczatkowa")
    private LocalDateTime validityStartDate;
    @Column(name = "data_koncowa")
    private LocalDateTime validityEndDate;
}
