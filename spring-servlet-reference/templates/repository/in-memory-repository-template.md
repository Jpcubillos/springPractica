# Plantilla In-memory Repository

Reemplaza: `Entity`, `entities`, campos de busqueda y datos iniciales.

```java
@Repository
public class InMemoryEntityRepository implements EntityRepository {
    private final List<Entity> entities = new ArrayList<>();

    @PostConstruct
    public void init() {
        save(new Entity(1L, "Ejemplo", true));
    }

    @PreDestroy
    public void destroy() {
        entities.clear();
    }

    public Entity save(Entity entity) {
        entities.add(entity);
        return entity;
    }

    public Optional<Entity> findById(Long id) {
        for (Entity entity : entities) {
            if (entity.getId().equals(id)) return Optional.of(entity);
        }
        return Optional.empty();
    }

    public List<Entity> findAll() {
        return new ArrayList<>(entities);
    }

    public Entity update(Entity entity) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getId().equals(entity.getId())) {
                entities.set(i, entity);
                return entity;
            }
        }
        throw new IllegalArgumentException("No existe la entidad");
    }
}
```
