package com.reference.repository;

import com.reference.model.Record;

import java.util.List;
import java.util.Optional;

public interface RecordRepository {
    Record save(Record record);

    Optional<Record> findById(Long id);

    List<Record> findAll();

    Record update(Record record);

    boolean deleteById(Long id);

    boolean existsById(Long id);

    List<Record> findByParentId(Long parentId);

    Optional<Record> findByParentIdAndTimestamp(Long parentId, long timestamp);

    boolean existsByParentIdAndTimestamp(Long parentId, long timestamp);

    Optional<Record> findLatestByParentId(Long parentId);

    long countByParentId(Long parentId);

    long countActiveByParentId(Long parentId);

    List<Record> findActiveByParentId(Long parentId);

    List<Record> findByStatus(boolean active);

    void clear();
}
