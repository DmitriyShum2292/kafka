package com.example.kafka.service;

import com.example.kafka.dto.Distance;
import com.example.kafka.dto.Signal;

public interface ProducerService {

    void sendToInputTopic (Signal signal);

    void sendToOutputTopic (Distance distance);
}
