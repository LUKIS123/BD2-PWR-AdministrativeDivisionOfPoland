package pl.edu.pwr.administrativedivisionofpolandbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.edu.pwr.administrativedivisionofpolandbackend.Settings.Secrets;

@SpringBootApplication
@EnableConfigurationProperties(Secrets.class)
public class Application {
    // SWAGGER-UI: http://localhost:8085/swagger-ui/index.html#/
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
