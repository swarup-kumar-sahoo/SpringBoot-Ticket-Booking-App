package com.example.bookevents.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoForceConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(
                "mongodb+srv://bookevents_user:StrongPassword123@ticketbookingcluster01.salsn5n.mongodb.net/bookevents?retryWrites=true&w=majority"
        );
    }
}
