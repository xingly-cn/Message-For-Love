package com.asugar.messageforlove.service.impl.db;

import com.asugar.messageforlove.mapper.db.UpdateDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
public class UpdateDaoImpl implements UpdateDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    private String tableSchema;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(DatabasePopulator populator) {
        DatabasePopulatorUtils.execute(populator, this.jdbcTemplate.getDataSource());
    }

    /**
     * 表是否存在
     *
     * @param tableName
     * @return true：存在，false：不存在
     */
    @Override
    public boolean isTableExist(String tableName) {

        tableSchema = jdbcUrl.substring(jdbcUrl.lastIndexOf("/") + 1, jdbcUrl.lastIndexOf("?"));

        String sql = "select count(*) from information_schema.tables where table_name = '" + tableName + "' and table_schema='" + tableSchema + "'";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != 0;
    }

    @Override
    public boolean showTablesCount() {
        tableSchema = jdbcUrl.substring(jdbcUrl.lastIndexOf("/") + 1, jdbcUrl.lastIndexOf("?"));
        String sql = "select count(1) from information_schema.tables where table_schema= '" + tableSchema + "'";

        int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count != 0;
	}

}
