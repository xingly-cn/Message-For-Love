package com.asugar.messageforlove.mapper.db;


import org.springframework.jdbc.datasource.init.DatabasePopulator;

public interface UpdateDao {

	/**
	 * 更新数据库
	 * 
	 * @return
	 */
	void execute(DatabasePopulator populator);

	/**
	 * 表是否存在
	 * 
	 * @param tableName
	 * @return true：存在，false：不存在
	 */
	boolean isTableExist(String tableName);

	/**
	 * 判断库是否被初始化过,已经初始化:true,未初始化:false
	 * 
	 * @param
	 */
	boolean showTablesCount();
}
