package net.ems.service.impl;

import lombok.RequiredArgsConstructor;
import net.ems.dto.EmployeeDto;
import net.ems.entity.Department;
import net.ems.entity.Employee;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.EmployeeMapper;
import net.ems.repository.DepartmentRepository;
import net.ems.repository.EmployeeRepository;
import net.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.ems.mapper.EmployeeMapper.mapToEmployee;
import static net.ems.mapper.EmployeeMapper.mapToEmployeeDto;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEmployee(employeeDto);
        employee.setDepartment(findDepartmentById(employeeDto.getDepartmentId()));
        return mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .map(EmployeeMapper::mapToEmployeeDto)
                .orElseThrow(() -> new ResourceNotFoundException("Employee with id " + employeeId + " does not exist"));
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        return employeeRepository.findById(employeeId).map(existingEmployee -> {
            existingEmployee.setFirstName(employeeDto.getFirstName());
            existingEmployee.setLastName(employeeDto.getLastName());
            existingEmployee.setEmail(employeeDto.getEmail());
            existingEmployee.setDepartment(findDepartmentById(employeeDto.getDepartmentId()));
            return mapToEmployeeDto(employeeRepository.save(existingEmployee));
        }).orElseThrow(
                () -> new ResourceNotFoundException("Employee with id " + employeeId + " does not exist")
        );
    }

    @Override
    public boolean deleteEmployee(Long employeeId) {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
            return true;
        } else {
            throw new ResourceNotFoundException("Employee with id " + employeeId + " does not exist");
        }
    }

    private Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Department with id " + departmentId + "does not exist")
                );
    }

}
