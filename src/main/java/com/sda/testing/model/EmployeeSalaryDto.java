package com.sda.testing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryDto {
    private String name;
    private Double salaryNet;
    private Double salaryGross;
}
