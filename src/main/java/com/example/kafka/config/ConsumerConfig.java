package com.example.kafka.config;


import com.example.kafka.dto.Distance;
import com.example.kafka.dto.Signal;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {

    @Value("${kafka.bootStrapServer}")
    private String bootstrapServers;

    public ConsumerFactory<String, Signal> inputConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "first_group");
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Signal.class, false));
    }
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Signal>>
    inputKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Signal> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(inputConsumerFactory());
        return factory;
    }

    public ConsumerFactory<String, Distance> outputConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "second_group");
        props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(Distance.class, false));
    }
    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Distance>>
    outputKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Distance> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(outputConsumerFactory());
        return factory;
    }
}
