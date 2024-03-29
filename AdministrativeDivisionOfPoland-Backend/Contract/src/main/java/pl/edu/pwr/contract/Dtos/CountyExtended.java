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
public class CountyExtended {
    public Integer id;
    public Integer voivodeshipId;
    public String voivodeshipName;
    public String name;
    public Boolean isCityWithCountyRights;
    public String licensePlateDifferentiator;
    public String terytCode;
    public Integer population;
    public Double area;
}
