package com.sda.testing.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private double salary;

    private EmployeeLevel level;

    @ManyToOne
    @JsonBackReference
    @ToString.Exclude
    private Team team;
}
