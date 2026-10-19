package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void testInsert(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.insert(new Car(null,"6666","丰田",32.0,"2020-11-11","燃油车"));
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDelete(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int delete = mapper.Delete(8L);
        System.out.println(delete);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.Update(new Car(1L,"1234","宝马",38.0,"2020-11-11","燃油车"));
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = mapper.Select(3L);
        System.out.println(car);
        sqlSession.commit();
        sqlSession.close();
    }
}
