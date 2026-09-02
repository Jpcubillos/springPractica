package com.compunet.repository;

import java.util.List;
import java.util.Optional;

import com.compunet.model.Device;

public interface DeviceRepository {
    
    void save(Device device);
    Optional<Device> findById(int id);
    List<Device> findAll();

    }
