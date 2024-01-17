package pl.edu.pwr.contract.Voivodeship;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VoivodeshipRequest {
    public String name;
    public String licensePlateDifferentiator;
    public String terytCode;

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
