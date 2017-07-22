package com.ntt.smartglass.rest.config;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.mapper.mapperhelper.MapperInterceptor;

import javax.sql.DataSource;
import java.util.Properties;


/**
 * {type your description }
 *
 * @since: 15/11/21.
 * @author: yangjunming
 */
@Configuration
@MapperScan("com.ntt.smartglass.rest.mapper")
public class MybasitConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean
                .setMapperLocations(resolver.getResources("classpath:mybatis/rest/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ntt.smartglass.rest.entity");
        MapperInterceptor tkMapperInterceptor = new MapperInterceptor();
        Properties tkProp = new Properties();
        tkProp.setProperty("mappers", "com.ntt.smartglass.common.mapper.TableMapper");
        tkProp.setProperty("IDENTITY", "MYSQL");
        tkProp.setProperty("notEmpty", "true");
        tkMapperInterceptor.setProperties(tkProp);
        Interceptor[] interceptors = new Interceptor[1];
        interceptors[0] = tkMapperInterceptor;
        sqlSessionFactoryBean.setPlugins(interceptors);

        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
