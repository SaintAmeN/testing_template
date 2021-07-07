package com.sda.testing.service;

import com.sda.testing.model.Employee;
import com.sda.testing.model.EmployeeLevel;
import com.sda.testing.repository.EmployeeRepository;
import com.sda.testing.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("tests")
public class EmployeeMockServiceTests {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private TeamRepository teamRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        BDDMockito.given(employeeRepository.findAll()).willReturn(new ArrayList<>());
//        BDDMockito.given(employeeRepository.findAll()).willReturn(Arrays.asList(
//                Employee.builder().firstName("Jan").lastName("Nieszczesny").salary(20).level(EmployeeLevel.LEAD).build()));
        this.employeeService = new EmployeeService(employeeRepository, teamRepository);
    }

    @Test
    void employeeListIsInitializedEmptyTest() {
        assert_initiallyEmpty();
    }

    private void assert_initiallyEmpty() {
        List<Employee> list = employeeService.findAll();
        Assertions.assertEquals(0, list.size());
    }
}
