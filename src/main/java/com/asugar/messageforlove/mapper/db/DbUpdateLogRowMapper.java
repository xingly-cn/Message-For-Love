package com.asugar.messageforlove.mapper.db;

import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DbUpdateLogRowMapper implements RowMapper<DbUpdateLog> {

	@Override
	public DbUpdateLog mapRow(ResultSet rs, int arg1) throws SQLException {

		DbUpdateLog db = new DbUpdateLog();

		db.setId(rs.getInt("id"));
		db.setVersion(rs.getString("version"));
		db.setUpdateId(rs.getInt("update_id"));
		db.setTableName(rs.getString("table_name"));
		db.setType(rs.getString("type"));
		db.setAuthor(rs.getString("author"));
		db.setTime(rs.getString("time"));
		db.setVersion(rs.getString("version"));
		db.setCreateTm(rs.getTimestamp("create_tm"));
		db.setStatu(rs.getInt("statu"));
		return db;
	}

}
