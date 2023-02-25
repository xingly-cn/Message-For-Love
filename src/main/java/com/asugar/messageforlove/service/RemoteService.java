package com.asugar.messageforlove.service;


import com.asugar.messageforlove.entity.Remote;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 肖念昕
 * @since 2022-04-11
 */
public interface RemoteService extends IService<Remote> {

    int deleteByPrimaryKey(Long id);

    int insert(Remote record);

    int insertSelective(Remote record);

    Remote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Remote record);

    int updateByPrimaryKey(Remote record);
}
