package com.compunet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.compunet.model.Measurement;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Repository
public class MeasurementRepositoryImpl implements MeasurementRepository{

    private List<Measurement> memoryMeasurement;

    public MeasurementRepositoryImpl(){
        memoryMeasurement = new ArrayList<>();

    }

    @PostConstruct
    public void metodoInicial(){
        System.out.println("-> [LIFECYCLE] Inicializando Repositorio en Memoria <-");
        memoryMeasurement.add(new Measurement(1,1,1000L,25.5));
        memoryMeasurement.add(new Measurement(1,2,2000L,28.0));
        memoryMeasurement.add(new Measurement(1,3,2995L,30.5));
    }

    @PreDestroy
    public void metodoFinal(){
        System.out.println("-> [LIFECYCLE] Finalizando Repositorio en Memoria <-");
    }

    @Override
    public void save(Measurement measurement){
        memoryMeasurement.add(measurement);

    }

    @Override
    public Optional<Measurement> findById(int id){
        for (int i = 0; i < memoryMeasurement.size(); i++) {
            if (memoryMeasurement.get(i).getId() == id){
                return Optional.of(memoryMeasurement.get(i));
            }  
        }
        
        return Optional.empty();
            

    }

    @Override
    public List<Measurement> findAll(){
        return memoryMeasurement;
        
    }

    
}
