package africa.semicolon.employeemanager.data.repositories;

import africa.semicolon.employeemanager.data.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String Email);
}
