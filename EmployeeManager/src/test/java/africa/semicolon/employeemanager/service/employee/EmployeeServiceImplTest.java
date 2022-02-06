package africa.semicolon.employeemanager.service.employee;

import africa.semicolon.employeemanager.data.dto.EmployeeDto;
import africa.semicolon.employeemanager.data.models.Employee;
import africa.semicolon.employeemanager.web.exceptions.EmployeeManagerException;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    @Qualifier("initial")
    EmployeeService employeeService;

    Employee employee;
    EmployeeDto employeeDto;
    EmployeeDto anotherEmployeeDto;

    @BeforeEach
    void setUp() {
        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Amarachi");
        employeeDto.setLastName("Onyeukwu");

        anotherEmployeeDto = new EmployeeDto();
        anotherEmployeeDto.setFirstName("Israel");
        anotherEmployeeDto.setLastName("Fasina");
    }

    @AfterEach
    void tearDown() {
        employeeService.deleteAllEmployees();
    }

    @Test
    void saveEmployeeToDatabaseTest() throws EmployeeManagerException {
        assertThat(employeeDto).isNotNull();
        employee = employeeService.createEmployee(employeeDto);

        assertThat(employee.getId()).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Amarachi");
        assertThat(employee.getEmail()).isEqualTo("a.onyeukwu@email.com");
    }

    @Test
    void findAllEmployeesTest() throws EmployeeManagerException {
        List<Employee> employeeList = employeeService.getAllEmployees();
        assertThat(employeeList).isEmpty();

        employeeService.createEmployee(employeeDto);
        employeeService.createEmployee(anotherEmployeeDto);
        employeeList = employeeService.getAllEmployees();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    void findEmployeeByIdTest() throws EmployeeManagerException {
        Employee savedEmployee = employeeService.createEmployee(employeeDto);

        Employee foundEmployee = employeeService.findEmployeeById(savedEmployee.getId());

        assertThat(foundEmployee.getId()).isEqualTo(savedEmployee.getId());
        assertThat(foundEmployee.getFirstName()).isEqualTo("Amarachi");
        assertThat(foundEmployee.getEmail()).isEqualTo("a.onyeukwu@email.com");
    }

    @Test
    void deleteEmployeeTest() throws EmployeeManagerException {
        Employee employee = employeeService.createEmployee(employeeDto);
        assertThat(employeeService.getAllEmployees()).isNotEmpty();
        assertThat(employeeService.findEmployeeById(employee.getId())).isNotNull();

        employeeService.deleteEmployee(employee);
        assertThat(employeeService.getAllEmployees()).isEmpty();
    }

    @Test
    void deleteAllEmployees() throws EmployeeManagerException {
        Employee employee = employeeService.createEmployee(employeeDto);
        Employee secondEmployee = employeeService.createEmployee(anotherEmployeeDto);
        assertThat(employeeService.getAllEmployees()).isNotEmpty();
        assertThat(employeeService.getAllEmployees().size()).isEqualTo(2);

        employeeService.deleteAllEmployees();
        assertThat(employeeService.getAllEmployees()).isEmpty();
        assertThat(employeeService.getAllEmployees().size()).isEqualTo(0);
    }

//    @Test
//    void updateEmployeeDetailsTest() throws IOException {
//        JsonPatch patch = Files.(Path.of("payload.json"));
//    }
}