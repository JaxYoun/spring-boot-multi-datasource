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
 * @date: 2019/7/21 19:44
 * @description: 第二个数据源配置类【备数据源】
 */
@Configuration
@MapperScans(value = {
        @MapperScan(basePackages = "com.yang.springbootmultidatasource.dao.two", sqlSessionFactoryRef = "sqlSessionFactoryTwo", sqlSessionTemplateRef = "sqlSessionTemplateTwo")
})
public class DatasourceConfigTwo {

    /**
     * 配置第二个数据源
     *
     * @return
     */
    @Bean(name = "dataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource dataSource() {
        return new HikariDataSource();
    }

    /**
     * 给第二个数据源配置事务管理器
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "dataSourceTransactionManagerTwo")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSourceTwo") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 给第二个数据源配置session工厂
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sessionFactory(@Qualifier("dataSourceTwo") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/yang/springbootmultidatasource/dao/mapper/two/*.xml"));
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/two/*.xml"));
        return sessionFactoryBean.getObject();
    }

    /**
     * 给第二个数据源配置sql模板（必须与session工厂对应）
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean("sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
