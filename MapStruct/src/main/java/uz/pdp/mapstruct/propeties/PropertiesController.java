package uz.pdp.mapstruct.propeties;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {



    private final PersonProperties personProperties;



//    @Value("${database.url}")
//    private String exampleFirst;
//    @Value("${database.username}")
//    private String exampleSecond;
//    @Value("${database.password}")
//    private String exampleThird;
//    @Value("${secret}")
//    private String exampleFourth;

    public PropertiesController(PersonProperties personProperties) {
        this.personProperties = personProperties;
    }

//
//    @GetMapping("/1")
//    public String getExampleFirst() {
//        return exampleFirst;
//    }
//    @GetMapping("/2")
//    public String getExampleSecond() {
//        return exampleSecond;
//    }
//    @GetMapping("/3")
//    public String exampleThird() {
//        return exampleThird;
//    }
//    @GetMapping("/4")
//    public String exampleFourth() {
//        return exampleFourth;
//    }


    @GetMapping("/person")
    public Person person(){
        return personProperties.getPerson();
    }
}
