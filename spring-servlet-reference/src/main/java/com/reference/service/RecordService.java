package com.reference.service;

import com.reference.model.Record;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    Record save(Record record);

    Optional<Record> findById(Long id);

    Record findRequiredById(Long id);

    List<Record> findAll();

    Record update(Record record);

    boolean deleteById(Long id);

    List<Record> findByParentId(Long parentId);

    List<Record> findByStatus(boolean active);
}
