package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    List<Student> selectById(Long id);

    int insertStudentByMap(Map<String,Object> map);

    int insertStudentByPojo(Student student);

    List<Student> selectByTwoParams(@Param("name")String name,@Param("age")int age);
}
