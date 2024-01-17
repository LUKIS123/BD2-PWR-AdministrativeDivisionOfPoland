package pl.edu.pwr.contract.Voivodeship;

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
public class AddVoivodeshipRequest {
    public String name;
    public String licensePlateDifferentiator;
    public String TERYTCode;

    // First Office
    public String localityFirst;
    public Boolean isSeatOfVoivodeFirst;
    public Boolean isSeatOfCouncilFirst;
    public Integer registeredOfficeAddressesIdFirst;

    // Second Office
    public String localitySecond;
    public Boolean isSeatOfVoivodeSecond;
    public Boolean isSeatOfCouncilSecond;
    public Integer registeredOfficeAddressesIdSecond;
}
