package com.compunet.repository;

import java.util.List;
import java.util.Optional;

import com.compunet.model.Measurement;

public interface MeasurementRepository {
    
    void save(Measurement measurement);
    Optional<Measurement> findById(int id);
    List<Measurement> findAll();
    }
