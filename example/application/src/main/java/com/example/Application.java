package com.example;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.matthew.sql.generated.SqlStatementHandler;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) {
        System.out.println(SqlStatementHandler.class);
        System.out.println(new SqlStatementHandler(null));
    }

    @Bean
    public SqlStatementHandler makeStatementHandler(DataSource dataSource) {
        return new SqlStatementHandler(dataSource);
    }

}
