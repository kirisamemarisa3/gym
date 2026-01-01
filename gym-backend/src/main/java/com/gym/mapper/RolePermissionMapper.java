package com.gym.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RolePermissionMapper {
    /**
     * 根据角色ID获取权限CODE列表
     * @param roleId 角色ID
     * @return 权限CODE列表
     */
    List<String> selectPermissionsByRoleId(@Param("roleId") Integer roleId);
}
