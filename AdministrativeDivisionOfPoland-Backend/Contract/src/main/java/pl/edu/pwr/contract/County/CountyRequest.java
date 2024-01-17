package pl.edu.pwr.contract.County;

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
public class CountyRequest {

    public Integer voivodeshipId;
    public String name;
    public Boolean isCityWithCountyRights;
    public String licensePlateDifferentiator;
    public String TERYTCode;

    // Office
    public Integer registeredOfficeAddressesId;
    public String locality;
}
