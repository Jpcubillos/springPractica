package com.reference.model;

public class Record {
    private Long id;
    private Long parentId;
    private long timestamp;
    private double value;
    private String description;
    private boolean active;

    public Record() {
    }

    public Record(Long id, Long parentId, long timestamp, double value, String description, boolean active) {
        this.id = id;
        this.parentId = parentId;
        this.timestamp = timestamp;
        this.value = value;
        this.description = description;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
