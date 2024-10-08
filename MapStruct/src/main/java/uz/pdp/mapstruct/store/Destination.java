package uz.pdp.mapstruct.store;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Destination {
    private Integer id;
    private String name;
    private String description;
}
