# Plantilla Repository Interface

Reemplaza: `Entity`, `findByCode`, campos unicos y paquete.

```java
package com.example.repository;

import com.example.model.Entity;
import java.util.List;
import java.util.Optional;

public interface EntityRepository {
    Entity save(Entity entity);
    Optional<Entity> findById(Long id);
    List<Entity> findAll();
    Entity update(Entity entity);
    boolean deleteById(Long id);
    boolean existsById(Long id);
    Optional<Entity> findByCode(String code);
    boolean existsByCode(String code);
    List<Entity> findByStatus(boolean active);
    void clear();
}
```
