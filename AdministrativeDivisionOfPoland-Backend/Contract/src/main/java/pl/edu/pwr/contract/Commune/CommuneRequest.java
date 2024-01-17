package pl.edu.pwr.contract.Commune;

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
public class CommuneRequest {
    public Integer countyId;
    public String name;
    public Integer population;
    public Double are;
    public Integer communeTypeId;
    public String TERYTCode;
    // Office
    public Integer registeredOfficeAddressesId;
    public String locality;
}
