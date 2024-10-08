package tmrv.dev.houseonlinemarket.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import tmrv.dev.houseonlinemarket.entities.domains.Type;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String title;

    private String description;

    private double price;

    private String location;

    @Enumerated(EnumType.STRING)
    private Type type;

    private boolean available;

    @NotEmpty
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
