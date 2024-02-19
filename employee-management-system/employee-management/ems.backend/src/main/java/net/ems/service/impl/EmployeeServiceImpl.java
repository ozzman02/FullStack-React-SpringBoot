package net.ems.service.impl;

import lombok.RequiredArgsConstructor;
import net.ems.dto.EmployeeDto;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.EmployeeMapper;
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

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return mapToEmployeeDto(employeeRepository.save(mapToEmployee(employeeDto)));
    }

    @Override
    public EmployeeDto getEmployeeId(Long employeeId) {
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

}
