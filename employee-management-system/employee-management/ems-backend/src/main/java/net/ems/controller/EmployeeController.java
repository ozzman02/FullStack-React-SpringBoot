package net.ems.controller;

import lombok.RequiredArgsConstructor;
import net.ems.dto.EmployeeDto;
import net.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId, employeeDto), HttpStatus.OK);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
    }

}
