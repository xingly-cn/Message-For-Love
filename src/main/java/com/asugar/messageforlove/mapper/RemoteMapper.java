package com.asugar.messageforlove.mapper;

import com.asugar.messageforlove.entity.Remote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 * @Author : 镜像
 * @create 2023/2/25 19:48
 */
@Mapper
public interface RemoteMapper extends BaseMapper<Remote> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Remote record);

    Remote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Remote record);

    int updateByPrimaryKey(Remote record);

}