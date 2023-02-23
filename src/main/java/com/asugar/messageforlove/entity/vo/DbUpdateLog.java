package com.asugar.messageforlove.entity.vo;

import java.util.Date;

/**
 * 频繁更换操作数据库自动执行脚本
 *
 */
public class DbUpdateLog {

	private Integer id;

	/**
	 * 项目版本(xml版本)
	 */
	private String version;

	/**
	 * 更新的sql 的唯一索引id
	 */
	private Integer updateId;

	/**
	 * 本次sql操作的表名
	 */
	private String tableName;
	/**
	 * 本次操作的类型: 创建:create;添加列:add;添加数据:insert
	 */
	private String type;
	/**
	 * 本次sql 提交的作者
	 */
	private String author;
	/**
	 * 脚本创建的时间描述 如 2019-08-16 15:00
	 */
	private String time;

	/**
	 * 脚本执行时间
	 */
	private Date createTm;

	/**
	 * 执行状态 : 0:执行失败,1:执行成功 默认0
	 */
	private Integer statu;
	/**
	 * 脚本
	 */
	private String sql;

	/**
	 * sql脚本 描述信息
	 * @return
	 */
	private String remark;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 项目版本(xml版本)
	 */
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 更新的sql 的唯一索引id
	 */
	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}

	/**
	 * 本次sql操作的表名
	 */
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 本次操作的类型: 创建:create;添加列:add;添加数据:insert
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 本次sql 提交的作者
	 */
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 脚本创建的时间描述 2019-08-16 15:00
	 */
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 脚本执行时间
	 */
	public Date getCreateTm() {
		return createTm;
	}

	public void setCreateTm(Date createTm) {
		this.createTm = createTm;
	}

	/**
	 * 执行状态 : 0:执行失败,1:执行成功 默认0
	 */
	public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
