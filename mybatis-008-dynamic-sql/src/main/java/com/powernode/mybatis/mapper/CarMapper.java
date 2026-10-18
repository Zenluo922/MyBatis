package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Objects;

public interface CarMapper {
    List<Car> selectIf(@Param("brand")String brand,@Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithWhere(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    List<Car> selectByMultiConditionWithTrim(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("carType") String carType);

    int updateWithSet(Car car);

    List<Car> selectWithChoose(@Param("brand") String brand, @Param("guidePrice") Double guidePrice, @Param("produceTime") String produceTime);

    int deleteBatchByForeach(@Param("ids") Long[] ids);
    int deleteBatchByForeachOr(@Param("ids") Long[] ids);

    int insertBatchByForeach(@Param("car") List<Car> list);
}
