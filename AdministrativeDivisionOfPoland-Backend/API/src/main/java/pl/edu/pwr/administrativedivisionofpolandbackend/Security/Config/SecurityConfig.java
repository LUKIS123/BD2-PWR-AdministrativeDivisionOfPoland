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
            "/api/commune/{id}",
            "/api/commune/byCounty",
            "/api/commune/all",
            "/api/commune/search",
            "/api/commune/address/{id}",
            "/api/commune/address/all",
            "/api/commune/address/byCounty",
            "/api/commune/teryt",
            "/api/county/{id}",
            "/api/county/byVoivodeship",
            "/api/county/all",
            "/api/county/search",
            "api/county/extended/{id}",
            "api/county/extended/byVoivodeship",
            "api/county/extended/all",
            "api/county/address/{id}",
            "api/county/address/byVoivodeship",
            "api/county/address/all",
            "/api/county/teryt",
            "/api/voivodeship/{id}",
            "/api/voivodeship/all",
            "/api/voivodeship/search",
            "api/voivodeship/extended/{id}",
            "api/voivodeship/extended/all",
            "api/voivodeship/address/{id}",
            "api/voivodeship/address/all",
            "/api/voivodeship/teryt",
            "api/history/**",
            "api/address/{id}",
            "api/address/all",
            "/api/report/all",
            "/api/report/{id}",
            "/api/report/byVoivodeship",
            "/api/report/byCounty",
            "/api/report/byCommune",
            "/api/report/add",
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
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                        .anyRequest().authenticated()
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
