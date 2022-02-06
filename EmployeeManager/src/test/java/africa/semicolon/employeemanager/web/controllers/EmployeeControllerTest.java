package africa.semicolon.employeemanager.web.controllers;

import africa.semicolon.employeemanager.data.dto.EmployeeDto;
import africa.semicolon.employeemanager.service.employee.EmployeeService;
import africa.semicolon.employeemanager.web.exceptions.EmployeeManagerException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    @Qualifier("initial")
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
        employeeService.createEmployee(employeeDto);

        anotherEmployeeDto = new EmployeeDto();
        anotherEmployeeDto.setFirstName("Israel");
        anotherEmployeeDto.setLastName("Fasina");
        employeeService.createEmployee(anotherEmployeeDto);
    }

    @AfterEach
    void tearDown() {
        employeeService.deleteAllEmployees();
    }

    @Test
    void createEmployee() throws Exception {
        String requestBody = objectMapper.writeValueAsString(employeeDto);
        mockMvc.perform(post("/api/product")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    void findEmployeeById() {
    }

    @Test
    void findAllEmployees() {
    }

    @Test
    void updateEmployeeDetails() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void deleteAllEmployees() {
    }
}