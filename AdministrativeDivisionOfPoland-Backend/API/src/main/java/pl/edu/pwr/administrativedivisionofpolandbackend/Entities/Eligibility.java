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

@Entity
@Table(name = "uprawnienia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Eligibility {
    @Id
    @Column(name = "id_upr", nullable = false, unique = true, updatable = false)
    private int id;
    @Column(name = "id_woj")
    private int voivodeshipId;
    @Column(name = "id_pow")
    private int countyId;
}
