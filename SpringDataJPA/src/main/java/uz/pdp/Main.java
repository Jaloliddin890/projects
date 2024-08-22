package uz.pdp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.pdp.book.Book;
import uz.pdp.book.BookRepository;
import uz.pdp.config.ApplicationConfigurer;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigurer.class);
        BookRepository bean = context.getBean(BookRepository.class);
//        Book book = Book.builder()
//                .title("Spring Security")
//                .author("Mee")
//                .build();
//        bean.save(book);
        bean.findAll().forEach(System.out::println);

    }
}