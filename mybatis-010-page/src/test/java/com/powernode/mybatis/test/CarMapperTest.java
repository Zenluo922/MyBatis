package com.powernode.mybatis.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.powernode.mybatis.mapper.CarMapper;
import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CarMapperTest {
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        CarMapper mapper = sqlSession.getMapper(CarMapper.class);
        int pageNum = 2;
        int pageSize = 3;
        PageHelper.startPage(pageNum,pageSize);
        List<Car> list = mapper.selectAll();
        PageInfo<Car> carPageInfo = new PageInfo<>(list,3);
        list.forEach(car -> System.out.println(car));
        sqlSession.close();
    }
}
