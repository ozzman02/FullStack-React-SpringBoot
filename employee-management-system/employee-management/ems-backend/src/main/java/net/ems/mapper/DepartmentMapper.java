package net.ems.mapper;

import net.ems.dto.DepartmentDto;
import net.ems.entity.Department;

public class DepartmentMapper {

    public static DepartmentDto mapToDepartmentDto(Department department) {
        return new DepartmentDto(department.getId(), department.getName(), department.getDescription());
    }

    public static Department mapToDepartment(DepartmentDto departmentDto) {
        return new Department(departmentDto.getId(), departmentDto.getName(), departmentDto.getDescription());
    }

}
