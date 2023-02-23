package com.asugar.messageforlove.mapper.db;


import com.asugar.messageforlove.entity.vo.DbVersion;

import java.util.List;


/**
 * @author fengbin
 */
public interface DbVersionDao {

	/**
	 * 查询所有脚本
	 * 
	 * @return
	 */
	List<DbVersion> findAll();

	/**
	 * 查询所有脚本名
	 * 
	 * @return
	 */
	List<String> findName();

	/**
	 * 添加
	 * 
	 * @return
	 */
	void add(String name, String url);

	/**
	 * 查询所有需要执行的脚本
	 * 
	 * @return
	 */
	List<DbVersion> findAllByStatus();

	/**
	 * 查询所有需要执行的脚本
	 * 
	 * @return
	 */
	void updateById(int id);

	/**
	 * 更改状态
	 * 
	 */
	void updateInitTableById();
}
