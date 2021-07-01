package com.sda.testing.controller;

import com.sda.testing.repository.EmployeeRepository;
import com.sda.testing.service.CompanyService;
import com.sda.testing.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // TODO: poniżej dodaj metody kontrolera pozwalające na:
    //  - listowanie pracowników
    //  - listowanie pracowników po poziomach
    //  - szukanie pracowników po pensjach
    //  - daj podwyżkę
    //  - daj awans (promotion) pracownikowi
}
