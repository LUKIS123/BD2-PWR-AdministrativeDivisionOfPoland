package pl.edu.pwr.contract.Reports;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddReportRequest {
    public Integer voivodeshipId;
    public Integer countyId;
    public Integer communeId;
    public String topic;
    public String content;
}
