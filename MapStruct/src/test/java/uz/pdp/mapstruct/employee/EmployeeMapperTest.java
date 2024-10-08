package uz.pdp.mapstruct.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static uz.pdp.mapstruct.employee.EmployeeMapper.EMPLOYEE_MAPPER;

class EmployeeMapperTest {

    @Test
    void dto() {
        Employee employee = new Employee(1, "Anvar");
        EmployeeDto dto = EMPLOYEE_MAPPER.dto(employee);
        System.out.println("dto = " + dto);

    }

    @Test
    void entity() {
        EmployeeDto employeeDto = new EmployeeDto(1, "Anvar");
        Employee entity = EMPLOYEE_MAPPER.entity(employeeDto);
        System.out.println("entity = " + entity);
    }
}