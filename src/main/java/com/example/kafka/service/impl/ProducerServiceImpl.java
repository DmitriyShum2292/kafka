package com.example.kafka.service.impl;

import com.example.kafka.dto.Distance;
import com.example.kafka.dto.Signal;
import com.example.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private KafkaTemplate<String, Signal> kafkaTemplate;

    @Autowired KafkaTemplate<String, Distance> kafkaTemplateDistance;


    public void sendToInputTopic (Signal signal) {
        kafkaTemplate.send("input_topic", Long.toString(signal.getVehicleId()), signal);
    }

    public void sendToOutputTopic (Distance distance) {
        kafkaTemplateDistance.send("output_topic", Long.toString(distance.getVehicleId()), distance);
    }
}
