package com.gym.entity;

import lombok.Data;

import java.util.Date;

/**
 * 课程 实体
 *

 */
@Data
public class Course {

    private Integer courseNo;

    private String courseName;

    private String courseTime;

    private String courseDuration;

    private double coursePrice;

    private String courseDesc;

    private int courseIntegral;

    private Integer employeeNo;

    private Integer managerNo;

    private String employeeNameCoach;

    private String employeePhoneCoach;

    private String employeeNameManager;

    private String employeePhoneManager;

}
