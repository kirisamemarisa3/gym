package com.gym.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gym.entity.Common;
import com.gym.entity.Course;
import com.gym.mapper.CourseMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 课程 服务类
 *

 */
@Service
public class CourseService {

    @Resource
    private CourseMapper courseMapper;




    public List<Course> getAllCourse(int page, int size) {
        return courseMapper.getAllCourse(page, size);
    }

    public List<Course> getAllCourseRegister() {
//        // 1. 生成缓存Key（包含分页参数）
//        String cacheKey = CACHE_KEY_PREFIX + page + ":" + size;
//
//        // 2. 先尝试从缓存获取
//        List<Course> cachedCourses = getFromCache(cacheKey);
//        if (CollectionUtils.isNotEmpty(cachedCourses)) {
//            return cachedCourses;
//        }
//
//        // 3. 缓存未命中，查询数据库
//        List<Course> courses = courseMapper.getAllCourse(page, size);
//
//        // 4. 处理空结果（避免缓存穿透）
//        if (CollectionUtils.isEmpty(courses)) {
//            // 缓存空列表（避免频繁查询数据库）
//            setEmptyCache(cacheKey);
//            return Collections.emptyList();
//        }
//
//        // 5. 将结果存入缓存
//        saveToCache(cacheKey, courses);
//
//

        return courseMapper.getAllCourseRegister();
    }

    public Map<String, Object> addCourse(Course course) {
        Map<String, Object> resultMap = new HashMap<>();

        DateTime courseDateTime = DateUtil.parse(course.getCourseTime());
        String courseTime = DateUtil.format(courseDateTime,DatePattern.NORM_DATETIME_PATTERN);
        course.setCourseTime(courseTime);

        int result = courseMapper.addCourse(course);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "添加成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "添加失败");
        }
        return resultMap;
    }

    public Map<String, Object> updateCourse(Course course) {
        Map<String, Object> resultMap = new HashMap<>();

        DateTime courseDateTime = DateUtil.parse(course.getCourseTime());
        String courseTime = DateUtil.format(courseDateTime,DatePattern.NORM_DATETIME_PATTERN);
        course.setCourseTime(courseTime);

        int result = courseMapper.updateCourse(course);
        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "修改成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "修改失败");
        }

        return resultMap;
    }

    public Map<String, Object> deleteCourse(int courseNo) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = courseMapper.deleteCourse(courseNo);

        if (result > 0) {
            resultMap.put("code", 200);
            resultMap.put("message", "删除成功");
        } else {
            resultMap.put("code", 400);
            resultMap.put("message", "删除失败");
        }

        return resultMap;
    }

    public Common totalCourse() {
        return courseMapper.totalCourse();
    }

    public List<Course> getByKeywordCourse(String keyWord, int page, int size) {
        return courseMapper.getByKeywordCourse(keyWord, page, size);
    }

    public Common totalCourseFuzzy(String keyWord) {
        return courseMapper.totalCourseFuzzy(keyWord);
    }

}


