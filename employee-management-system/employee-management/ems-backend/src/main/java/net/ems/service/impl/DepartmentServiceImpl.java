package net.ems.service.impl;

import lombok.RequiredArgsConstructor;
import net.ems.dto.DepartmentDto;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.DepartmentMapper;
import net.ems.repository.DepartmentRepository;
import net.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.ems.mapper.DepartmentMapper.mapToDepartment;
import static net.ems.mapper.DepartmentMapper.mapToDepartmentDto;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        return mapToDepartmentDto(departmentRepository.save(mapToDepartment(departmentDto)));
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .map(DepartmentMapper::mapToDepartmentDto)
                .orElseThrow(() -> new ResourceNotFoundException("Department with id " + departmentId + " does not exist"));
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        return departmentRepository.findById(departmentId).map(existingDepartment -> {
            existingDepartment.setName(departmentDto.getName());
            existingDepartment.setDescription(departmentDto.getDescription());
            return mapToDepartmentDto(existingDepartment);
        }).orElseThrow(
                () -> new ResourceNotFoundException("Department with id " + departmentId + " does not exist")
        );
    }

    @Override
    public boolean deleteDepartment(Long departmentId) {
        if (departmentRepository.existsById(departmentId)) {
            departmentRepository.deleteById(departmentId);
            return true;
        } else {
            throw new ResourceNotFoundException("Department with id " + departmentId + " does not exist");
        }
    }

}
