package uz.pdp.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = ("uz.pdp")) //for scanning entities and repositories
public class SpringDataJpaConfigurer {


    private final Environment env;

    public SpringDataJpaConfigurer(Environment env) {
        this.env = env;

    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getRequiredProperty("spring.datasource.jdbc.url"));
        dataSource.setSchema(env.getRequiredProperty("spring.datasource.jdbc.schema"));
        dataSource.setUsername(env.getRequiredProperty("spring.datasource.jdbc.user"));
        dataSource.setPassword(env.getRequiredProperty("spring.datasource.jdbc.password"));
        dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.jdbc.driver"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("uz.pdp");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        entityManagerFactoryBean.setJpaProperties(properties());
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        EntityManagerFactory managerFactoryBeanObject = entityManagerFactoryBean.getObject();
        return new JpaTransactionManager(managerFactoryBeanObject);
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", env.getRequiredProperty("spring.datasource.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getRequiredProperty("spring.datasource.hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("spring.datasource.hibernate.hbm2ddl.auto"));
        return properties;
    }
}
