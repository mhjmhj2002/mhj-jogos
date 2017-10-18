package com.mhj.jogos.conf;

import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("prod")
@PropertySource("classpath:jdbc-prod.properties")
@EnableTransactionManagement
public class JPAProductionConfiguration {
	
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.dialect}")
	private String dialect;
	@Value("${jdbc.url}")
	private String jdbcURL;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;	

	@Bean
	public Properties additionalProperties() {
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", dialect);
        props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		return props;
	}

	@Bean
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(jdbcURL);
        dataSource.setDriverClassName(driverClassName);
		
		return dataSource;
	}
	
}









