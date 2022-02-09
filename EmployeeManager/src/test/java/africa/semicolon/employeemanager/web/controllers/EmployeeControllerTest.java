package africa.semicolon.employeemanager.web.controllers;

import africa.semicolon.employeemanager.data.dto.EmployeeDto;
import africa.semicolon.employeemanager.data.models.Employee;
import africa.semicolon.employeemanager.data.repositories.EmployeeRepository;
import africa.semicolon.employeemanager.service.employee.EmployeeService;
import africa.semicolon.employeemanager.web.exceptions.EmployeeManagerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
//    @Qualifier("initial")
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    ObjectMapper objectMapper;

    EmployeeDto employeeDto;
    EmployeeDto anotherEmployeeDto;

    @BeforeEach
    void setUp() throws EmployeeManagerException {
        objectMapper = new ObjectMapper();

        employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Amarachi");
        employeeDto.setLastName("Onyeukwu");
//        employeeRepository.save(employeeDto);

        anotherEmployeeDto = new EmployeeDto();
        anotherEmployeeDto.setFirstName("Israel");
        anotherEmployeeDto.setLastName("Fasina");
//        employeeemployeeRepository.createEmployee(anotherEmployeeDto);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    void createEmployee() throws Exception {
        String requestBody = objectMapper.writeValueAsString(employeeDto);
        mockMvc.perform(post("/api/employee/")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().is(201))
                .andDo(print());
    }

    @Test
    void findEmployeeById() throws Exception, EmployeeManagerException {
        Employee employee = employeeService.createEmployee(employeeDto);
        mockMvc.perform(get("/api/employee/1")
                        .contentType("application.json"))
                .andExpect(status().is(302))
                .andDo(print());
    }

    @Test
    void findAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employee")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void updateEmployeeDetails() {
    }

    @Test
    void deleteEmployee() throws Exception, EmployeeManagerException {
        Employee employee = employeeService.createEmployee(anotherEmployeeDto);
        mockMvc.perform(delete("/api/employee/1")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void deleteAllEmployees() throws EmployeeManagerException, Exception {
        Employee employee = employeeService.createEmployee(employeeDto);
        Employee anotherEmployee = employeeService.createEmployee(anotherEmployeeDto);

        mockMvc.perform(delete("/api/employee")
                        .contentType("application/json"))
                .andExpect(status().is(200))
                .andDo(print());
    }
}