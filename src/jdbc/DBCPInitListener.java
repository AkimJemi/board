package jdbc;

import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {
	// ServletContextListener : 웹 어플리케이션의 시작과 종료 될 때 특정한 기능을 실행
	// contextInitialized, contextDestroyed
	//
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String poolConfig = sce.getServletContext().getInitParameter("poolConfig"); // web.xml <context-param> -
																					// <param-name>
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
			// "키=값" 형식으로 구성된 문자열으로 프로퍼티를 로딩
			// poolCofig 컨텐스트 초기화 파라미터가 키값 형식을 가지므로 Properties 객체에 프로퍼티로 등록
			//
		} catch (Exception e) {
			throw new RuntimeException();
		}
		loadJDBCDriver(prop);
		initConnection(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("jdbcdriver");
		try {
			Class.forName(driverClass);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}

	private void initConnection(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, username, pw);

			PoolableConnectionFactory poolableConnFactroy = new PoolableConnectionFactory(connFactory, null);
			String validationQuery = prop.getProperty("validationQuery");
			if (validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactroy.setValidationQuery(validationQuery);
			}

			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(10001 * 601 * 5L);
			poolConfig.setTestWhileIdle(true);
			int minIdle = getIntProperty(prop, "minIdle", 5);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);

			GenericObjectPool<PoolableConnection> connentionPool = new GenericObjectPool<PoolableConnection>(
					poolableConnFactroy, poolConfig);
			poolableConnFactroy.setPool(connentionPool);

			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");

			String poolName = prop.getProperty("poolName");
			driver.registerPool(poolName, connentionPool);

		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

	private int getIntProperty(Properties prop, String propName, int ddefaultValue) {
		String value = prop.getProperty(propName);
		if (value == null)
			return ddefaultValue;
		return Integer.parseInt(value);
	}

}
