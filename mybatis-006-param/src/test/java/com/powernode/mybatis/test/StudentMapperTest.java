package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.StudentMapper;
import com.powernode.mybatis.pojo.Student;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class StudentMapperTest {
    @Test
    public void testSelectByParams(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> list = mapper.selectByTwoParams("张无忌", 20);
        list.forEach(student -> System.out.println(student));
        sqlSession.close();
    }
    @Test
    public void testInsertByPojo(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("张无忌");
        student.setAge(20);
        Date date = new Date();
        student.setBirth(date);
        student.setHeight(180.0);
        student.setSex('男');
        int count = mapper.insertStudentByPojo(student);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testInsertByMap(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Map<String, Object> map = new HashMap<>();
        Date date = new Date();
        map.put("姓名","你好");
        map.put("年龄",18);
        map.put("性别",'男');
        map.put("生日",date);
        map.put("身高",18.9);
        int count = mapper.insertStudentByMap(map);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelectById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> students = mapper.selectById(1L);
        students.forEach(student -> System.out.println(student));
        sqlSession.close();
    }
}
