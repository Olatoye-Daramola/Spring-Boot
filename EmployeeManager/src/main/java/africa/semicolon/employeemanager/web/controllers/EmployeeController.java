package africa.semicolon.employeemanager.web.controllers;

import africa.semicolon.employeemanager.data.dto.EmployeeDto;
import africa.semicolon.employeemanager.data.models.Employee;
import africa.semicolon.employeemanager.service.employee.EmployeeService;
import africa.semicolon.employeemanager.web.exceptions.EmployeeManagerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    @Qualifier("initial")
    EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto employeeDto) {
        if (employeeDto == null) return ResponseEntity.status(NOT_FOUND).body("Provide employee details");
        try {
            Employee employee = employeeService.createEmployee(employeeDto);
            return ResponseEntity.status(CREATED).body(employee.getFirstName() + " " + employee.getLastName() + " saved");
        } catch (EmployeeManagerException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployeeById(@PathVariable Long id) {
        if (id == null) return ResponseEntity.status(NOT_FOUND).body("Provide employee id");
        try{
            Employee foundEmployee = employeeService.findEmployeeById(id);
            return ResponseEntity.status(FOUND).body(foundEmployee);
        } catch (EmployeeManagerException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAllEmployees() {
        List<Employee> listOfEmployees = employeeService.getAllEmployees();
        return ResponseEntity.status(OK).body(listOfEmployees);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateEmployeeDetails(@PathVariable Long id, @RequestBody JsonPatch patch) {
        if (id == null) return ResponseEntity.status(NOT_FOUND).body("Provide employee id");
        try {
            Employee updatedEmployee = employeeService.updateEmployeeDetails(id, patch);
            return ResponseEntity.status(OK).body(updatedEmployee);
        } catch (EmployeeManagerException | JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteEmployee(@RequestBody Employee employee) {
        if (employee == null) return ResponseEntity.status(NOT_FOUND).body("Provide employee");
        try {
            employeeService.deleteEmployee(employee);
            return ResponseEntity.status(OK).body("Employee successfully deleted");
        } catch (EmployeeManagerException e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllEmployees() {
        employeeService.deleteAllEmployees();
        return ResponseEntity.status(OK).body("All employees successfully deleted");
    }
}
