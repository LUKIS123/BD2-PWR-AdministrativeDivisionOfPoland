package pl.edu.pwr.contract.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommuneDto {
    public Integer id;
    public Integer countyId;
    public String countyName;
    public String name;
    public Integer population;
    public Double area;
    public String communeType;
    public String terytCode;
}
