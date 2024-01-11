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
public class CountyAddressData {
    public Integer id;

    public Integer voivodeshipId;

    public String name;

    public String officeLocalityName;

    public String postalCode;

    public String locality;

    public String street;

    public String buildingNumber;

    public String apartmentNumber;
}
