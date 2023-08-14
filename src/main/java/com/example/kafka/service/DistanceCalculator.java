package com.example.kafka.service;

import com.example.kafka.dto.Distance;
import com.example.kafka.dto.Signal;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;

public interface DistanceCalculator {

     Distance calculate (Signal signal, Signal lastSignal);
}
