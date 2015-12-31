package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceFactory {

    @Bean
    public DataSource dataSource() throws IOException {
        try (InputStream in = getClass().getResourceAsStream("/db.properties")) {
            Properties properties = new Properties();
            properties.load(in);

            HikariConfig config = new HikariConfig(properties);

            return new HikariDataSource(config);
        }
    }

}
