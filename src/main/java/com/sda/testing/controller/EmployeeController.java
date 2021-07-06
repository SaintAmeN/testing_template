package com.sda.testing.controller;

import com.sda.testing.exception.InvalidOperation;
import com.sda.testing.model.Employee;
import com.sda.testing.model.EmployeeLevel;
import com.sda.testing.repository.EmployeeRepository;
import com.sda.testing.service.CompanyService;
import com.sda.testing.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    //  - listowanie pracowników
    @GetMapping("")
    public List<Employee> all() {
        return employeeService.findAll();
    }

    //  - listowanie pracowników po poziomach
    @GetMapping("/level")
    public List<Employee> allByLevel(@RequestParam(required = false) EmployeeLevel level) {
        return employeeService.findAllFrom(level);
    }

    //  - szukanie pracowników po pensjach
    // http://localhost:8080/employee/bysalary?from=80&to=10000
    @GetMapping("/bysalary")
    public List<Employee> findBySalary(@RequestParam(name = "from", required = false, defaultValue = "-1") Double from,
                                       @RequestParam(name = "to", required = false, defaultValue = ""+Double.MAX_VALUE) Double to) {
        log.info("Params: " + from + " to: " + to);
        return employeeService.findAllBySalary(from, to);
    }

    //  - daj podwyżkę
    //    @PatchMapping("/bysalary")
    @GetMapping("/raise")
    public void giveRaise(@RequestParam() Long employeeId,
                                       @RequestParam double percentage) throws InvalidOperation {
        employeeService.giveRaise(employeeId, percentage);
    }

    //  - daj awans (promotion) pracownikowi
    //    @PatchMapping("/promotion")
    @GetMapping("/promotion")
    public void findBySalary(@RequestParam() Long employeeId) throws InvalidOperation {
        employeeService.givePromotion(employeeId);
    }
}
