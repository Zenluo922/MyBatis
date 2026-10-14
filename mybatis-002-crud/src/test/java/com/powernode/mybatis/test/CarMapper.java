package com.powernode.mybatis.test;

import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CarMapper {
    @Test
    public void testeSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Car> list = sqlSession.selectList("selectAll");
        list.forEach(car-> System.out.println(car));
        sqlSession.close();
    }
    @Test
    public void testSelect(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Object car = sqlSession.selectOne("selectById", 1);
        System.out.println(car);
        sqlSession.close();

    }
    @Test
    public void testUpdateCarByPOJO(){
        // 准备数据
        Car car = new Car();
        car.setId(3L);
        car.setCarNum("102");
        car.setBrand("比亚迪汉");
        car.setGuidePrice(30.23);
        car.setProduceTime("2018-09-10");
        car.setCarType("电车");
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 执行SQL语句
        int count = sqlSession.update("updateCarByPOJO", car);
        System.out.println("更新了几条记录：" + count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testDelete(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.delete("deleteById", 2);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testVarPojo(){
        Car car = new Car(null,"3303","奔驰",33.39,"2020-10-11","燃油车");
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.insert("insertCar",car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testCarMapper(){
        SqlSession sqlSession = SqlSessionUtil.openSession();

        Map<String, Object> map = new HashMap<>();
        map.put("k1","1111");
        map.put("k2","比亚迪汉");
        map.put("k3","10.0");
        map.put("k4","2020-11-11");
        map.put("k5","电车");

        int count = sqlSession.insert("insertCar", map);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
