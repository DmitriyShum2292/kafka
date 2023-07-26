package com.example.kafka.dto;

public class Distance {

    private long vehicleId;
    private double distance;

    public Distance() {
    }

    public Distance(long vehicleId, double distance) {
        this.vehicleId = vehicleId;
        this.distance = distance;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "vehicleId=" + vehicleId +
                ", distance=" + distance +
                '}';
    }
}
