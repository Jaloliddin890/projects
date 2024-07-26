package uz.pdp.JavaConfig;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:city.properties")
//@ComponentScan(basePackages = "uz.pdp.JavaConfig")
public class CityConfig {

    @Bean
    @Scope("prototype")
    public City city(
            @Value("${city.name}") String name,
            @Value("${city.numberOfPeople}") int numberOfPeople,
            @Value("${city.size}") double size
    ) {
        return new City(name,numberOfPeople,size);
    }


}
