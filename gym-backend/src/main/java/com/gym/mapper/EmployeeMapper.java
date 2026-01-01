package com.gym.mapper;

import com.gym.entity.Common;
import com.gym.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工 仓库类
 *
 * @author: ShanZhu
 * @date: 2024-08-01
 */
@Mapper
@Repository
public interface EmployeeMapper {

    List<Employee> getAllEmployee(int page,int size);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    int deleteEmployee(int employeeNo);

    Common totalEmployee();

    List<Employee> getByKeywordEmployee(String keyWord,int page,int size);

    Common totalEmployeeFuzzy(String keyWord);
}
