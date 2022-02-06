package africa.semicolon.employeemanager.service.employee;


import africa.semicolon.employeemanager.data.dto.EmployeeDto;
import africa.semicolon.employeemanager.data.models.Employee;
import africa.semicolon.employeemanager.web.exceptions.EmployeeManagerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee findEmployeeById(Long employeeId) throws EmployeeManagerException;
    Employee createEmployee(EmployeeDto employeeDto) throws EmployeeManagerException;
    Employee updateEmployeeDetails(Long employeeId, JsonPatch patch) throws EmployeeManagerException, JsonPatchException, JsonProcessingException;
    String deleteEmployee(Employee employee) throws EmployeeManagerException;
    String deleteAllEmployees();
}
