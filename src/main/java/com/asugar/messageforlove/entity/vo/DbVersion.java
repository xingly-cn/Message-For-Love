package com.asugar.messageforlove.entity.vo;

import java.util.Date;

public class DbVersion {

	private int id;

	/**
	 * 脚本文件名
 	 */
	private String sqlName;

	/**
	 * 脚本路径,绝对路径
 	 */
	private String sqlFileUrl;

	/**
	 * 脚本执行状态 , 0:未执行;1已执行;默认0
 	 */
	private int status;

	/**
	 * 脚本创建日期
 	 */

	private Date createTm;

	/**
	 * 脚本执行日期
 	 */
	private Date executeTm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getSqlFileUrl() {
		return sqlFileUrl;
	}

	public void setSqlFileUrl(String sqlFileUrl) {
		this.sqlFileUrl = sqlFileUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	public Date getExecuteTm() {
		return executeTm;
	}

	public void setExecuteTm(Date executeTm) {
		this.executeTm = executeTm;
	}

	@Override
	public String toString() {
		return sqlName;
	}

}
