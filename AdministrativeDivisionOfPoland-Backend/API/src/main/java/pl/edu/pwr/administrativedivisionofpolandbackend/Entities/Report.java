package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "zgloszenia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Report {
    @Id
    @Column(name = "id_zgl", nullable = false, unique = true, updatable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_woj")
    private Voivodeship voivodeship;
    @ManyToOne
    @JoinColumn(name = "id_pow")
    private County county;
    @ManyToOne
    @JoinColumn(name = "id_gm")
    private Commune commune;
    @Column(name = "temat")
    private String topic;
    @Column(name = "tresc")
    private String content;
    @Column(name = "data_zgloszenia")
    private LocalDateTime reportingDate;
}
