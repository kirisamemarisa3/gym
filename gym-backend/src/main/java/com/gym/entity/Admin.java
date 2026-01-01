package com.gym.entity;

import lombok.Data;

/**
 * 管理员 实体
 *

 */
@Data
public class Admin {

    private Integer adminNo;

    private String adminAccount;

    private String adminPassword;

    private String token;
}
