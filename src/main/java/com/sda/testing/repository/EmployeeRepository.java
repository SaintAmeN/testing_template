package com.sda.testing.repository;

import com.sda.testing.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findBySalaryBetween(Double salaryFrom, Double salaryTo);
}
