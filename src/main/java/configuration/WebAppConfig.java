package configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.sql.SQLException;

/**
 * Created by LulzimG on 29/12/16.
 */

@Configuration
@EnableWebMvc
@ComponentScan({"controller", "service"})
@PropertySource(value = {"classpath:db.properties"})
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${database.driver}")
    private String db_driver;

    @Value("${database.url}")
    private String db_url;

    @Value("${database.username}")
    private String db_username;

    @Value("${database.password}")
    private String db_password;


    @Bean("dataSource")
    public DriverManagerDataSource dataSource() throws SQLException {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(db_driver);
        driverManagerDataSource.setUrl(db_url);
        driverManagerDataSource.setUsername(db_username);
        driverManagerDataSource.setPassword(db_password);

        driverManagerDataSource.getConnection();

        return driverManagerDataSource;
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate() throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    @Bean("namedParameterJdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws SQLException {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return namedParameterJdbcTemplate;
    }

    @Bean(name = "platformTransactionManager")
    public PlatformTransactionManager getPlatformTransactionManager() throws SQLException {
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());

        PlatformTransactionManager txManage = txManager;

        return txManage;
    }

}
