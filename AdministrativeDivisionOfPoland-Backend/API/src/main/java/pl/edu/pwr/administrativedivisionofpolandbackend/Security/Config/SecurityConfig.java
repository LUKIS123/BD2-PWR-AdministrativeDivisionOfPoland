package pl.edu.pwr.administrativedivisionofpolandbackend.Security.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import pl.edu.pwr.administrativedivisionofpolandbackend.Configuration.ApplicationConfig;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final ApplicationConfig applicationConfig;
    private static final String[] WHITE_LIST_URLS = {
            "/v2/**",
            "/v3/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/auth/**",
            "/api/voivodeship/**",
            "/api/county/**",
            "/api/commune/**",
            "/api/report/**",
            "/api/address/**",
            "/api/history/**",
    };

    private static final String[] BLACK_LIST_URLS = {
            "/api/voivodeship/add/**",
            "/api/voivodeship/update/**",
            "/api/voivodeship/delete/**",
            "/api/county/add/**",
            "/api/county/update/**",
            "/api/county/delete/**",
            "/api/commune/add/**",
            "/api/commune/update/**",
            "/api/commune/delete/**",
            "/api/report/delete/**",
            "/api/address/add/**",
            "/api/address/update/**",
            "/api/address/delete/**",
    };

    private final JwtAuthenticationFilter jwtAuthFiler;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(applicationConfig.corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(BLACK_LIST_URLS).authenticated()
                        .anyRequest().permitAll()
                )
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFiler, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
