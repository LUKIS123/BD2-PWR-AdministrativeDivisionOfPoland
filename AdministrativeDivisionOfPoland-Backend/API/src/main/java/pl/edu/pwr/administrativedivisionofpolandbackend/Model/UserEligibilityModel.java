package pl.edu.pwr.administrativedivisionofpolandbackend.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEligibilityModel {
    public Integer id;
    public Integer voivodeshipId;
    public Integer countyId;
    public Integer userId;
    public LocalDateTime validityStartingDate;
    public LocalDateTime validityEndingDate;
    public String login;
}
