package uz.pdp.mapstruct.store;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Store {
    private Integer id;
    private String name;
    private String description;
}
