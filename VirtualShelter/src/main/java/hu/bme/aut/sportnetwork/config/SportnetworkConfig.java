package hu.bme.aut.sportnetwork.config;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "hu.bme.aut.sportnetwork.dal")
@EnableTransactionManagement
public class SportnetworkConfig extends Neo4jConfiguration {

	@Override
	@Bean
	public SessionFactory getSessionFactory() {
		return new SessionFactory(getConfig(), "hu.bme.aut.sportnetwork.entity");
	}

	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Session getSession() throws Exception {
		return super.getSession();
	}

	@Bean
	public org.neo4j.ogm.config.Configuration getConfig() {
		org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
		config.driverConfiguration().setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
				.setURI("http://neo4j:yegoap@localhost:7474");
		return config;
	}

}
