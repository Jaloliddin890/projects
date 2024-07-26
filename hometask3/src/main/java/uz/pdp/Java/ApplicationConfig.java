package uz.pdp.Java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfig {


    @Bean
    public Transform transform(){
        return new Transform();
    }

    @Bean
    public TransformMagic transformMagic(){
        return new TransformMagic();
    }
}
