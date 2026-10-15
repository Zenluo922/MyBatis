package com.powernode.mybatis.test;

import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.junit.Test;

public class CarMapperTest {
    @Test
    public void testInsertUseGeneratedKeys(){
        CarMapper mapper = SqlSessionUtil.openSession().getMapper(CarMapper.class);
        Car car = new Car();
        car.setCarNum("5262");
        car.setBrand("BYD汉");
        car.setGuidePrice(30.3);
        car.setProduceTime("2020-10-11");
        car.setCarType("新能源");
        mapper.insertCarUseGeneratedKeys(car);
        SqlSessionUtil.openSession().commit();
        System.out.println(car.getId());
    }
}
