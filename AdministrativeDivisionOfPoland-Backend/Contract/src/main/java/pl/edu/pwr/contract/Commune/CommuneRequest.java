package pl.edu.pwr.contract.Commune;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CommuneRequest {
    public Integer countyId;
    public String name;
    public Integer population;
    public Double area;
    public Integer communeTypeId;
    public String terytCode;
    // Office
    public Integer registeredOfficeAddressesId;
    public String locality;
}
