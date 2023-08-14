package com.example.kafka.controller;

import com.example.kafka.dto.Signal;
import com.example.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

    @Autowired
    private ProducerService producerService;

    @PostMapping
    public void send (@RequestBody Signal signal) {
        producerService.sendToInputTopic(signal);
    }
}
