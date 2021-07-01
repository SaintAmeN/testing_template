package com.sda.testing.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sda.testing.model.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDto {
    private String name;
    private List<EmployeeDto> workers;
    private EmployeeDto manager;
    private EmployeeDto lead;
}
