# Plantilla Service Interface

Reemplaza: `Entity` y operaciones segun el enunciado.

```java
public interface EntityService {
    Entity save(Entity entity);
    Optional<Entity> findById(Long id);
    Entity findRequiredById(Long id);
    List<Entity> findAll();
    Entity update(Entity entity);
    boolean deleteById(Long id);
}
```
