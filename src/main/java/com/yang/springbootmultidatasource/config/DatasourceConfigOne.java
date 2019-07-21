package com.yang.springbootmultidatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @author: Yang
 * @date: 2019/7/21 14:11
 * @description: 第一个数据源配置类【主数据源】
 */
@Configuration
@MapperScans(value = {
        @MapperScan(basePackages = "com.yang.springbootmultidatasource.dao.one", sqlSessionFactoryRef = "sqlSessionFactoryOne", sqlSessionTemplateRef = "sqlSessionTemplateOne"),
})
public class DatasourceConfigOne {

    /**
     * 配置第一个数据源
     *
     * @return
     */
    @Primary
    @Bean(name = "dataSourceOne")
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    /**
     * 给第一个数据源配置事务管理器
     *
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "dataSourceTransactionManagerOne")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSourceOne") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 给第一个数据源配置session工厂
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean(name = "sqlSessionFactoryOne")
    public SqlSessionFactory sessionFactory(@Qualifier("dataSourceOne") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/yang/springbootmultidatasource/dao/mapper/one/*.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/one/*.xml"));
        return sessionFactoryBean.getObject();
    }

    /**
     * 给第一个数据源配置sql模板（必须与session工厂对应）
     *
     * @param sqlSessionFactory
     * @return
     */
    @Primary
    @Bean("sqlSessionTemplateOne")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
