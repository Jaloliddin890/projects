package uz.pdp.mapstruct.employee;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import uz.pdp.mapstruct.store.MapperForTwoClass;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);


    @Mapping(target = "employeeId", source = "id")
    @Mapping(target = "employeeName", source = "name")
    EmployeeDto dto (Employee employee);


    @InheritInverseConfiguration
    Employee entity (EmployeeDto dto);
}
