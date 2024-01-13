package pl.edu.pwr.contract.OfficeAdres;

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
public class AddOfficeAddressRequest {
    public String postalCode;
    public String locality;
    public String street;
    public String numberOfBuilding;
    public String apartmentNumber;
}
