package com.example.kafka.service.impl;

import com.example.kafka.dto.Distance;
import com.example.kafka.dto.Signal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ConsumerService  {

    @Autowired
    private ProducerService producerService;
    @Autowired
    private DistanceCalculator distanceCalculator;

    private Logger logger = LoggerFactory.getLogger(ConsumerService.class);

     Map<Long, Signal> signals = Collections.synchronizedMap(new HashMap<>());


    @KafkaListener(
            topics = "input_topic",
            groupId="first_group",
            containerFactory="inputKafkaListenerContainerFactory")
    void firstGroupListener1 (Signal signal){
        logger.info("CustomUserListener [{}]", signal.toString() +" first_group****listener1");
        SendThread thread = new SendThread(signal);
    }
    @KafkaListener(
            topics = "input_topic",
            groupId="first_group",
            containerFactory="inputKafkaListenerContainerFactory")
    void firstGroupListener2 (Signal signal){
        logger.info("CustomUserListener [{}]", signal.toString() +  " first_group****listener2");
        SendThread thread = new SendThread(signal);
    }
    @KafkaListener(
            topics = "input_topic",
            groupId="first_group",
            containerFactory="inputKafkaListenerContainerFactory")
    void firstGroupListener3(Signal signal){
        logger.info("CustomUserListener [{}]", signal.toString() + " first_group****listener3");
        SendThread thread = new SendThread(signal);
    }

    @KafkaListener(
            topics = "output_topic",
            groupId="second_group",
            containerFactory="outputKafkaListenerContainerFactory")
    void secondGroupListener(Distance distance){
        logger.info("CustomUserListener [{}]", distance.toString() + " second_group****listener1");
    }


    class SendThread implements Runnable {

        private Signal signal;

        public SendThread(Signal signal) {
            this.signal = signal;
            run();
        }

        @Override
        public void run() {

            Signal previousSignal = signals.get(signal.getVehicleId());

            signals.put(signal.getVehicleId(), signal);
            if (previousSignal != null) {
                Distance distance = distanceCalculator.calculate(previousSignal, signal);
                producerService.sendToOutputTopic(distance);
            }

        }
    }
}
