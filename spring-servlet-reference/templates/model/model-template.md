# Plantilla Model

Reemplaza: `Entity`, campos, constructor y getters/setters.

```java
package com.example.model;

public class Entity {
    private Long id;
    private String name;
    private boolean active;

    public Entity() {
    }

    public Entity(Long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
```
