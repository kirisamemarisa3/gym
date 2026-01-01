package com.gym.service;

import com.gym.entity.Admin;
import com.gym.mapper.AdminMapper;
import com.gym.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员 服务类
 *

 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    public Map<String,Object> getAdminPassword(String adminAccount,String adminPassword){
        Map<String,Object> resultMap = new HashMap<>();
        Admin result = adminMapper.getAdminPassword(adminAccount,adminPassword);

        if(result != null){
            result.setToken(JwtUtil.createToken());
            resultMap.put("token",result.getToken());
            resultMap.put("adminAccount",adminAccount);
            resultMap.put("code",200);
            resultMap.put("message","登录成功");

        }else{
            resultMap.put("code",400);
            resultMap.put("message","登录失败");
            resultMap.put("token",null);
        }
        return resultMap;
    }

}
