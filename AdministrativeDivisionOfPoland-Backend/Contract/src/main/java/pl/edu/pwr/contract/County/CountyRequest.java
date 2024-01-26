package pl.edu.pwr.contract.County;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountyRequest {

    public Integer voivodeshipId;
    public String name;
    public Boolean isCityWithCountyRights;
    public String licensePlateDifferentiator;
    public String terytCode;

    // Office
    public Integer registeredOfficeAddressesId;
    public String locality;
}
