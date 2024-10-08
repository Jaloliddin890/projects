package uz.pdp.mapstruct.employee;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    private int id;
    private String name;
}
