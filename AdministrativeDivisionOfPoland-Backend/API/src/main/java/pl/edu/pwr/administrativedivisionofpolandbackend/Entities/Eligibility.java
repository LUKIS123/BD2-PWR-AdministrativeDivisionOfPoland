package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "uprawnienia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Eligibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_upr", nullable = false, unique = true, updatable = false)
    private Integer id;
    @Column(name = "id_woj")
    private Integer voivodeshipId;
    @Column(name = "id_pow")
    private Integer countyId;
}
