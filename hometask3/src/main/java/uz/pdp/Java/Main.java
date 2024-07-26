package uz.pdp.Java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.pdp.XML.TransForm;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Transform bean = context.getBean(Transform.class);
        bean.start();
    }
}
