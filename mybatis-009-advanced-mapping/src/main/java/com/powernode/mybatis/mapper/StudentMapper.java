package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Student;

import java.util.List;

public interface StudentMapper {

    Student selectBySid(Integer id);
    Student selectByAssociation(Integer id);

    List<Student> selectByStep2(Integer cid);
}
