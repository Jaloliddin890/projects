package uz.pdp.springbootdatajpa.JpaRepository;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NamedQuery;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@NamedQuery(name = "Post.getAllByUserID",
            query = "select p from Post p where p.userId=?1")

public class Post {

    @Id
    @GeneratedValue
    private Integer id;


    @Column(nullable = false)
    private Integer userId;


    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    @Lob
    private String body;
}
