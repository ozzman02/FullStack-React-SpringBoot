package net.ems.controller;

import lombok.RequiredArgsConstructor;
import net.ems.dto.DepartmentDto;
import net.ems.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.createDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long departmentId) {
        return new ResponseEntity<>(departmentService.getDepartmentById(departmentId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId, @RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId, departmentDto), HttpStatus.OK);
    }

    @DeleteMapping("{departmentId}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long departmentId) {
        return new ResponseEntity<>(departmentService.deleteDepartment(departmentId), HttpStatus.NO_CONTENT);
    }

}
