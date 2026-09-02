package com.reference.service;

import com.reference.model.Parent;
import com.reference.repository.ParentRepository;
import com.reference.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    private final RecordRepository recordRepository;

    // Recibe por constructor los repositories que Spring debe inyectar.
    public ParentServiceImpl(ParentRepository parentRepository, RecordRepository recordRepository) {
        this.parentRepository = parentRepository;
        this.recordRepository = recordRepository;
    }

    // Valida el Parent, verifica id/codigo unicos y lo guarda.
    @Override
    public Parent save(Parent parent) {
        validateParent(parent);
        validateUniqueId(parent.getId());
        validateUniqueCode(parent.getCode());
        return parentRepository.save(parent);
    }

    // Busca un Parent por id y devuelve Optional.empty si no existe.
    @Override
    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    // Busca un Parent obligatorio y lanza IllegalArgumentException si no existe.
    @Override
    public Parent findRequiredById(Long id) {
        return parentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el Parent con id " + id));
    }

    // Devuelve todos los Parent delegando al repository.
    @Override
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    // Valida el Parent, controla que el codigo no sea de otro Parent y actualiza.
    @Override
    public Parent update(Parent parent) {
        validateParent(parent);
        Parent current = findRequiredById(parent.getId());
        parentRepository.findByCode(parent.getCode())
                .filter(other -> !other.getId().equals(current.getId()))
                .ifPresent(other -> {
                    throw new IllegalArgumentException("Ya existe otro Parent con codigo " + parent.getCode());
                });
        return parentRepository.update(parent);
    }

    // Elimina un Parent solo si existe y no tiene Records relacionados.
    @Override
    public boolean deleteById(Long id) {
        Parent parent = findRequiredById(id);
        long relatedRecords = recordRepository.countByParentId(parent.getId());
        if (relatedRecords > 0) {
            throw new IllegalArgumentException("No se puede eliminar: tiene registros relacionados");
        }
        return parentRepository.deleteById(id);
    }

    // Devuelve Parent filtrados por estado activo/inactivo.
    @Override
    public List<Parent> findByStatus(boolean active) {
        return parentRepository.findByStatus(active);
    }

    // Agrupa las validaciones generales que debe cumplir un Parent.
    private void validateParent(Parent parent) {
        if (parent == null) {
            throw new IllegalArgumentException("El Parent no puede ser nulo");
        }
        validateId(parent.getId());
        validateText(parent.getName(), "nombre", 3, 60);
        validateText(parent.getCode(), "codigo", 2, 20);
        if (parent.getMaximumCapacity() <= 0) {
            throw new IllegalArgumentException("La capacidad maxima debe ser positiva");
        }
        if (parent.getMinimumValue() > parent.getMaximumValue()) {
            throw new IllegalArgumentException("El valor minimo no puede ser mayor que el maximo");
        }
        if (parent.getPeriod() <= 0) {
            throw new IllegalArgumentException("El periodo debe ser positivo");
        }
        if (parent.getTolerance() < 0) {
            throw new IllegalArgumentException("La tolerancia no puede ser negativa");
        }
        if (parent.getTolerance() > parent.getPeriod()) {
            throw new IllegalArgumentException("La tolerancia no debe superar el periodo");
        }
    }

    // Valida que el id exista y sea positivo.
    private void validateId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id debe ser positivo");
        }
    }

    // Valida null, vacio, longitud minima y longitud maxima de un texto.
    private void validateText(String text, String field, int min, int max) {
        if (text == null) {
            throw new IllegalArgumentException("El campo " + field + " no puede ser nulo");
        }
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo " + field + " no puede estar vacio");
        }
        if (text.length() < min) {
            throw new IllegalArgumentException("El campo " + field + " debe tener minimo " + min + " caracteres");
        }
        if (text.length() > max) {
            throw new IllegalArgumentException("El campo " + field + " debe tener maximo " + max + " caracteres");
        }
    }

    // Verifica que no exista otro Parent con el mismo id.
    private void validateUniqueId(Long id) {
        if (parentRepository.existsById(id)) {
            throw new IllegalArgumentException("Ya existe un Parent con id " + id);
        }
    }

    // Verifica que no exista otro Parent con el mismo codigo.
    private void validateUniqueCode(String code) {
        if (parentRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Ya existe un Parent con codigo " + code);
        }
    }
}
