package uz.pdp.mapstruct.propeties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import uz.pdp.mapstruct.propeties.Person;



@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "example.person")
public class PersonProperties {

    // Getters and Setters
    private Long id;
    private String name;
    private Integer age;



    public Person getPerson() {
        return new Person(id, name, age);
    }
}

