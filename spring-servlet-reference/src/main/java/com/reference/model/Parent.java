package com.reference.model;

public class Parent {
    private Long id;
    private String name;
    private String code;
    private int maximumCapacity;
    private double minimumValue;
    private double maximumValue;
    private long period;
    private long tolerance;
    private boolean active;

    public Parent() {
    }

    public Parent(Long id, String name, String code, int maximumCapacity, double minimumValue,
                  double maximumValue, long period, long tolerance, boolean active) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.maximumCapacity = maximumCapacity;
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
        this.period = period;
        this.tolerance = tolerance;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public double getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    public double getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(double maximumValue) {
        this.maximumValue = maximumValue;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public long getTolerance() {
        return tolerance;
    }

    public void setTolerance(long tolerance) {
        this.tolerance = tolerance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
