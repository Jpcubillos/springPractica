package com.compunet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.compunet.model.Device;
import com.compunet.repository.DeviceRepository;
import com.compunet.repository.DeviceRepositoryImpl;

@Service
public class DeviceServiceImpl implements DeviceService{
    
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl (DeviceRepository repository){
        this.deviceRepository = repository;
    }

    @Override
    public void save(Device device){
        if(!validateSerial(device.getSerialNumber())){
            throw new IllegalArgumentException(
                "El serial no valido, revisa que tenga almenos 1 caracter y menos de 20"
            );
        }
        if(deviceRepository.findById(device.getId()).isPresent()){
            throw new IllegalArgumentException(
            "Ya existe un dispositivo con ese ID"
            );  
        }
        
        deviceRepository.save(device);
    }
    
    @Override
    public boolean validateSerial(String serialNumber){
        if (serialNumber == null || serialNumber.length() > 20){
            return false;
        }
        return true;
    }

    @Override
    public Optional<Device> findById(int id){
        return deviceRepository.findById(id);
    }

    @Override
    public List<Device> findAll(){
        return deviceRepository.findAll();
    }
}
