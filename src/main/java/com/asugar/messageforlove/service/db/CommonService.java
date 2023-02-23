package com.asugar.messageforlove.service.db;



public interface CommonService {
	/**
	 * 依次执行db 中的update下的sql脚本
	 */
	void execute();

	/**
	 * 依次执行xml 中的xml下的sql语句
	 */
	void readerXml();
}
