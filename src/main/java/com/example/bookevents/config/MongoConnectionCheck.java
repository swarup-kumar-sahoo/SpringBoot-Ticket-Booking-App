package com.example.bookevents.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConnectionCheck {

    @Bean
    CommandLineRunner testMongoConnection(MongoTemplate mongoTemplate) {
        return args -> {
            System.out.println("MongoDB Database Name: "
                    + mongoTemplate.getDb().getName());

            mongoTemplate.getDb().listCollectionNames()
                    .forEach(System.out::println);

            System.out.println("✅ MongoDB Atlas connection successful");
        };
    }
}
