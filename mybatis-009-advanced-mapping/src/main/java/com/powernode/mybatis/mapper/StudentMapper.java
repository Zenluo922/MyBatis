package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Student;

public interface StudentMapper {
    Student selectByAssociation(Integer id);
}
