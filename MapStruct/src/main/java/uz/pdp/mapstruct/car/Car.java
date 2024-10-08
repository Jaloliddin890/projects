package uz.pdp.mapstruct.car;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car {
    private String id;
    private String name;
    private String color;

    private double price;
    private String description;

    private String maker;
    private String country;
}
