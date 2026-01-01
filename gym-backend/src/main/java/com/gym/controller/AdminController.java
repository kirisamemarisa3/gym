package com.gym.controller;

import com.gym.service.AdminService;
import com.gym.utils.JwtUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 管理员 控制层
 *

 */
@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping(path = "/getAdminPassword")
    @CrossOrigin
    public Map<String, Object> getAdminPassword(String adminAccount, String adminPassword) {
        return adminService.getAdminPassword(adminAccount, adminPassword);
    }

    @RequestMapping(path = "/checkToken")
    @CrossOrigin
    public Boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return JwtUtil.checkToken(token);
    }

}
