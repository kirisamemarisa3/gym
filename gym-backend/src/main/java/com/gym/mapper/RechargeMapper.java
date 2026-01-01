package com.gym.mapper;

import com.gym.entity.Recharge;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 充值 仓库类
 *
 * @author: ShanZhu
 * @date: 2024-08-01
 */
@Mapper
@Repository
public interface RechargeMapper {

    List<Recharge> getRechargeByMemberNo(int memberNo);

    int addRechargeByMemberNo(Recharge recharge);
}
