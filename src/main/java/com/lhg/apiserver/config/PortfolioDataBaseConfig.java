package com.lhg.apiserver.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;


@Configuration
@MapperScan(value = "com.lhg.apiserver.db.portfolio", sqlSessionFactoryRef = "portfolioSqlSessionFactory")
@EnableTransactionManagement
public class PortfolioDataBaseConfig {
    @Bean(name="portfolioDataSource")
    @ConfigurationProperties(prefix = "spring.portfolio.datasource")
    public DataSource portfolioDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="portfolioSqlSessionFactory")
    public SqlSessionFactory portfolioSqlSessionFactory(@Qualifier("portfolioDataSource")DataSource portfolioDataSource,
                                                        ApplicationContext applicationContext) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(portfolioDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/portfolio/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="portfolioSqlSessionTemplate")
    public SqlSessionTemplate portfolioSqlSessionTemplate(SqlSessionFactory portfolioSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(portfolioSqlSessionFactory);
    }

}
