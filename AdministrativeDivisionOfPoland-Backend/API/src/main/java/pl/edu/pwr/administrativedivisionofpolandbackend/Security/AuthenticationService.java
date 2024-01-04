package pl.edu.pwr.administrativedivisionofpolandbackend.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pwr.administrativedivisionofpolandbackend.Entities.User;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.TokenRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Repositories.UserRepository;
import pl.edu.pwr.administrativedivisionofpolandbackend.Security.Token.Token;
import pl.edu.pwr.administrativedivisionofpolandbackend.Security.Token.TokenType;
import pl.edu.pwr.contract.Auth.AuthenticateRequest;
import pl.edu.pwr.contract.Auth.AuthenticationResponse;
import pl.edu.pwr.contract.Auth.RegisterRequest;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .login(registerRequest.getLogin())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getLogin(),
                        authenticateRequest.getPassword()
                )
        );
        var user = userRepository.findByLogin(authenticateRequest.getLogin()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
