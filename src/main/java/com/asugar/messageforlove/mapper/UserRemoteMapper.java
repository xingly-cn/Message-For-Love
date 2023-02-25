package com.asugar.messageforlove.mapper;

import com.asugar.messageforlove.entity.UserRemote;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : 镜像
 * @create 2023/2/25 22:00
 */
@Mapper
public interface UserRemoteMapper extends BaseMapper<UserRemote> {

    int deleteByPrimaryKey(Long id);

    int insert(UserRemote record);

    int insertSelective(UserRemote record);

    UserRemote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRemote record);

    int updateByPrimaryKey(UserRemote record);

    void renew(UserRemote userRemote);
}