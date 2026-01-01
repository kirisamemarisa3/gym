package com.gym.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gym.entity.Run;
import com.gym.mapper.RunMapper;
import com.gym.service.RunService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RunServiceImpl extends ServiceImpl<RunMapper, Run> implements RunService {


    @Override
    public void reserveMachine(Long id) {
        Run run = this.getById(id);
        if (run == null) {
            throw new RuntimeException("跑步机不存在");
        }
        if (run.getStatus() != 1) {
            throw new RuntimeException("该跑步机已被预约或正在使用");
        }
        // 更新状态为“使用中”
        run.setStatus(2);
        this.updateById(run);

    }
}
