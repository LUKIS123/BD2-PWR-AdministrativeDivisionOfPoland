package pl.edu.pwr.contract.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ReportDto {
    public Integer id;
    public Integer voivodeshipId;
    public String voivodeshipName;
    public Integer countyId;
    public String countyName;
    public Integer communeId;
    public String communeName;
    public String topic;
    public String content;
    public LocalDateTime reportingDate;
}
