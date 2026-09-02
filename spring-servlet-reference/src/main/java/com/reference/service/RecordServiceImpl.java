package com.reference.service;

import com.reference.model.Parent;
import com.reference.model.Record;
import com.reference.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository recordRepository;
    private final ParentService parentService;

    public RecordServiceImpl(RecordRepository recordRepository, ParentService parentService) {
        this.recordRepository = recordRepository;
        this.parentService = parentService;
    }

    @Override
    public Record save(Record record) {
        validateBasicRecord(record);
        Parent parent = parentService.findRequiredById(record.getParentId());
        validateParentIsActive(parent);
        validateRecordBelongsToParentRules(record, parent, false);
        return recordRepository.save(record);
    }

    @Override
    public Optional<Record> findById(Long id) {
        return recordRepository.findById(id);
    }

    @Override
    public Record findRequiredById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el Record con id " + id));
    }

    @Override
    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    @Override
    public Record update(Record record) {
        validateBasicRecord(record);
        Record current = findRequiredById(record.getId());
        if (!current.isActive()) {
            throw new IllegalArgumentException("No se puede modificar un registro inactivo");
        }
        Parent parent = parentService.findRequiredById(record.getParentId());
        validateParentIsActive(parent);
        validateRecordBelongsToParentRules(record, parent, true);
        return recordRepository.update(record);
    }

    @Override
    public boolean deleteById(Long id) {
        Record record = findRequiredById(id);
        if (!record.isActive()) {
            throw new IllegalArgumentException("El registro ya esta inactivo");
        }
        record.setActive(false);
        recordRepository.update(record);
        return true;
    }

    @Override
    public List<Record> findByParentId(Long parentId) {
        Parent parent = parentService.findRequiredById(parentId);
        return recordRepository.findByParentId(parent.getId());
    }

    @Override
    public List<Record> findByStatus(boolean active) {
        return recordRepository.findByStatus(active);
    }

    private void validateBasicRecord(Record record) {
        if (record == null) {
            throw new IllegalArgumentException("El Record no puede ser nulo");
        }
        if (record.getId() == null || record.getId() <= 0) {
            throw new IllegalArgumentException("El id debe ser positivo");
        }
        if (record.getParentId() == null || record.getParentId() <= 0) {
            throw new IllegalArgumentException("El parentId debe ser positivo");
        }
        if (record.getTimestamp() <= 0) {
            throw new IllegalArgumentException("El timestamp debe ser positivo");
        }
        if (record.getDescription() == null) {
            throw new IllegalArgumentException("La descripcion no puede ser nula");
        }
        if (record.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion no puede estar vacia");
        }
        if (record.getDescription().length() > 100) {
            throw new IllegalArgumentException("La descripcion debe tener maximo 100 caracteres");
        }
    }

    private void validateRecordBelongsToParentRules(Record record, Parent parent, boolean updating) {
        if (!updating && recordRepository.existsById(record.getId())) {
            throw new IllegalArgumentException("Ya existe un Record con id " + record.getId());
        }
        recordRepository.findByParentIdAndTimestamp(record.getParentId(), record.getTimestamp())
                .filter(existing -> !updating || !existing.getId().equals(record.getId()))
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Ya existe un registro para ese Parent y timestamp");
                });
        if (record.getValue() < parent.getMinimumValue() || record.getValue() > parent.getMaximumValue()) {
            throw new IllegalArgumentException("El valor debe estar entre " + parent.getMinimumValue()
                    + " y " + parent.getMaximumValue());
        }
        if (recordRepository.countActiveByParentId(parent.getId()) >= parent.getMaximumCapacity() && !updating) {
            throw new IllegalArgumentException("El Parent alcanzo su capacidad maxima de registros activos");
        }
        recordRepository.findLatestByParentId(parent.getId())
                .filter(previous -> !updating || !previous.getId().equals(record.getId()))
                .ifPresent(previous -> validateSamplingPeriod(previous, record, parent));
    }

    private void validateSamplingPeriod(Record previous, Record current, Parent parent) {
        if (current.getTimestamp() <= previous.getTimestamp()) {
            throw new IllegalArgumentException("El timestamp nuevo debe ser mayor que el anterior");
        }
        long difference = current.getTimestamp() - previous.getTimestamp();
        long minimumAllowed = parent.getPeriod() - parent.getTolerance();
        long maximumAllowed = parent.getPeriod() + parent.getTolerance();
        if (difference < minimumAllowed || difference > maximumAllowed) {
            throw new IllegalArgumentException("La diferencia debe respetar periodo +/- tolerancia: "
                    + minimumAllowed + " a " + maximumAllowed);
        }
    }

    private void validateParentIsActive(Parent parent) {
        if (!parent.isActive()) {
            throw new IllegalArgumentException("No se permite operar sobre un Parent inactivo");
        }
    }
}
