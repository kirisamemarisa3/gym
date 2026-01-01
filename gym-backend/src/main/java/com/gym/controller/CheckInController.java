package com.gym.controller;

import com.gym.entity.CheckIn;
import com.gym.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 签到 控制层
 *

 */
@Controller
@ResponseBody
public class CheckInController {

    @Resource
    private CheckInService checkInService;

    @RequestMapping(path = "/addCheckIn")
    public Map<String, Object> addCheckIn(CheckIn checkIn) {
        return checkInService.addCheckIn(checkIn);
    }

}
