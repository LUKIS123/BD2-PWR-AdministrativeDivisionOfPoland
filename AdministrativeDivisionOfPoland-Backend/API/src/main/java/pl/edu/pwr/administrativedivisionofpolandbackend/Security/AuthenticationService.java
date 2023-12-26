package pl.edu.pwr.administrativedivisionofpolandbackend.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.contract.AuthenticateRequest;
import pl.edu.pwr.contract.AuthenticationResponse;
import pl.edu.pwr.contract.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        return null;
    }

    public AuthenticationResponse authenticate(AuthenticateRequest registerRequest) {
        return null;
    }
}
