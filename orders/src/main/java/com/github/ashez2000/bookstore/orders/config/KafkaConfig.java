package com.github.ashez2000.bookstore.orders.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic ordersTopic() {
        return new NewTopic("orders", 3, (short) 1);
    }
}
