package com.asugar.messageforlove.service.impl.db;


import com.asugar.messageforlove.entity.vo.DbUpdateLog;
import com.asugar.messageforlove.mapper.db.DbUpdateLogDao;
import com.asugar.messageforlove.mapper.db.DbUpdateLogRowMapper;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;


@Repository
public class DbUpdateLogDaoImpl implements DbUpdateLogDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DbUpdateLog> findAll() {
        String sql = "select * from db_update_log ";
        List<DbUpdateLog> list = jdbcTemplate.query(sql, new DbUpdateLogRowMapper());
        return list;
    }

    @Override
    public List<Integer> findUpdateId() {
        String sql = "select update_id from db_update_log ";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("update_id"));
    }

    @Override
    public void updateById(int id) {
        String sql = "update db_update_log set statu = 1 WHERE id =" + id;
        jdbcTemplate.execute(sql);
    }

    @Override
    public void execute(String sql) {
        jdbcTemplate.execute(sql);

    }

    @Override
    public void addLog(List<DbUpdateLog> list) {
        String sql = "insert into db_update_log (version,update_id,table_name,type,author,time,create_tm,statu) values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int index) throws SQLException {
                DbUpdateLog dbUpdateLog = list.get(index);
                ps.setString(1, dbUpdateLog.getVersion());
                ps.setInt(2, dbUpdateLog.getUpdateId());
                ps.setString(3, dbUpdateLog.getTableName());
                ps.setString(4, dbUpdateLog.getType());
                ps.setString(5, dbUpdateLog.getAuthor());
                ps.setString(6, dbUpdateLog.getTime());
                ps.setTimestamp(7, new Timestamp(new java.util.Date().getTime()));
                ps.setInt(8, dbUpdateLog.getStatu() == null ? 0 : dbUpdateLog.getStatu());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }

        });

    }

}
