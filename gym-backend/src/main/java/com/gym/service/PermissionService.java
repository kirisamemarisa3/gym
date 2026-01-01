package com.gym.service;

import com.gym.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PermissionService {
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    // 从数据库获取当前角色的所有权限
    public Set<String> getPermissionsByRoleIds(List<Integer> roleIds) {
        return roleIds.stream()
                .flatMap(roleId -> getPermissionsByRoleId(roleId).stream())
                .collect(Collectors.toSet());
    }

    // 实际从数据库查询（MyBatis Mapper 示例）
    private List<String> getPermissionsByRoleId(Integer roleId) {
        // 这里调用数据库查询 role_permission 表
        // 返回该角色拥有的权限 code 列表
        return rolePermissionMapper.selectPermissionsByRoleId(roleId);
    }

    // 检查是否有指定权限
    public boolean hasPermission(List<Integer> roleIds, String permissionCode) {
        return getPermissionsByRoleIds(roleIds).contains(permissionCode);
    }
}
