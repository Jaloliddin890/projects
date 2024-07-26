package uz.pdp.XML;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("CityBean.xml");
        City city = context.getBean(City.class);

        System.out.println("City Name: " + city.getName());
        System.out.println("Number of People: " + city.getNumberOfPeople());
        System.out.println("Size: " + city.getSize());

    }
}