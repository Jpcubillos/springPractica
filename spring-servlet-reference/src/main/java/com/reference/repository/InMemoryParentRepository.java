package com.reference.repository;

import com.reference.model.Parent;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryParentRepository implements ParentRepository {
    private final List<Parent> parents = new ArrayList<>();

    // Carga datos iniciales validos cuando Spring termina de crear el repository.
    @PostConstruct
    public void init() {
        save(new Parent(1L, "Linea Norte", "NORTE", 5, 0.0, 100.0, 60L, 10L, true));
        save(new Parent(2L, "Linea Sur", "SUR", 3, 10.0, 80.0, 30L, 5L, true));
        save(new Parent(3L, "Linea Inactiva", "INACTIVA", 2, 0.0, 50.0, 15L, 3L, false));
    }

    // Limpia la lista antes de que Spring destruya el repository.
    @PreDestroy
    public void destroy() {
        parents.clear();
    }

    // Agrega un Parent nuevo a la lista interna.
    @Override
    public Parent save(Parent parent) {
        parents.add(parent);
        return parent;
    }

    // Recorre la lista con for y devuelve el Parent que tenga el id recibido.
    @Override
    public Optional<Parent> findById(Long id) {
        for (Parent parent : parents) {
            if (parent.getId().equals(id)) {
                return Optional.of(parent);
            }
        }
        return Optional.empty();
    }

    // Devuelve una copia para no exponer directamente la lista interna.
    @Override
    public List<Parent> findAll() {
        return new ArrayList<>(parents);
    }

    // Busca la posicion del Parent por id y reemplaza el objeto completo.
    @Override
    public Parent update(Parent parent) {
        for (int i = 0; i < parents.size(); i++) {
            if (parents.get(i).getId().equals(parent.getId())) {
                parents.set(i, parent);
                return parent;
            }
        }
        throw new IllegalArgumentException("No existe un Parent con id " + parent.getId());
    }

    // Elimina de la lista cualquier Parent que tenga el id recibido.
    @Override
    public boolean deleteById(Long id) {
        return parents.removeIf(parent -> parent.getId().equals(id));
    }

    // Reutiliza findById para saber si el id existe.
    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    // Busca por codigo ignorando mayusculas y minusculas.
    @Override
    public Optional<Parent> findByCode(String code) {
        return parents.stream()
                .filter(parent -> parent.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    // Reutiliza findByCode para validar codigos duplicados.
    @Override
    public boolean existsByCode(String code) {
        return findByCode(code).isPresent();
    }

    // Filtra Parent segun estado activo o inactivo.
    @Override
    public List<Parent> findByStatus(boolean active) {
        return parents.stream()
                .filter(parent -> parent.isActive() == active)
                .toList();
    }

    // Elimina todos los Parent almacenados en memoria.
    @Override
    public void clear() {
        parents.clear();
    }
}
