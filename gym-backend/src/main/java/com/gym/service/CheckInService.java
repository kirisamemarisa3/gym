package com.gym.service;

import com.gym.entity.CheckIn;
import com.gym.mapper.CheckInMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class CheckInService {

    @Resource
    private CheckInMapper checkInMapper;

    public Map<String, Object> addCheckIn(CheckIn checkIn) {
        Map<String, Object> resultMap = new HashMap<>();
        checkIn.setCheckDate(LocalDate.now());

        CheckIn checkInByMemberNo = checkInMapper.getCheckByMemberNo(checkIn);

        if (checkInByMemberNo != null) {
            resultMap.put("message", "今日已签到，请勿重复签到");
        } else {
            int result = checkInMapper.addCheckIn(checkIn);

            if (result > 0) {
                resultMap.put("code", 200);
                resultMap.put("message", "签到成功");
            } else {
                resultMap.put("code", 400);
                resultMap.put("message", "签到失败");
            }
        }
        return resultMap;
    }

}
