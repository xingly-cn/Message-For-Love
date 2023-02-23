package com.asugar.messageforlove.mapper.db;


import com.asugar.messageforlove.entity.vo.DbVersion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DbVersionRowMapper implements RowMapper<DbVersion> {

	@Override
	public DbVersion mapRow(ResultSet rs, int arg1) throws SQLException {
		
		DbVersion db = new DbVersion();

		db.setId(rs.getInt("id"));
		db.setSqlName(rs.getString("sql_name"));
		db.setSqlFileUrl(rs.getString("sql_file_url"));
		db.setStatus(rs.getInt("status"));
		db.setCreateTm(rs.getTimestamp("create_tm"));
		db.setExecuteTm(rs.getTimestamp("execute_tm"));
		return db;
	}

}
