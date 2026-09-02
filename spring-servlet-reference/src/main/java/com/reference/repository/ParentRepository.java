package com.reference.repository;

import com.reference.model.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentRepository {
    Parent save(Parent parent);

    Optional<Parent> findById(Long id);

    List<Parent> findAll();

    Parent update(Parent parent);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    Optional<Parent> findByCode(String code);

    boolean existsByCode(String code);

    List<Parent> findByStatus(boolean active);

    void clear();
}
