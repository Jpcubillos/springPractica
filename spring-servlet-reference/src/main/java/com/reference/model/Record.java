package com.reference.model;

public class Record {
    private Long id;
    private Long parentId;
    private long timestamp;
    private double value;
    private String description;
    private boolean active;

    // Constructor vacio requerido por frameworks o formularios que crean el objeto sin datos iniciales.
    public Record() {
    }

    // Constructor completo para crear un Record con todos sus campos de una vez.
    public Record(Long id, Long parentId, long timestamp, double value, String description, boolean active) {
        this.id = id;
        this.parentId = parentId;
        this.timestamp = timestamp;
        this.value = value;
        this.description = description;
        this.active = active;
    }

    // Devuelve el identificador unico del Record.
    public Long getId() {
        return id;
    }

    // Cambia el identificador unico del Record.
    public void setId(Long id) {
        this.id = id;
    }

    // Devuelve el id del Parent relacionado.
    public Long getParentId() {
        return parentId;
    }

    // Cambia el id del Parent relacionado.
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    // Devuelve el instante o momento del registro.
    public long getTimestamp() {
        return timestamp;
    }

    // Cambia el instante o momento del registro.
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Devuelve el valor medido o registrado.
    public double getValue() {
        return value;
    }

    // Cambia el valor medido o registrado.
    public void setValue(double value) {
        this.value = value;
    }

    // Devuelve la descripcion del registro.
    public String getDescription() {
        return description;
    }

    // Cambia la descripcion del registro.
    public void setDescription(String description) {
        this.description = description;
    }

    // Indica si el Record esta activo.
    public boolean isActive() {
        return active;
    }

    // Cambia el estado activo/inactivo del Record.
    public void setActive(boolean active) {
        this.active = active;
    }
}
