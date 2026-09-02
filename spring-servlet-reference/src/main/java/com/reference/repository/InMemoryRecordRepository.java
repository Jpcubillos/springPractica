package com.reference.repository;

import com.reference.model.Record;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryRecordRepository implements RecordRepository {
    private final List<Record> records = new ArrayList<>();

    // Carga datos iniciales validos cuando Spring termina de crear el repository.
    @PostConstruct
    public void init() {
        save(new Record(1L, 1L, 1000L, 20.0, "Registro inicial", true));
        save(new Record(2L, 1L, 1060L, 25.0, "Registro siguiente", true));
        save(new Record(3L, 2L, 2000L, 40.0, "Registro sur", true));
    }

    // Limpia la lista antes de que Spring destruya el repository.
    @PreDestroy
    public void destroy() {
        records.clear();
    }

    // Agrega un Record nuevo a la lista interna.
    @Override
    public Record save(Record record) {
        records.add(record);
        return record;
    }

    // Recorre la lista con for y devuelve el Record que tenga el id recibido.
    @Override
    public Optional<Record> findById(Long id) {
        for (Record record : records) {
            if (record.getId().equals(id)) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    // Devuelve una copia para no exponer directamente la lista interna.
    @Override
    public List<Record> findAll() {
        return new ArrayList<>(records);
    }

    // Busca la posicion del Record por id y reemplaza el objeto completo.
    @Override
    public Record update(Record record) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId().equals(record.getId())) {
                records.set(i, record);
                return record;
            }
        }
        throw new IllegalArgumentException("No existe un Record con id " + record.getId());
    }

    // Elimina de la lista cualquier Record que tenga el id recibido.
    @Override
    public boolean deleteById(Long id) {
        return records.removeIf(record -> record.getId().equals(id));
    }

    // Reutiliza findById para saber si el id existe.
    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

    // Devuelve todos los Record que pertenecen al Parent recibido.
    @Override
    public List<Record> findByParentId(Long parentId) {
        List<Record> result = new ArrayList<>();
        for (Record record : records) {
            if (record.getParentId().equals(parentId)) {
                result.add(record);
            }
        }
        return result;
    }

    // Busca un Record por la combinacion Parent + timestamp.
    @Override
    public Optional<Record> findByParentIdAndTimestamp(Long parentId, long timestamp) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .filter(record -> record.getTimestamp() == timestamp)
                .findFirst();
    }

    // Reutiliza la busqueda combinada para detectar timestamp duplicado.
    @Override
    public boolean existsByParentIdAndTimestamp(Long parentId, long timestamp) {
        return findByParentIdAndTimestamp(parentId, timestamp).isPresent();
    }

    // Devuelve el Record con timestamp mas alto para un Parent.
    @Override
    public Optional<Record> findLatestByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .max(Comparator.comparingLong(Record::getTimestamp));
    }

    // Cuenta todos los Record que pertenecen a un Parent.
    @Override
    public long countByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .count();
    }

    // Cuenta solo los Record activos que pertenecen a un Parent.
    @Override
    public long countActiveByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .filter(Record::isActive)
                .count();
    }

    // Devuelve solo los Record activos de un Parent.
    @Override
    public List<Record> findActiveByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .filter(Record::isActive)
                .toList();
    }

    // Filtra Record segun estado activo o inactivo.
    @Override
    public List<Record> findByStatus(boolean active) {
        return records.stream()
                .filter(record -> record.isActive() == active)
                .toList();
    }

    // Elimina todos los Record almacenados en memoria.
    @Override
    public void clear() {
        records.clear();
    }
}
