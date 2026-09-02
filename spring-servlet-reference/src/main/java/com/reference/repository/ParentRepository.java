package com.reference.repository;

import com.reference.model.Parent;

import java.util.List;
import java.util.Optional;

public interface ParentRepository {
    // Guarda un Parent nuevo en memoria.
    Parent save(Parent parent);

    // Busca un Parent por id y devuelve Optional para evitar null.
    Optional<Parent> findById(Long id);

    // Devuelve todos los Parent existentes.
    List<Parent> findAll();

    // Reemplaza los datos de un Parent existente.
    Parent update(Parent parent);

    // Elimina un Parent por id.
    boolean deleteById(Long id);

    // Indica si existe un Parent con ese id.
    boolean existsById(Long id);

    // Busca un Parent por codigo unico.
    Optional<Parent> findByCode(String code);

    // Indica si ya existe un Parent con ese codigo.
    boolean existsByCode(String code);

    // Filtra Parent activos o inactivos.
    List<Parent> findByStatus(boolean active);

    // Borra todos los datos en memoria.
    void clear();
}
