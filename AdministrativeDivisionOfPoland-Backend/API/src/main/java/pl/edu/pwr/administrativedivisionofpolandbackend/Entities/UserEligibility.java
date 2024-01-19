package pl.edu.pwr.administrativedivisionofpolandbackend.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lista_uprawnien")
@Getter
@Setter
public class UserEligibility {
    @Id
    @Column(name = "id_upr", nullable = false, unique = true, updatable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_admin")
    private User user;

    @Column(name = "data_poczatkowa")
    LocalDateTime validityStartingDate;

    @Column(name = "data_koncowa")
    LocalDateTime validityEndingDate;
}
