package com.gym.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 员工 实体
 *

 */
@Data
public class Employee {

    private Integer employeeNo;

    private String employeeName;

    private Integer employeeAge;

    private String employeeGender;

    private String employeePhone;

    private LocalDateTime employeeTime;

    private String employeeJob;

    private String employeeMessage;

}
