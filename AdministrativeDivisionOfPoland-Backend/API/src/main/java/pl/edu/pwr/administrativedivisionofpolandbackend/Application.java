package pl.edu.pwr.administrativedivisionofpolandbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // TODO:
        // -dodac Contract (nowy modul) w kotrym beda trzymane obiekty typu DTO
        // aby udostepnic jara do frontu
    }

}
