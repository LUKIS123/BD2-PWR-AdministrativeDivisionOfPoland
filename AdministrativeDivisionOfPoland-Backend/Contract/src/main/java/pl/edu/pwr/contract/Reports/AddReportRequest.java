package pl.edu.pwr.contract.Reports;

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
public class AddReportRequest {
    public Integer voivodeshipId;
    public Integer countyId;
    public Integer communeId;
    public String topic;
    public String content;
}
