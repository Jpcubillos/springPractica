package com.reference.repository;

import com.reference.model.Record;

import java.util.List;
import java.util.Optional;

public interface RecordRepository {
    // Guarda un Record nuevo en memoria.
    Record save(Record record);

    // Busca un Record por id y devuelve Optional para evitar null.
    Optional<Record> findById(Long id);

    // Devuelve todos los Record existentes.
    List<Record> findAll();

    // Reemplaza los datos de un Record existente.
    Record update(Record record);

    // Elimina un Record por id.
    boolean deleteById(Long id);

    // Indica si existe un Record con ese id.
    boolean existsById(Long id);

    // Devuelve los Record asociados a un Parent.
    List<Record> findByParentId(Long parentId);

    // Busca un Record usando la combinacion Parent + timestamp.
    Optional<Record> findByParentIdAndTimestamp(Long parentId, long timestamp);

    // Indica si ya existe un Record para ese Parent y timestamp.
    boolean existsByParentIdAndTimestamp(Long parentId, long timestamp);

    // Busca el Record mas reciente de un Parent.
    Optional<Record> findLatestByParentId(Long parentId);

    // Cuenta todos los Record asociados a un Parent.
    long countByParentId(Long parentId);

    // Cuenta solo los Record activos asociados a un Parent.
    long countActiveByParentId(Long parentId);

    // Devuelve solo los Record activos de un Parent.
    List<Record> findActiveByParentId(Long parentId);

    // Filtra Record activos o inactivos.
    List<Record> findByStatus(boolean active);

    // Borra todos los datos en memoria.
    void clear();
}
