package com.compunet.model;

public class Device {
    
    private int id;
    private String name;
    private String serialNumber;
    private String type;
    private double maxValue;
    private double minValue;
    private long samplingPeriod;
    private long timeTolerance;
    private String unit;

    public Device(){

    }


    public Device(int id, double maxValue, double minValue, String name, long samplingPeriod, String serialNumber, long timeTolerance, String type, String unit) {
        this.id = id;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.name = name;
        this.samplingPeriod = samplingPeriod;
        this.serialNumber = serialNumber;
        this.timeTolerance = timeTolerance;
        this.type = type;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public long getSamplingPeriod() {
        return samplingPeriod;
    }

    public void setSamplingPeriod(long samplingPeriod) {
        this.samplingPeriod = samplingPeriod;
    }

    public long getTimeTolerance() {
        return timeTolerance;
    }

    public void setTimeTolerance(long timeTolerance) {
        this.timeTolerance = timeTolerance;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


}
    