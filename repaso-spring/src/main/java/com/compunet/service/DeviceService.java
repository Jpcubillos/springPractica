package com.compunet.service;

import java.util.List;
import java.util.Optional;

import com.compunet.model.Device;

public interface DeviceService {

    void save(Device device);
    boolean validateSerial(String serialNumber);
    Optional<Device> findById(int id);
    List<Device> findAll();
}
