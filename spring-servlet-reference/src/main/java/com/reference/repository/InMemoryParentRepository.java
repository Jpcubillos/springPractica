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

    @PostConstruct
    public void init() {
        save(new Parent(1L, "Linea Norte", "NORTE", 5, 0.0, 100.0, 60L, 10L, true));
        save(new Parent(2L, "Linea Sur", "SUR", 3, 10.0, 80.0, 30L, 5L, true));
        save(new Parent(3L, "Linea Inactiva", "INACTIVA", 2, 0.0, 50.0, 15L, 3L, false));
    }

    @PreDestroy
    public void destroy() {
        parents.clear();
    }

    @Override
    public Parent save(Parent parent) {
        parents.add(parent);
        return parent;
    }

    @Override
    public Optional<Parent> findById(Long id) {
        for (Parent parent : parents) {
            if (parent.getId().equals(id)) {
                return Optional.of(parent);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Parent> findAll() {
        return new ArrayList<>(parents);
    }

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

    @Override
    public boolean deleteById(Long id) {
        return parents.removeIf(parent -> parent.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    @Override
    public Optional<Parent> findByCode(String code) {
        return parents.stream()
                .filter(parent -> parent.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    @Override
    public boolean existsByCode(String code) {
        return findByCode(code).isPresent();
    }

    @Override
    public List<Parent> findByStatus(boolean active) {
        return parents.stream()
                .filter(parent -> parent.isActive() == active)
                .toList();
    }

    @Override
    public void clear() {
        parents.clear();
    }
}
