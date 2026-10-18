package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarTest {
    @Test
    public void selectIf(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> list = mapper.selectIf("丰田",30.0,"氢能源");
        list.forEach(car -> System.out.println(car));
        sqlSession.close();
    }
    @Test
    public void selectByMultiConditionWithWhereTest(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> list = mapper.selectByMultiConditionWithWhere("丰田", 20.0, "燃油车");
        list.forEach(car -> System.out.println(car));
        sqlSession.close();
    }
    @Test
    public void testSelectByMultiConditionWithTrim(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> cars = mapper.selectByMultiConditionWithTrim("丰田", 20.0, "");
        System.out.println(cars);
        sqlSession.close();
    }
    @Test
    public void testUpdateWithSet(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car = new Car(1L,"1001","丰田霸道2",10.0,"",null);
        int count = mapper.updateWithSet(car);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void testSelectWithChoose(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        List<Car> list = mapper.selectWithChoose("", null, "");
        System.out.println(list);
    }
    @Test
    public void deleteBatchByForeach(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int count = mapper.deleteBatchByForeach(new Long[]{1L, 2L});
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void insertBatchByForeach(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        Car car1 = new Car(null, "2001", "兰博基尼", 100.0, "1998-10-11", "燃油车");
        Car car2 = new Car(null, "2001", "兰博基尼", 100.0, "1998-10-11", "燃油车");
        Car car3 = new Car(null, "2001", "兰博基尼", 100.0, "1998-10-11", "燃油车");
        List<Car> list = new ArrayList();
        list.add(car1);
        list.add(car2);
        list.add(car3);
        int count = mapper.insertBatchByForeach(list);
        System.out.println(count);
        sqlSession.commit();
        sqlSession.close();
    }
}
