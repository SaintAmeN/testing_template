package com.sda.testing.service;

import com.sda.testing.exception.InvalidOperation;
import com.sda.testing.model.Employee;
import com.sda.testing.model.EmployeeLevel;
import com.sda.testing.repository.EmployeeRepository;
import com.sda.testing.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    /**
     * List all employees.
     * @return list of employees.
     */
    public List<Employee> findAll(){
        return null;
    }

    /**
     * List all employees who are of given employee type.
     * @param level - type, can be null, then all employees should be returned.
     * @return list of employees with same EmployeeLevel as provided in parameter.
     */
    public List<Employee> findAllFrom(EmployeeLevel level){

        return null;
    }

    /**
     * Find employees by salary.
     * @param salaryFrom - lower bound of salary. Can be null, then should be ignored.
     * @param salaryTo - upper bound of salary. Can be null, then should be ignored.
     * @return list of employees which salary is between #salaryFrom and #salaryTo
     */
    public List<Employee> findAllBySalary(Double salaryFrom, Double salaryTo) {
        return employeeRepository.findAllBySalaryBetween(salaryFrom, salaryTo);
    }

    /**
     * Give raise to employee found by Id.
     * @param employeeId - employee identifier, can't be null.
     * @param salaryRaisePercent - percentage of salary raise. Value can't be lower than -5 and higher than 100.
     * @throws InvalidOperation - if values of percentage or employee id is not provided, exception will be thrown.
     */
    public void giveRaise(Long employeeId, double salaryRaisePercent) throws InvalidOperation {

    }

    /**
     * Promote employee. Allowed promotions are:
     * - WORKER -> LEAD
     * - LEAD -> MANAGER
     * - MANAGER -> EXECUTIVE
     * - SALES -> MANAGER
     * - ACCOUNTING -> MANAGER
     *
     * Independent employee cannot be promoted. Each promotion results in 5% net raise.
     * Promotion of manager to executive results in 3% raise.
     *
     * @param employeeId - identifier of promoted employee.
     * @throws InvalidOperation - if operation should not succeed, exception will be thrown.
     */
    public void givePromotion(Long employeeId) throws InvalidOperation{

    }
}
