package com.example.kafka.dto;

import java.util.Objects;

public class Signal {

    private long vehicleId;
    private String lat;

    private String lon;

    public Signal() {
    }

    public Signal(long vehicleId, String lat, String lon) {
        this.vehicleId = vehicleId;
        this.lat = lat;
        this.lon = lon;
    }

    public boolean validate () {
        if (this.lat.isEmpty() || this.lon.isEmpty() || this.vehicleId >= 0) {
           return false;
        } else {
            return true;
        }
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Signal signal = (Signal) o;
        return vehicleId == signal.vehicleId && Objects.equals(lat, signal.lat) && Objects.equals(lon, signal.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, lat, lon);
    }

    @Override
    public String toString() {
        return "Signal{" +
                "vehicleId=" + vehicleId +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
