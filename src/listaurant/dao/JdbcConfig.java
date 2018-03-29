package listaurant.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class JdbcConfig {

	// template method
	public final void init() {
		setTransactionManager();
		setQueryProperties();
		setJdbcTemplate();
	}

	protected abstract void setTransactionManager();

	protected abstract void setQueryProperties();

	protected abstract void setJdbcTemplate();
	
	public abstract NamedParameterJdbcTemplate getJdbcTemplate();
	
	public abstract String getQuery(String queryName);
}
