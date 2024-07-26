package uz.pdp.JavaConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CityConfig.class);
        City city = context.getBean(City.class);
        System.out.println(city);

    }
}
