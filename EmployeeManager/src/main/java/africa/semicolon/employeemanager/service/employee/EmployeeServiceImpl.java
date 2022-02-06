package africa.semicolon.employeemanager.service.employee;

import africa.semicolon.employeemanager.data.dto.EmployeeDto;
import africa.semicolon.employeemanager.data.models.Employee;
import africa.semicolon.employeemanager.data.models.EmployeeBuilder;
import africa.semicolon.employeemanager.data.repositories.EmployeeRepository;
import africa.semicolon.employeemanager.web.exceptions.EmployeeManagerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("initial")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long employeeId) throws EmployeeManagerException {
        if(employeeId == null) throw new IllegalArgumentException("ID cannot be null");

        Optional<Employee> queryResult = employeeRepository.findById(employeeId);

        if(queryResult.isPresent()) return queryResult.get();
        throw new EmployeeManagerException("Employee with ID : " + employeeId + " does not exist");
    }

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) throws EmployeeManagerException {
        if (employeeDto == null) throw new EmployeeManagerException("Argument cannot be empty");

        Optional<Employee> queryResult = employeeRepository.findByEmail(employeeDto.generateEmail());
        if (queryResult.isPresent()) throw new EmployeeManagerException("Employee already exist");

        Employee employee = new EmployeeBuilder()
                .setFirstName(employeeDto.getFirstName())
                .setLastName(employeeDto.getLastName()).build();

        return employeeRepository.save(employee);
    }

    private Employee saveOrUpdate(Employee employee) throws EmployeeManagerException {
        if (employee == null) throw new EmployeeManagerException("Argument cannot be empty");
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeDetails(Long employeeId, JsonPatch patch)
            throws EmployeeManagerException {
        if (employeeId == null) throw new EmployeeManagerException("Argument cannot be empty");

        Optional<Employee> employeeQuery = employeeRepository.findById(employeeId);
        if (employeeQuery.isEmpty()) throw new EmployeeManagerException("Employee not found");

        Employee targetEmployee = employeeQuery.get();
        try {
            targetEmployee = applyPatchToEmployee(patch, targetEmployee);
            return saveOrUpdate(targetEmployee);
        }
        catch (JsonPatchException | JsonProcessingException e) {
            throw new EmployeeManagerException("Update failed");
        }
    }

    private Employee applyPatchToEmployee(JsonPatch employeePatch, Employee targetEmployee) throws JsonPatchException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode patch = employeePatch.apply(mapper.convertValue(targetEmployee, JsonNode.class));
        return mapper.treeToValue(patch, Employee.class);
    }

    @Override
    public String deleteEmployee(Employee employee) throws EmployeeManagerException {
        if (employee == null) throw new EmployeeManagerException("Argument cannot be empty");
        if (employeeRepository.findById(employee.getId()).isEmpty()) throw new EmployeeManagerException("");
        employeeRepository.delete(employee);
        return "Employee deleted";
    }

    @Override
    public String deleteAllEmployees() {
        employeeRepository.deleteAll();
        return "All employees deleted";
    }
}
