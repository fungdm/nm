package com.dava.maintaince.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class AppConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        String connectionString = "mongodb://localhost:27017/employeeDB";
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(connectionString));
    }
}
