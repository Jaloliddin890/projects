package uz.pdp.mapstruct;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.pdp.mapstruct.propeties.PersonProperties;

@SpringBootApplication
@EnableConfigurationProperties({PersonProperties.class})
public class MapStructApplication {
    public static void main(String[] args) {
        SpringApplication.run(MapStructApplication.class, args);
    }
}
