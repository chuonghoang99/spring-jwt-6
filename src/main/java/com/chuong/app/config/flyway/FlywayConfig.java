package com.chuong.app.config.flyway;

import org.springframework.beans.factory.annotation.Value;


public class FlywayConfig {

    @Value("${spring.flyway.location}")
    private String[] flywayLocation;


    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.username}")
    private String datasourceUsername;


    //    @Value("${spring.datasource.password}")
    //    private String datasourcePassword;

//    @Bean
//    public Flyway flyway() {
//        Flyway flyway = Flyway.configure().dataSource(dataSource()).locations(flywayLocation).baselineOnMigrate(true).load();
//        flyway.migrate(); // run sql file if version is newer
//        return flyway;
//    }
//
//    @Bean
//    public DataSource dataSource() {
//        var dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(datasourceUrl);
//        dataSource.setUsername(datasourceUsername);
//        return dataSource;
//    }
}