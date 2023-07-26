package com.example.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("input_topic").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("output_topic").build();
    }
}
