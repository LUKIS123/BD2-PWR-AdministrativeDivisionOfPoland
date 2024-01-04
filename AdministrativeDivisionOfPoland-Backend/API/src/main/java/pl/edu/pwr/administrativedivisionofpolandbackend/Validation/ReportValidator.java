package pl.edu.pwr.administrativedivisionofpolandbackend.Validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pwr.contract.Reports.AddReportRequest;

@Component
@RequiredArgsConstructor
public class ReportValidator {
    public boolean validateAddReportRequest(AddReportRequest addReportRequest) {
        return addReportRequest.topic != null && addReportRequest.content != null;
    }
}
