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
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestHeader;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.Arrays;
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
        private final String BASE_URL = "http://localhost:" + randomServerPort + "/employee";
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
        void getEmployeeBySalaryWithRange() {
            final double rangeFrom = 499.0, rangeTo = 501.0;
            Map<String, String> params = new HashMap<String, String>() {{
                put("from", "" + rangeFrom);
                put("to", "" + rangeTo);
            }};
            Employee[] responseBody = requestForEmployees(params, "/bysalary?from={from}&to={to}");
            Assertions.assertNotNull(responseBody);
            assert_resultsAreCorrect(responseBody, rangeFrom, rangeTo);
        }

        @Test
        void getEmployeeBySalaryWithoutFrom() {
            final double rangeTo = 2501.0;
            Map<String, String> params = new HashMap<String, String>() {{
                put("to", "" + rangeTo);
            }};
            Employee[] responseBody = requestForEmployees(params, "/bysalary?to={to}");
            Assertions.assertNotNull(responseBody);
            assert_resultsAreCorrect(responseBody, null, rangeTo);
        }

        @Test
        void getEmployeeBySalaryWithoutTo() {
            final double rangeFrom = 800.0;
            Map<String, String> params = new HashMap<String, String>() {{
                put("from", "" + rangeFrom);
            }};
            Employee[] responseBody = requestForEmployees(params, "/bysalary?from={from}");
            Assertions.assertNotNull(responseBody);
            assert_resultsAreCorrect(responseBody, rangeFrom, null);
        }

        @Test
        void getEmployeeBySalaryWithHighRange() {
            final double rangeFrom = 2000.0, rangeTo = 3000.0;
            Map<String, String> params = new HashMap<String, String>() {{
                put("from", "" + rangeFrom);
                put("to", "" + rangeTo);
            }};
            Employee[] responseBody = requestForEmployees(params, "/bysalary?from={from}&to={to}");
            Assertions.assertNotNull(responseBody);
            assert_resultsAreCorrect(responseBody, rangeFrom, null);
        }

        @Test
        void getEmployeeBySalaryWithInvalidRange() {
            final double rangeFrom = -200.0, rangeTo = -100.0;
            Map<String, String> params = new HashMap<String, String>() {{
                put("from", "" + rangeFrom);
                put("to", "" + rangeTo);
            }};
            Employee[] responseBody = requestForEmployees(params, "/bysalary?from={from}&to={to}");
            Assertions.assertNotNull(responseBody);
            assert_resultsAreCorrect(responseBody, rangeFrom, rangeTo);
        }

        @Test
        void getEmployeeBySalaryWithoutParams() {
            Employee[] responseBody = requestForEmployees(null, "/bysalary?from={from}&to={to}");
            Assertions.assertNotNull(responseBody);
            assert_resultsAreCorrect(responseBody, null, null);
        }


        private void assert_resultsAreCorrect(Employee[] responseBody, Double rangeFrom, Double rangeTo) {
            long result = Arrays.asList(EMPLOYEES_INFO).stream()
                    .filter(employee -> rangeFrom == null || employee.getSalary() > rangeFrom)
                    .filter(employee -> rangeTo == null || employee.getSalary() < rangeTo)
                    .count();
            Assertions.assertEquals(result, responseBody.length);
        }

        private Employee[] requestForEmployees(Map<String, String> params, String url) {
            ResponseEntity<Employee[]> responseEntity = testRestTemplate.getForEntity(BASE_URL + url, Employee[].class, params);
            Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            return responseEntity.getBody();
        }
    }

    @Nested
    class EmployeeIntegrationRaiseTests {
        private final String BASE_URL = "http://localhost:" + randomServerPort + "/employee";
        private final Employee EMPLOYEE_TESTED = Employee.builder().firstName("Jan").lastName("Kowalski").salary(500.0).level(EmployeeLevel.WORKER).build();


        @BeforeEach
        void setup() {
            employeeRepository.deleteAll();
            employeeRepository.save(EMPLOYEE_TESTED);
        }

        @Test
        void giveRaiseWithoutEmployeeIdExpectErrorTest() {
            final double percentage = 5;
            Map<String, String> params = new HashMap<String, String>() {{
                put("percentage", "" + percentage);
            }};
            ResponseEntity<?> responseEntity = testRestTemplate.getForEntity(BASE_URL + "/raise?percentage={percentage}", Object.class, params);
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        }

    }
}
