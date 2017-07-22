package com.ntt.smartglass.rest.main;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * スタートアップクラス
 */
@SpringBootApplication
@ComponentScan("com.ntt.smartglass")
@EnableConfigurationProperties
public class RestApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(RestApplication.class);
    private final int versionFilterOrder = 2;


    @Qualifier("smartGlassDataSource")
    @Autowired
    protected DataSource dataSource;

//    @Autowired
//    private VersionFilter versionFilter;
//
//    @Autowired
//    private GlassTokenFilter glassTokenFilter;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        try {
            dataSource.getConnection().close();
            logger.info("try db connection ok");
        } catch (Exception e) {
            logger.error("db connection fail:", e);
        }
    }


}