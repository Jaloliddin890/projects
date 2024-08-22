package uz.pdp.HomeTaskBook.BookDTO;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class dto {
    private String title;
    private String description;
    private Double price;
    private String author;
}
