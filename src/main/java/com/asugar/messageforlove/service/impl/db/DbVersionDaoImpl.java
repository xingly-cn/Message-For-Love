package com.asugar.messageforlove.service.impl.db;


import com.asugar.messageforlove.entity.vo.DbVersion;
import com.asugar.messageforlove.mapper.db.DbVersionDao;
import com.asugar.messageforlove.mapper.db.DbVersionRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class DbVersionDaoImpl implements DbVersionDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<DbVersion> findAll() {
		String sql = "SELECT * FROM db_version ";
		List<DbVersion> list = jdbcTemplate.query(sql, new DbVersionRowMapper());
		return list;
	}

	@Override
	public List<String> findName() {
		String sql = "SELECT sql_name FROM db_version ";
		List<String> list = jdbcTemplate.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("sql_name");
			}

		});
		return list;
	}

	@Override
	public List<DbVersion> findAllByStatus() {
		String sql = "SELECT * FROM db_version where status =0";
		List<DbVersion> list = jdbcTemplate.query(sql, new DbVersionRowMapper());
		return list;
	}

	@Override
	public void add(String name, String url) {
		String sql = "insert into db_version (sql_name,sql_file_url) values (?,?)";
		jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				int i = 0;
				ps.setString(++i, name);
				ps.setString(++i, url);
			}
		});

	}

	@Override
	public void updateById(int id) {
		String sql = "UPDATE db_version set status = 1 WHERE id =" + id;
		jdbcTemplate.execute(sql);

	}

	@Override
	public void updateInitTableById() {
		String sql = "UPDATE init_table set status = 1 WHERE id =1";
		jdbcTemplate.execute(sql);

	}

}
