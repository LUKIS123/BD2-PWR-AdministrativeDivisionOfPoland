package pl.edu.pwr.administrativedivisionofpolandbackend.Settings;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secrets")
public record Secrets(String jwtSecret) {
}
