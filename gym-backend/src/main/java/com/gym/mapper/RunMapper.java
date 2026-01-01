package com.gym.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gym.entity.Run;
import org.apache.ibatis.annotations.Mapper;

/**
 * 必须满足：
 * 1. 加@Mapper注解；
 * 2. 继承BaseMapper<Run>（泛型不能错，必须是Run）；
 * 3. 包名是com.gym.mapper；
 */
@Mapper
public interface RunMapper extends BaseMapper<Run> {
    // 无需写任何方法，BaseMapper已包含selectPage
}