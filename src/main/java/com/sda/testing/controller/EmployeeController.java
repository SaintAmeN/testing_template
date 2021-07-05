package com.sda.testing.controller;

import com.sda.testing.model.Employee;
import com.sda.testing.repository.EmployeeRepository;
import com.sda.testing.service.CompanyService;
import com.sda.testing.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // TODO: poniżej dodaj metody kontrolera pozwalające na:
    //  - listowanie pracowników
    //  - listowanie pracowników po poziomach
    //  - szukanie pracowników po pensjach
    //  - daj podwyżkę
    //  - daj awans (promotion) pracownikowi

    // http://localhost:8080/employee/bysalary?from=80&to=10000
    @GetMapping("/bysalary")
    public List<Employee> findBySalary(@RequestParam(required = false) Double from,
                                       @RequestParam(required = false, defaultValue = ""+Double.MAX_VALUE) Double to) {
        return employeeService.findAllBySalary(from, to);
    }
}
