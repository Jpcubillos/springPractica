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

    @PostConstruct
    public void init() {
        save(new Record(1L, 1L, 1000L, 20.0, "Registro inicial", true));
        save(new Record(2L, 1L, 1060L, 25.0, "Registro siguiente", true));
        save(new Record(3L, 2L, 2000L, 40.0, "Registro sur", true));
    }

    @PreDestroy
    public void destroy() {
        records.clear();
    }

    @Override
    public Record save(Record record) {
        records.add(record);
        return record;
    }

    @Override
    public Optional<Record> findById(Long id) {
        for (Record record : records) {
            if (record.getId().equals(id)) {
                return Optional.of(record);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Record> findAll() {
        return new ArrayList<>(records);
    }

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

    @Override
    public boolean deleteById(Long id) {
        return records.removeIf(record -> record.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return findById(id).isPresent();
    }

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

    @Override
    public Optional<Record> findByParentIdAndTimestamp(Long parentId, long timestamp) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .filter(record -> record.getTimestamp() == timestamp)
                .findFirst();
    }

    @Override
    public boolean existsByParentIdAndTimestamp(Long parentId, long timestamp) {
        return findByParentIdAndTimestamp(parentId, timestamp).isPresent();
    }

    @Override
    public Optional<Record> findLatestByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .max(Comparator.comparingLong(Record::getTimestamp));
    }

    @Override
    public long countByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .count();
    }

    @Override
    public long countActiveByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .filter(Record::isActive)
                .count();
    }

    @Override
    public List<Record> findActiveByParentId(Long parentId) {
        return records.stream()
                .filter(record -> record.getParentId().equals(parentId))
                .filter(Record::isActive)
                .toList();
    }

    @Override
    public List<Record> findByStatus(boolean active) {
        return records.stream()
                .filter(record -> record.isActive() == active)
                .toList();
    }

    @Override
    public void clear() {
        records.clear();
    }
}
