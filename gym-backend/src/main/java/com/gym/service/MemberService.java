package com.gym.service;

import com.gym.entity.Common;
import com.gym.entity.Member;
import com.gym.mapper.MemberMapper;
import com.gym.mapper.RegisterMapper;
import com.gym.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private RegisterMapper registerMapper;

    public List<Member> getMemberMapper(int page, int size) {
        return memberMapper.getAllMember(page, size);
    }

    public List<Member> getAllMemberNoPage() {
        return memberMapper.getAllMemberNoPage();
    }

    public Map<String, Object> saveMemberBatch(List<Member> list) {
        Map<String, Object> resultMap = new HashMap<>();

        int result = memberMapper.saveMemberBatch(list);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "导入成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "导入失败");
        }

        return resultMap;
    }

    public Map<String, Object> addMember(Member member) {

        Map<String, Object> resultMap = new HashMap<>();


        /*随机生成用户名*/
        int Username = java.util.UUID.randomUUID().toString().hashCode();
        if (Username < 0) {
            Username = -Username;
        }
        // 0 代表前面补充0
        // 10 代表长度为10
        // d 代表参数为正数型
        String format = String.format("%010d", Username).substring(0, 10);
        member.setMemberUsername(format);

        /*默认密码*/
        member.setMemberPassword("123456");
        member.setCardTime(LocalDateTime.now());

        /*新增用户时将购买时间=剩余时间*/
        member.setCardNextClass(member.getCardClass());

        int result = memberMapper.addMember(member);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "创建成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "创建失败");
        }

        return resultMap;
    }

    public Map<String, Object> deleteMember(int memberNo) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = memberMapper.deleteMember(memberNo);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "删除成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "删除失败");
        }
        return resultMap;
    }

    public Map<String, Object> updateMember(Member member) {

        Map<String, Object> resultMap = new HashMap<>();


        int result = memberMapper.updateMember(member);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "修改成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "修改失败");
        }
        return resultMap;
    }

    public Map<String, Object> updateMemberByMemberNo(Member member) {

        Map<String, Object> resultMap = new HashMap<>();


        int result = memberMapper.updateMemberByMemberNo(member);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "修改成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "修改失败");
        }
        return resultMap;
    }

    public Common totalMember() {
        return memberMapper.totalMember();
    }

    public List<Member> getByKeywordMember(String keyWord, int page, int size) {
        return memberMapper.getByKeywordMember(keyWord, page, size);
    }

    public Common totalMemberFuzzy(String keyWord) {
        return memberMapper.totalMemberFuzzy(keyWord);
    }


    public Map<String, Object> getMemberPassword(String memberName, String memberPassword) {
        Map<String, Object> resultMap = new HashMap<>();
        Member result = memberMapper.getMemberPassword(memberName, memberPassword);

        if (result != null) {

            String token = JwtUtil.createTokenToMember(String.valueOf(result.getMemberNo()));

            resultMap.put("token", token);
            resultMap.put("memberNo", result.getMemberNo());
            resultMap.put("memberPhone", result.getMemberPhone());
            resultMap.put("code", 200);
            resultMap.put("message", "登录成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "账号或密码错误");
        }
        return resultMap;
    }

    public Member getMemberByMemberNo(int memberNo) {
        return memberMapper.getMemberByMemberNo(memberNo);
    }

    public double getMemberIntegral(int memberNo) {
        return memberMapper.getMemberIntegral(memberNo);
    }


    public double getMemberPower(int memberNo) {
        return memberMapper.getMemberPower(memberNo);
    }

    public Double getMemberChange(int memberNo) {
        return memberMapper.getMemberChange(memberNo);
    }

    public Double getTotalMoney(int memberNo) {
        Double totalMoney = memberMapper.getTotalMoney(memberNo);
        if (totalMoney == null) {
            totalMoney = Double.valueOf(0);
        }
        return totalMoney;
    }


    public int updateMemberChange(int memberNo) {
        //获取购物总额
        Double totalBuy = registerMapper.getTotalBuyByMemberNo(memberNo);
        if (totalBuy == null) {
            totalBuy = Double.valueOf(0);
        }
        return memberMapper.updateMemberChange(memberNo, totalBuy);
    }

    public int updateMemberChangeByMemberNo(int memberNo, double coursePrice) {
        return memberMapper.updateMemberChangeByMemberNo(memberNo, coursePrice);
    }

    public int updateMemberIntegral(double price, int memberNo) {
        return memberMapper.updateMemberIntegral(price, memberNo);
    }

    public Map<String, Object> updateMemberPassword(String memberPhone, String memberPassword) {
        Map<String, Object> resultMap = new HashMap<>();

        int result = memberMapper.updateMemberPassword(memberPhone, memberPassword);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "密码修改成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "密码修改失败");
        }
        return resultMap;
    }

    public Map<String, Object> updateMemberPower(int memberPower, int memberNo) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = memberMapper.updateMemberPower(memberPower, memberNo);
        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "兑换成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "兑换失败");
        }
        return resultMap;
    }



    /**
     * 判断用户是否有指定权限
     */
    public boolean hasPermission(Integer memberNo, String permissionCode) {
        if (memberNo == null || permissionCode == null) {
            return false;
        }
        return memberMapper.hasPermission(memberNo, permissionCode);
    }

    // 如果前端传的是 String memberNo
    public boolean hasPermission(String memberNoStr, String permissionCode) {
        try {
            Integer no = Integer.parseInt(memberNoStr);
            return hasPermission(no,permissionCode);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
