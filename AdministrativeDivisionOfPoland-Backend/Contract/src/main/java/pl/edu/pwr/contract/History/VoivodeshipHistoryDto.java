package pl.edu.pwr.contract.History;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VoivodeshipHistoryDto {
    public Integer id;
    public Integer voivodeshipId;
    public String voivodeshipName;
    public String address;
    public Integer population;
    public Double area;
    public String licensePlateDifferentiator;
    public LocalDateTime startDate;
    public LocalDateTime endDate;
}
