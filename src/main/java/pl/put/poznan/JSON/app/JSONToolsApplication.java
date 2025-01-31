package pl.put.poznan.JSON.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.JSON.rest"})
public class JSONToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JSONToolsApplication.class, args);
    }
}
