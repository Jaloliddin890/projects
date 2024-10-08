package uz.pdp.mapstruct.car;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakerDto {

    private String maker;
    private String country;
}
