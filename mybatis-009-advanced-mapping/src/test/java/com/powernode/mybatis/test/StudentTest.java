package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.ClazzMapper;
import com.powernode.mybatis.mapper.StudentMapper;
import com.powernode.mybatis.pojo.Clazz;
import com.powernode.mybatis.pojo.Student;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class StudentTest {
    @Test
    public void selectByStep(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByStep1(1001);
        System.out.println(clazz);
        sqlSession.close();
    }
    @Test
    public void selectByCollection(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        ClazzMapper mapper = sqlSession.getMapper(ClazzMapper.class);
        Clazz clazz = mapper.selectByCollection(1002);
        System.out.println(clazz);
        sqlSession.close();
    }
    @Test
    public void selectByCid2(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectBySid(2);
        System.out.println(student);
        sqlSession.commit();
    }
    @Test
    public void selectBySid(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectBySid(2);
        System.out.println(student);
        sqlSession.commit();
    }
    @Test
    public void testStudentpAssociation(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectByAssociation(2);
        System.out.println(student);
        sqlSession.close();
    }
}
