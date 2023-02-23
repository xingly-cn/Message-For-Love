package com.asugar.messageforlove.mapper.db;


import com.asugar.messageforlove.entity.vo.DbUpdateLog;

import java.util.List;


public interface DbUpdateLogDao {

	/**
	 * 查询所有执行脚本
	 * 
	 * @return
	 */
	List<DbUpdateLog> findAll();

	/**
	 * 查询所有的更新角标
	 * 
	 * @return
	 */
	List<Integer> findUpdateId();

	/**
	 * 根据id去更新脚本执行状态
	 * 
	 * @param id
	 */
	void updateById(int id);

	/**
	 * 执行sql 脚本
	 * 
	 * @param sql
	 */
	void execute(String sql);

	/**
	 * 添加脚本日志(批量)
	 * 
	 * @param list
	 */
	void addLog(List<DbUpdateLog> list);
}
