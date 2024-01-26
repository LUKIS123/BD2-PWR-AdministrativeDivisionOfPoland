package pl.edu.pwr.contract.OfficeAdres;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeAddressRequest {
    public String postalCode;
    public String locality;
    public String street;
    public String numberOfBuilding;
    public String apartmentNumber;
}
