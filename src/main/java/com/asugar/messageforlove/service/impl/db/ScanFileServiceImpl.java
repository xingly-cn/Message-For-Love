package com.asugar.messageforlove.service.impl.db;


import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import com.asugar.messageforlove.entity.vo.DbVersion;
import com.asugar.messageforlove.mapper.db.DbVersionDao;
import com.asugar.messageforlove.mapper.db.UpdateDao;
import com.asugar.messageforlove.service.db.ScanFileService;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScanFileServiceImpl implements ScanFileService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DbVersionDao dbVersionDao;

	@Autowired
	private UpdateDao updateDao;

	/**
	 * 初始化base 下面的 1.0_prod.sql
	 *
	 */
	@Override
	public void initDb() {
		boolean showTablesCount = updateDao.showTablesCount();
		boolean isTableExist = updateDao.isTableExist("init_table");
		try {
			if (!showTablesCount && !isTableExist) {

				PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
				// 初始化大脚本
				Resource resource = pmrpr.getResource("classpath:base/init_table.sql");
				ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
				populator.setSqlScriptEncoding("UTF-8");
				updateDao.execute(populator);
				populator = null;
				resource = null;

				resource = pmrpr.getResource("classpath:base/init_prod.sql");
				populator = new ResourceDatabasePopulator(resource);
				populator.setSqlScriptEncoding("UTF-8");
				logger.info("=====================【初始化数据库 】,prod.sql 文件执行中 ......");
				updateDao.execute(populator);
				logger.info("=====================脚本 prod.sql 执行成功!!!");
				dbVersionDao.updateInitTableById();
			}
		} catch (Exception e) {
			logger.error("【初始化数据库失败,系统已经退出,请检查初始化数据库文件后重启】:", e);
			System.out.println("Initialize database exception, program ready to exit .......");
			System.exit(0);
		}
	}

	/*
	 * 初始化数据库脚本记录文件步骤: 第一步:扫描项目中classes中update中存在的所有历史记录的脚本; 第二步:查找数据库脚本记录的历史版本;
	 * 第三步:比对项目中脚本更新与数据库记录的脚本,将近期新增的脚本追加记录到数据库中.
	 */
	@Override
	public List<DbVersion> scanResources() {

//		PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
//
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		Resource[] resources;// 脚本记录
//		try {
//			resources = pmrpr.getResources("classpath:update/**/*.sql");
//
//			resources = fileSort(resources);
//
//			for (Resource re : resources) {
//				String url = re.getURL().toString();
//				// 截取文件名
//				int lastIndexOf = url.lastIndexOf("/");
//				String fileName = url.substring(lastIndexOf + 1, url.length());
//
//				map.put(fileName, url);
//			}
//
//			// 项目中classes下的所有脚本文件
//			LinkedHashSet<String> sqlNameAll = Sets.newLinkedHashSet(map.keySet());
//			// 新建脚本记录表
//			Resource resource = pmrpr.getResource("classpath:init/db_version.sql");
//			ResourceDatabasePopulator populator = new ResourceDatabasePopulator(resource);
//			populator.setSqlScriptEncoding("UTF-8");
//			updateDao.execute(populator);
//			logger.info("=====================初始化脚本记录表成功!=====================");
//			// 数据库表中记录的所有脚本文件
//			List<String> listName = dbVersionDao.findName();
//
//			Set<String> result = new HashSet<String>(listName);
//
//			// classes下的脚本与数据库中记录的脚本的 差集(比对)
//			sqlNameAll.removeAll(result);
//
//			// logger.info("扫描项目中脚本与数据库脚本对比的结果");
//
//			for (String key : sqlNameAll) {
//				String url = map.get(key);
//				dbVersionDao.add(key, url);
//			}
//			logger.info("=====================本次更新新增数据库脚本记录文件 :{" + sqlNameAll.toString() + "},共 " + sqlNameAll.size() + "个!=====================");
//
//			// 需要执行的脚本的信息
//			List<DbVersion> updateData = dbVersionDao.findAllByStatus();
//
//			return updateData;
//
//		} catch (Exception e) {
//			logger.error("扫描脚本文件发生错误", e);
//		}
		return null;
	}

	@Override
	public Map<Integer, DbUpdateLog> xmlToObject() {

		Map<Integer, DbUpdateLog> map = new LinkedHashMap<>();
		try {
			SAXReader saxReader = new SAXReader();

			PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
			Resource[] resources = pmrpr.getResources("classpath:db_xml/**/*.xml");
			for (Resource resource : resources) {
				String versionName = resource.getFilename();
				Document document = saxReader.read(resource.getURL());
				Element root = document.getRootElement();
				document.clearContent();
				for (Object object : root.elements()) {
					Element element = (Element) object;
					String id = element.attribute("id").getValue();
					String table = element.attribute("table").getValue();
					String type = element.attribute("type").getValue();
					String author = element.attribute("author").getValue();
					String time = element.attribute("time").getValue();
					String remark = element.attribute("remark").getValue();
					String sql = element.getText();
					if (StringUtils.isEmpty(id) || StringUtils.isEmpty(table) || StringUtils.isEmpty(type) || StringUtils
							.isEmpty(author) || StringUtils.isEmpty(time) || StringUtils.isEmpty(sql)) {
						String info = ">>>>>>>>>>>无效的xml数据库脚本," + versionName + "中,参数{id=" + id + "},{table=" + table + "},{type=" + type + "},{author=" + author + "},{time=" + time + "},{remark=" + remark + "},{sql=" + sql + "}中不允许null或''>>>>>>>>>>>";
						logger.error(info, new DocumentException());
						continue;
					}
					DbUpdateLog dbUpdateLog = new DbUpdateLog();

					dbUpdateLog.setUpdateId(Integer.parseInt(id));
					dbUpdateLog.setSql(sql);
					dbUpdateLog.setVersion(versionName);
					dbUpdateLog.setTableName(table);
					dbUpdateLog.setType(type);
					dbUpdateLog.setAuthor(author);
					dbUpdateLog.setTime(time);
					dbUpdateLog.setRemark(remark);
					dbUpdateLog.setCreateTm(new Date());

					if (map.containsKey(Integer.parseInt(id))) {
						String info = ">>>>>>>>>>>无效的xml数据库脚本," + versionName + "中,文件中参数{id=" + id + "}重复,请测试联系脚本提供者{author:" + author + "}核查xml";
						logger.error(info, new DocumentException());
						continue;
					}
					map.put(Integer.parseInt(id), dbUpdateLog);
				}
			}
			
		} catch (Exception e) {
			logger.error("解析xml脚本错误", e);
		}
		return map;

	}

	private Resource[] fileSort(Resource[] resources) {
		// 排序
		List<Resource> list = new ArrayList<>(Arrays.asList(resources));
		list.sort((a1, a2) -> {
			String sp_1 = "";
			String sp_2 = "";
			try {
				String url_1 = a1.getURL().toString();
				int index_1 = url_1.lastIndexOf("/");
				String fileName_1 = url_1.substring(index_1 + 1, url_1.length());
				String[] split_1 = fileName_1.split("_");
				sp_1 = split_1[0] + split_1[1];

				String url_2 = a2.getURL().toString();
				int index_2 = url_2.lastIndexOf("/");
				String fileName_2 = url_2.substring(index_2 + 1, url_2.length());
				String[] split_2 = fileName_2.split("_");
				sp_2 = split_2[0] + split_2[1];
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sp_1.compareTo(sp_2);
		});
		return list.toArray(new Resource[list.size()]);
	}
}
