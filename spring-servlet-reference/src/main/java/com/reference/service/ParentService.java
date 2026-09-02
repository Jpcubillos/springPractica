package com.reference.service;

import com.reference.model.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentService {
    // Valida y guarda un Parent nuevo.
    Parent save(Parent parent);

    // Busca un Parent por id sin lanzar error si no existe.
    Optional<Parent> findById(Long id);

    // Busca un Parent por id y lanza error si no existe.
    Parent findRequiredById(Long id);

    // Devuelve todos los Parent.
    List<Parent> findAll();

    // Valida y actualiza un Parent existente.
    Parent update(Parent parent);

    // Elimina un Parent si no tiene Records relacionados.
    boolean deleteById(Long id);

    // Devuelve Parent activos o inactivos.
    List<Parent> findByStatus(boolean active);
}
