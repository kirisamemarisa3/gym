package com.gym.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRoleMapper {
    List<Integer> getRoleIdsByMemberId(@Param("memberId") Integer memberId);
}
