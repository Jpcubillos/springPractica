package com.compunet.service;


import java.util.List;
import java.util.Optional;

import com.compunet.model.Device;
import com.compunet.model.Measurement;

public interface MeasurementService {
    
    void save(Measurement Measurement);
    Optional<Measurement> findById(int id);
    List<Measurement> findAll();
    boolean validateValue(Device device, double  value);
    boolean validateTimeStamp(Measurement measurement);
    boolean validatePeriod(Device device, Measurement measurement);

}
