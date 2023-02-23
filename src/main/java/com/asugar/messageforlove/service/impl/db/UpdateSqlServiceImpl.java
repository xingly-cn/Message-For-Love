package com.asugar.messageforlove.service.impl.db;


import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import com.asugar.messageforlove.entity.vo.DbVersion;
import com.asugar.messageforlove.mapper.db.DbUpdateLogDao;
import com.asugar.messageforlove.mapper.db.DbVersionDao;
import com.asugar.messageforlove.mapper.db.UpdateDao;
import com.asugar.messageforlove.service.db.UpdateSqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author fengbin
 */
@Service
public class UpdateSqlServiceImpl implements UpdateSqlService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DbVersionDao dbVersionDao;

	@Autowired
	private UpdateDao updateDao;

	@Autowired
	private DbUpdateLogDao dbUpdateLogDao;

	@Override
	@Transactional(value = "")
	public void update(List<DbVersion> list) {
//		String sqlName = null;
//
//		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
//
//		logger.info("=======程序自动执行脚本,当前需执行的脚本共有{" + list.toString() + "}需执行!!!=======");
//
//		for (DbVersion dbVersion : list) {
//			sqlName = dbVersion.getSqlName();
//			Resource resource = pmrpr.getResource("classpath:update/" + sqlName);
//			ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
//			populator.setSqlScriptEncoding("UTF-8");
//			try {
//				updateDao.execute(populator);
//				dbVersionDao.updateById(dbVersion.getId());
//				logger.info("执行当前脚本:[ " + sqlName + " ]成功!");
//			} catch (Exception e) {
//				logger.error("执行当前脚本 " + sqlName + " 错误,请检查此脚本是否语法错误,或该脚本人为手动已执行过:", e);
//			}
//		}

	}

	@Override
	public void update(Map<Integer, DbUpdateLog> map) {
		String author = "";
		Integer sqlId = null;
		String remark = "";
		List<DbUpdateLog> findAll = dbUpdateLogDao.findAll();
		// 查询所有的脚本角标
		List<Integer> indexList = dbUpdateLogDao.findUpdateId();
		Set<Integer> indexSet = new HashSet<Integer>(indexList);
		Set<Integer> indexXml = map.keySet();
		if (indexList.size() != 0) {
			// 脚本与数据库的差集
			for (DbUpdateLog dbUpdateLog : findAll) {
				if (map.containsKey(dbUpdateLog.getUpdateId())) {
					if (dbUpdateLog.getStatu() == 0) {
						try {
							// 去执行这个sql:
							DbUpdateLog obj = map.get(dbUpdateLog.getUpdateId());
							String sql = obj.getSql();
							sqlId = obj.getUpdateId();
							author = obj.getAuthor();
							remark = obj.getRemark();
//							logger.info("初始化SQL执行 ,[author='" + author + "',sqlId='" + sqlId + "',sql='" + sql + "']");
							dbUpdateLogDao.execute(sql);
							dbUpdateLogDao.updateById(dbUpdateLog.getId());
							logger.info("初始化SQL执行sqlId= "+sqlId+",成功 ,[author='" + author + "]");

						} catch (Exception e) {
							logger.error("初始化SQL脚本异常,请联系脚本提供者(author='" + author + "')核查脚本:id='" + sqlId + "',脚本描述:"+remark,e);
							continue;
						}
					}
				}
			}

		}
		// 脚本全集
		indexXml.removeAll(indexSet);
		List<DbUpdateLog> list = new ArrayList<>();
		for (Integer index : indexXml) {
			// 执行xml 的数据库脚本
			DbUpdateLog dbUpdateLog = map.get(index);
			String sql = dbUpdateLog.getSql();
			sqlId = dbUpdateLog.getUpdateId();
			author = dbUpdateLog.getAuthor();
			remark = dbUpdateLog.getRemark();
			//logger.info("初始化SQL执行 ,[author='" + author + "',sqlId='" + sqlId + "',sql='" + sql + "']");
			try {
				dbUpdateLogDao.execute(sql);
				dbUpdateLog.setStatu(1);
				list.add(dbUpdateLog);
				logger.info("初始化SQL执行sqlId= "+sqlId+",成功 ,[author='" + author + "]");
			} catch (Exception e) {
				logger.error("初始化SQL脚本异常,请联系脚本提供者(author='" + author + "')核查脚本:id='" + sqlId + "',脚本描述:"+remark, e);
				dbUpdateLog.setStatu(0);
				list.add(dbUpdateLog);
				continue;
			}
		}
		if (list.size() > 0)
			dbUpdateLogDao.addLog(list);

	}

}
