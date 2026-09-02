package com.reference.service;

import com.reference.model.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentService {
    Parent save(Parent parent);

    Optional<Parent> findById(Long id);

    Parent findRequiredById(Long id);

    List<Parent> findAll();

    Parent update(Parent parent);

    boolean deleteById(Long id);

    List<Parent> findByStatus(boolean active);
}
