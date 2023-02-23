package com.asugar.messageforlove.service.impl.db;

import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import com.asugar.messageforlove.service.db.CommonService;
import com.asugar.messageforlove.service.db.ScanFileService;
import com.asugar.messageforlove.service.db.UpdateSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 自动执行脚本
 * 
 * @author fengbin
 */
@Service
public class CommonServiceImpl implements CommonService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ScanFileService scanFileService;

	@Autowired
	private UpdateSqlService updateSqlService;

	@Override
	public void execute() {
		scanFileService.initDb();
	}

	@Override
	public void readerXml() {
		// 解析合法的xml脚本
		Map<Integer, DbUpdateLog> xmlToObject = scanFileService.xmlToObject();

		// 比对数据库的脚本sql执行状态
		try {
			updateSqlService.update(xmlToObject);
		} catch (Exception e) {

			logger.info("自动化执行xml SQL脚本错误", e);
		}
	}
}
