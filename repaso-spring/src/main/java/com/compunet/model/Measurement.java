package com.compunet.model;

public class Measurement {
    
    private int id;
    private long timeStamp;
    private double value;
    private int deviceId;


    public Measurement(){

    }

    public Measurement(int deviceId, int id, long timeStamp, double value) {
        this.deviceId = deviceId;
        this.id = id;
        this.timeStamp = timeStamp;
        this.value = value;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public long getTimeStamp() {
        return timeStamp;
    }


    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


    public double getValue() {
        return value;
    }


    public void setValue(double value) {
        this.value = value;
    }


    public int getDeviceId() {
        return deviceId;
    }


    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    
}
