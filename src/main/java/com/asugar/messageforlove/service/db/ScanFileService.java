package com.asugar.messageforlove.service.db;


import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import com.asugar.messageforlove.entity.vo.DbVersion;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface ScanFileService {
	/**
	 * 初始化base 数据库
	 * 
	 */
	void initDb();

	/**
	 * @return
	 */
	List<DbVersion> scanResources();

	/**
	 * xml转对象
	 * 
	 * @return
	 */
	Map<Integer, DbUpdateLog> xmlToObject();

}
