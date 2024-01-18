package pl.edu.pwr.administrativedivisionofpolandbackend.Security.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.pwr.administrativedivisionofpolandbackend.Security.JwtService;
import pl.edu.pwr.administrativedivisionofpolandbackend.Validation.UserValidationService;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserEligibilityFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserValidationService userValidationService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String bearerName = "Bearer";
        final String jwt;
        final String userLogin;

        if (request.getContextPath().contains("/swagger-ui/")) {
            return;
        }
        if (Objects.equals(request.getMethod(), HttpMethod.GET.name())) {
            filterChain.doFilter(request, response);
            return;
        }
        if (authHeader == null || !authHeader.startsWith(bearerName)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(bearerName.length());
        userLogin = jwtService.extractUsername(jwt);
        if (userLogin == null) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean validateUser = userValidationService.validateUser(userLogin);
        if (validateUser) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User not eligible to perform this action");
        }
    }
}
