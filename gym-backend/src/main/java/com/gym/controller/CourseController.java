package com.gym.controller;

import com.gym.entity.Common;
import com.gym.entity.Course;
import com.gym.service.CourseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 课程 控制层
 *

 */
@RestController
public class CourseController {

    @Resource
    private CourseService courseService;

    @RequestMapping(path = "/getAllCourse")
    public List<Course> getAllCourse(int page, int size) {
        return courseService.getAllCourse(page, size);
    }

    @RequestMapping(path = "/getAllCourseRegister")
    public List<Course> getAllCourseRegister() {
        return courseService.getAllCourseRegister();
    }

    @RequestMapping(path = "/addCourse")
    public Map<String, Object> addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @RequestMapping(path = "/updateCourse")
    public Map<String, Object> updateCourse(Course course) {
        return courseService.updateCourse(course);
    }

    @RequestMapping(path = "/deleteCourse")
    public Map<String, Object> deleteEmployee(int courseNo) {
        return courseService.deleteCourse(courseNo);
    }

    @RequestMapping(path = "/totalCourse")
    public Common totalCourse() {
        return courseService.totalCourse();
    }

    @RequestMapping(path = "/getByKeywordCourse")
    public List<Course> getByKeywordCourse(String keyWord, int page, int size) {
        return courseService.getByKeywordCourse(keyWord, page, size);
    }

    @RequestMapping(path = "/totalCourseFuzzy")
    public Common totalCourseFuzzy(String keyWord) {
        return courseService.totalCourseFuzzy(keyWord);
    }

}
