package com.gym.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gym.entity.Run;
import com.gym.entity.Member; // 假设你有 Member 实体
import com.gym.mapper.RunMapper;
import com.gym.service.RunService;
import com.gym.service.MemberService; // 新增：用于查权限
import com.gym.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import com.gym.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Slf4j
@RestController
public class RunController {

    @Autowired
    private RunService runService;

    @Autowired
    private MemberService memberService; // 注入会员服务

    /**
     * 获取跑步机列表 —— 仅限权限为 5 的用户
     */
    @GetMapping("/run")
    public Result<Page<Run>> list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            HttpServletRequest request) {

        // 1. 从 Authorization 头获取 Token
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Result.error("未登录或 token 无效");
        }

        String token = authHeader.substring(7); // 去掉 "Bearer "

        // 2. 从 Token 中解析出 memberNo（用于鉴权）
        String memberNo = JwtUtil.getMemberNoFromToken(token);

        if (memberNo == null) {
            return Result.error("token 无效或已过期");
        }

        // 3. RBAC 鉴权：检查该会员是否有 "pre_run" 权限
        Boolean  h1 = memberService.hasPermission(memberNo, "pre_run");
        if (h1==false) {
            return Result.error("权限不足，无法访问跑步机信息");
        }

        Page<Run> pageInfo = new Page<>(page, size);
        Page<Run> result = runService.page(pageInfo); // ← 保持原样

        return Result.success(result);
    }

    // 预约跑步机
    @PostMapping("/run/{id}/reserve")
    public Result<?> reserveMachine(@PathVariable Long id) {
        runService.reserveMachine(id);
        return Result.success("预约成功");
    }
}