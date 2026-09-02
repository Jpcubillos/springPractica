# Plantilla Service Implementation

Reemplaza: `Entity`, `entityRepository`, validaciones y mensajes.

```java
@Service
public class EntityServiceImpl implements EntityService {
    private final EntityRepository entityRepository;

    public EntityServiceImpl(EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    public Entity save(Entity entity) {
        validate(entity);
        if (entityRepository.existsById(entity.getId())) {
            throw new IllegalArgumentException("Id duplicado");
        }
        return entityRepository.save(entity);
    }

    public Entity findRequiredById(Long id) {
        return entityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la entidad"));
    }

    private void validate(Entity entity) {
        if (entity == null) throw new IllegalArgumentException("La entidad no puede ser nula");
        if (entity.getName() == null || entity.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
    }
}
```
