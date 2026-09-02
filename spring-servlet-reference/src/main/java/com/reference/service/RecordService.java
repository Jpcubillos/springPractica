package com.reference.service;

import com.reference.model.Record;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    // Valida y guarda un Record nuevo.
    Record save(Record record);

    // Busca un Record por id sin lanzar error si no existe.
    Optional<Record> findById(Long id);

    // Busca un Record por id y lanza error si no existe.
    Record findRequiredById(Long id);

    // Devuelve todos los Record.
    List<Record> findAll();

    // Valida y actualiza un Record existente.
    Record update(Record record);

    // Inactiva o elimina logicamente un Record por id.
    boolean deleteById(Long id);

    // Devuelve los Record asociados a un Parent.
    List<Record> findByParentId(Long parentId);

    // Devuelve Record activos o inactivos.
    List<Record> findByStatus(boolean active);
}
