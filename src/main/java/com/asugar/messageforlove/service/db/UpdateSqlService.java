package com.asugar.messageforlove.service.db;


import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import com.asugar.messageforlove.entity.vo.DbVersion;

import java.util.List;
import java.util.Map;

public interface UpdateSqlService {
	/**
	 * @return
	 */
	void update(List<DbVersion> list);

	/**
	 * @param map
	 */
	void update(Map<Integer, DbUpdateLog> map);
}
