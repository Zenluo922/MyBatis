package com.powernode.mybatis.mapper;

import com.powernode.mybatis.pojo.Clazz;
import org.apache.ibatis.annotations.Param;

public interface ClazzMapper {
    Clazz selectByCid(@Param("cid")Integer cid);

    Clazz selectByCollection(Integer cid);

    Clazz selectByStep1(Integer cid);
}
