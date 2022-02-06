package africa.semicolon.employeemanager.data.repositories;

import africa.semicolon.employeemanager.data.models.Employee;
import africa.semicolon.employeemanager.data.models.EmployeeBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    Employee employee;

    @BeforeEach
    void setUp() {
        employee = new EmployeeBuilder()
                .setFirstName("Olatoye")
                .setLastName("Daramola").build();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void saveEmployeeToDatabaseTest() {
        assertThat(employee).isNotNull();

        employeeRepository.save(employee);
//        log.info("Employee saved -> {}", employee);

        assertThat(employee.getId()).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Olatoye");
        assertThat(employee.getLastName()).isEqualTo("Daramola");
        assertThat(employee.getEmail()).isEqualTo("o.daramola@email.com");

        employeeRepository.deleteAll();
    }

    @Test
    void findProductFromDatabaseTest() {
        employeeRepository.save(employee);
        Employee employee = employeeRepository.findById(1L).orElse(null);
//        log.info("Employee saved -> {}", employee);

        assertThat(employee).isNotNull();
        assertThat(employee.getId()).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Olatoye");
        assertThat(employee.getLastName()).isEqualTo("Daramola");
        assertThat(employee.getEmail()).isEqualTo("o.daramola@email.com");
    }

    @Test
    void findAllEmployeesTest() {
        employeeRepository.save(employee);
        List<Employee> employeeList = employeeRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(1);
    }

    @Test
    void findEmployeeByEmail() {
        employeeRepository.save(employee);
        Employee employee = employeeRepository.findByEmail("o.daramola@email.com").orElse(null);
//        log.info("Employee saved -> {}", employee);

        assertThat(employee).isNotNull();
        assertThat(employee.getId()).isNotNull();
//        assertThat(employee.getId()).isEqualTo(1L);
        assertThat(employee.getFirstName()).isEqualTo("Olatoye");
        assertThat(employee.getLastName()).isEqualTo("Daramola");
    }

    @Test
    void updateEmployeeDetailsTest() {
        employeeRepository.save(employee);
        Employee employee = employeeRepository.findByEmail("o.daramola@email.com").orElse(null);
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Olatoye");

        employee.setFirstName("David");
        employeeRepository.save(employee);

        assertThat(employee.getFirstName()).isEqualTo("David");
        assertThat(employee.getLastName()).isEqualTo("Daramola");
        assertThat(employee.getEmail()).isEqualTo("o.daramola@email.com");
    }
}