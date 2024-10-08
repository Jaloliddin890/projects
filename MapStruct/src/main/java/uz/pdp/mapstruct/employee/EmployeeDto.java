package uz.pdp.mapstruct.employee;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private int employeeId;
    private String employeeName;
}
