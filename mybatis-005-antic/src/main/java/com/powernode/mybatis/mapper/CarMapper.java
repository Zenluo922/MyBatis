package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;

public interface CarMapper {
    int insertCarUseGeneratedKeys(Car car);
}
