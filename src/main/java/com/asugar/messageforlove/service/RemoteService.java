package com.asugar.messageforlove.service;


import com.asugar.messageforlove.entity.Remote;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    Boolean deleteByPrimaryKey(Long id);

    Boolean insert(Remote record);

    int insertSelective(Remote record);

    Remote selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Remote record);

    Boolean updateByPrimaryKey(Remote record);

    IPage<Remote> getRemotePage(Integer page, Integer size, String name);
}
