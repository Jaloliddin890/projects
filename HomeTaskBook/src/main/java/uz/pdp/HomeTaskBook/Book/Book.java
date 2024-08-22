package uz.pdp.HomeTaskBook.Book;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {
    private int id;
    private String title;
    private String description;
    private Double price;
    private String author;
}
