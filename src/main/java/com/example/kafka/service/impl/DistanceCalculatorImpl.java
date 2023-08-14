package com.example.kafka.service.impl;

import com.example.kafka.dto.Distance;
import com.example.kafka.dto.Signal;
import com.example.kafka.service.DistanceCalculator;
import com.grum.geocalc.Coordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import org.springframework.stereotype.Service;


@Service
public class DistanceCalculatorImpl implements DistanceCalculator {

    public Distance calculate (Signal signal, Signal lastSignal) {
        Coordinate lat = Coordinate.fromDegrees(Double.valueOf(signal.getLat()));
        Coordinate lon = Coordinate.fromDegrees(Double.valueOf(signal.getLon()));

        Point point1 = Point.at(lat,lon);

        Coordinate lat1 = Coordinate.fromDegrees(Double.valueOf(lastSignal.getLat()));
        Coordinate lat2 = Coordinate.fromDegrees(Double.valueOf(lastSignal.getLon()));

        Point point2 = Point.at(lat1, lat2);

        double distance = EarthCalc.gcd.distance(point1, point2);

        return new Distance(lastSignal.getVehicleId(), distance);
    }

}
