package com.sda.testing.controller;


import com.sda.testing.exception.InvalidOperation;
import com.sda.testing.model.Employee;
import com.sda.testing.model.EmployeeLevel;
import com.sda.testing.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("tests")
public class EmployeeIntegrationTests {
    private final EmployeeRepository employeeRepository;

    @LocalServerPort
    int randomServerPort;
    // do wywołań zapytań rest'owych
    // http://localhost:8080/CTX/students
    // /students
    private final TestRestTemplate testRestTemplate;

    @Autowired
    public EmployeeIntegrationTests(EmployeeRepository employeeRepository, TestRestTemplate testRestTemplate) {
        this.employeeRepository = employeeRepository;
        this.testRestTemplate = testRestTemplate;
    }

    @Nested
    class EmployeeIntegrationSalaryTests {
        private final Employee[] EMPLOYEES_INFO = new Employee[]{
                Employee.builder().firstName("Jan").lastName("Kowalski").salary(500.0).level(EmployeeLevel.WORKER).build(),
                Employee.builder().firstName("Kasia").lastName("Nowak").salary(2500.0).level(EmployeeLevel.WORKER).build(),
                Employee.builder().firstName("Iza").lastName("Leśniak").salary(5000.0).level(EmployeeLevel.MANAGER).build(),
        };

        @BeforeEach
        void setup() {
            employeeRepository.deleteAll();

            for (Employee employee : EMPLOYEES_INFO) {
                employeeRepository.save(employee);
            }
        }

        @Test
        void cantGiveRaiseWithoutEmployeeIdTest() {
            Map<String, String> params = new HashMap<>();
            params.put("from", "499.0");
            params.put("to", "501.0");
            ResponseEntity<Employee[]> responseEntity = testRestTemplate.getForEntity("http://localhost:"+randomServerPort+"/employee/bysalary?from={from}&to={to}", Employee[].class, params);
            Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            Employee[] responseBody = responseEntity.getBody();
            Assertions.assertNotNull(responseBody);
            Assertions.assertEquals(1, responseBody.length);
        }

        // tc 1 - brakuje from, to jest ustalone na 2501.0
        // tc 2 - brakuje to, jest from 800.0
        // tc 3 - 2000 - 3000
        // tc 4 - -200 - -100


    }

}
