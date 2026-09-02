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

    // Constructor vacio requerido por frameworks o formularios que crean el objeto sin datos iniciales.
    public Parent() {
    }

    // Constructor completo para crear un Parent con todos sus campos de una vez.
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

    // Devuelve el identificador unico del Parent.
    public Long getId() {
        return id;
    }

    // Cambia el identificador unico del Parent.
    public void setId(Long id) {
        this.id = id;
    }

    // Devuelve el nombre visible del Parent.
    public String getName() {
        return name;
    }

    // Cambia el nombre visible del Parent.
    public void setName(String name) {
        this.name = name;
    }

    // Devuelve el codigo unico del Parent.
    public String getCode() {
        return code;
    }

    // Cambia el codigo unico del Parent.
    public void setCode(String code) {
        this.code = code;
    }

    // Devuelve la cantidad maxima de Records activos permitidos.
    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    // Cambia la cantidad maxima de Records activos permitidos.
    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    // Devuelve el valor minimo permitido para un Record relacionado.
    public double getMinimumValue() {
        return minimumValue;
    }

    // Cambia el valor minimo permitido para un Record relacionado.
    public void setMinimumValue(double minimumValue) {
        this.minimumValue = minimumValue;
    }

    // Devuelve el valor maximo permitido para un Record relacionado.
    public double getMaximumValue() {
        return maximumValue;
    }

    // Cambia el valor maximo permitido para un Record relacionado.
    public void setMaximumValue(double maximumValue) {
        this.maximumValue = maximumValue;
    }

    // Devuelve el periodo esperado entre registros.
    public long getPeriod() {
        return period;
    }

    // Cambia el periodo esperado entre registros.
    public void setPeriod(long period) {
        this.period = period;
    }

    // Devuelve la tolerancia permitida sobre el periodo.
    public long getTolerance() {
        return tolerance;
    }

    // Cambia la tolerancia permitida sobre el periodo.
    public void setTolerance(long tolerance) {
        this.tolerance = tolerance;
    }

    // Indica si el Parent esta activo.
    public boolean isActive() {
        return active;
    }

    // Cambia el estado activo/inactivo del Parent.
    public void setActive(boolean active) {
        this.active = active;
    }
}
