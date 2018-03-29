package listaurant.dao;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class MySqlJdbcConfig extends JdbcConfig {

	@Resource(name = "mySqlDataSource")
	private DataSource dataSource;

	@Resource(name = "mySqlQueryProps")
	private Properties queryProps;

	@Resource(name = "mySqlTransactionManager")
	private DataSourceTransactionManager transactionMgr;

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	protected void setTransactionManager() {
		transactionMgr.setDataSource(dataSource);
	}

	@Override
	protected void setQueryProperties() {
		if (queryProps == null) {
			queryProps = new Properties();
		}
	}

	@Override
	protected void setJdbcTemplate() {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public String getQuery(String queryName) {
		return queryProps.getProperty(queryName);
	}

}
