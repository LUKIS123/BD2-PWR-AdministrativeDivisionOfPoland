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
public class VoivodeshipAddressData {
    public Integer id;

    public String name;

    public String officeLocalityName;

    public Boolean isSeatOfVoivode;

    public Boolean isSeatOfCouncil;

    public String postalCode;

    public String locality;

    public String street;

    public String buildingNumber;

    public String apartmentNumber;
}
