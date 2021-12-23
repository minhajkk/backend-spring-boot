package com.bsf.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @NotEmpty
    String fullName;

    @Column(columnDefinition = "varchar(50) not null", unique = true)
    @Email
    String email;
    String mobile;

    String password;

    BigDecimal salary;
    @Min(1) @Max(31)
    Integer salaryDay;
}
