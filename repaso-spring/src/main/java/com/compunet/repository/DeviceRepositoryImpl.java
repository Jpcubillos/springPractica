package com.compunet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.compunet.model.Device;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Repository
public class DeviceRepositoryImpl implements DeviceRepository{

    private List<Device> memoryDevices;

    public DeviceRepositoryImpl(){
        memoryDevices = new ArrayList<>();
    }

    @PostConstruct
    public void metodoInicial(){
        System.out.println("-> [LIFECYCLE] Inicializando Repositorio en Memoria <-");
        memoryDevices.add(new Device(1,300.0,-100.0,"Temp_Reactor",1000L,"TMP-AX34-7789",10L,"Temperature","Celsius"));
        memoryDevices.add(new Device(2,20.0,0.0,"Pressure_Line_A",2000L,"PRS-BX11-9921",100L,"Pressure","Bar"));
        memoryDevices.add(new Device(3,14.0,0.0,"PH_Mixing_Tank",3000L,"PH-CX09-3345",200L,"pH","pH"));
    }

    @PreDestroy
    public void metodoFinal(){
        System.out.println("-> [LIFECYCLE] Finalizando Repositorio en Memoria <-");
    }

    @Override
    public void save(Device device){
        memoryDevices.add(device);

    }

    @Override
    public Optional<Device> findById(int id){
        for (int i = 0; i < memoryDevices.size(); i++) {
            if (memoryDevices.get(i).getId() == id){
                return Optional.of(memoryDevices.get(i));
            }  
        }
        
        return Optional.empty();
            

    }

    @Override
    public List<Device> findAll(){
        return memoryDevices;
        
    }

    
}
