package com.gym.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.gym.entity.Common;
import com.gym.entity.Member;
import com.gym.service.MemberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 会员 控制层
 *

 */
@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    @RequestMapping(path = "/getAllMember")
    public List<Member> getAllMember(int page, int size) {
        return memberService.getMemberMapper(page, size);
    }

    @RequestMapping(path = "/addMember")
    public Map<String, Object> addMember(Member member) {
        return memberService.addMember(member);
    }

    @RequestMapping(path = "/deleteMember")
    public Map<String, Object> deleteMember(int memberNo) {
        return memberService.deleteMember(memberNo);
    }

    @RequestMapping(path = "/updateMember")
    public Map<String, Object> updateMember(Member member) {
        return memberService.updateMember(member);
    }

    @RequestMapping(path = "/updateMemberByMemberNo")
    public Map<String, Object> updateMemberByMemberNo(Member member) {
        return memberService.updateMemberByMemberNo(member);
    }

    @RequestMapping(path = "/totalMember")
    public Common totalMember() {
        return memberService.totalMember();
    }

    @RequestMapping(path = "/getMemberPassword")
    @CrossOrigin
    public Map<String, Object> getMemberPassword(String memberName, String memberPassword) {
        return memberService.getMemberPassword(memberName, memberPassword);
    }

    @RequestMapping(path = "/getByKeywordMember")
    public List<Member> getByKeywordMember(String keyWord, int page, int size) {
        return memberService.getByKeywordMember(keyWord, page, size);
    }

    @RequestMapping(path = "/totalMemberFuzzy")
    public Common totalMemberFuzzy(String keyWord) {
        return memberService.totalMemberFuzzy(keyWord);
    }

    @RequestMapping(path = "/getMemberIntegral")
    public double getMemberIntegral(int memberNo) {
        return memberService.getMemberIntegral(memberNo);
    }

    @ResponseBody
    @RequestMapping(path = "/getMemberChange")
    public double getMemberChange(int memberNo) {
        return memberService.getMemberChange(memberNo);
    }

    @RequestMapping(path = "/getMemberByMemberNo")
    public Member getMemberByMemberNo(Integer memberNo) {
        return memberService.getMemberByMemberNo(memberNo);
    }

    @RequestMapping(path = "/getTotalMoney")
    public Double getTotalMoney(Integer memberNo) {
        return memberService.getTotalMoney(memberNo);
    }

    @RequestMapping(path = "/getMemberPower")
    public double getMemberPower(Integer memberNo) {
        return memberService.getMemberPower(memberNo);
    }


    @RequestMapping(path = "/updateMemberChange")
    public double updateMemberChange(Integer memberNo) {
        return memberService.updateMemberChange(memberNo);
    }

    @RequestMapping(path = "/updateMemberIntegral")
    public double updateMemberIntegral(Double price, Integer memberNo) {
        return memberService.updateMemberIntegral(price, memberNo);
    }

    @RequestMapping(path = "/updateMemberChangeByMemberNo")
    public double updateMemberChangeByMemberNo(int memberNo, double coursePrice) {
        return memberService.updateMemberChangeByMemberNo(memberNo, coursePrice);
    }

    @RequestMapping(path = "/updateMemberPassword")
    public Map<String, Object> updateMemberPassword(String memberPhone, String memberPassword) {
        return memberService.updateMemberPassword(memberPhone, memberPassword);
    }

    @RequestMapping(path = "/updateMemberPower")
    public Map<String, Object> updateMemberPower(int memberPower, int memberNo) {
        return memberService.updateMemberPower(memberPower, memberNo);
    }

    /**
     * 导出接口
     */
    @GetMapping("/memberExport")
    public void export(HttpServletResponse response) throws Exception {
        // 从数据库查询出所有的数据
        List<Member> list = memberService.getAllMemberNoPage();
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("会员信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    /**
     * excel 导入
     */
    @RequestMapping("/memberImport")
    @CrossOrigin
    public Map<String, Object> imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Member> list = reader.readAll(Member.class);

        //上传的excel数据,保存到数据库中
        Map<String, Object> stringObjectMap = memberService.saveMemberBatch(list);
        return stringObjectMap;
    }

}
