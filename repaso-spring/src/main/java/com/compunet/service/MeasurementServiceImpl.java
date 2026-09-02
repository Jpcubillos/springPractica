package com.compunet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compunet.model.Device;
import com.compunet.model.Measurement;
import com.compunet.repository.MeasurementRepository;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final DeviceService deviceService;

    @Autowired
    public MeasurementServiceImpl(
            MeasurementRepository measurementRepository,
            DeviceService deviceService) {

        this.measurementRepository = measurementRepository;
        this.deviceService = deviceService;
    }

    @Override
    public void save(Measurement measurement) {

        Device device = deviceService.findById(measurement.getDeviceId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Dispositivo no encontrado"
                ));

        if (!validateValue(device, measurement.getValue())) {
            throw new IllegalArgumentException(
                    "El valor de la medición está fuera del rango del dispositivo"
            );
        }

        if (!validateTimeStamp(measurement)) {
            throw new IllegalArgumentException(
                    "El timestamp ya existe para este dispositivo"
            );
        }

        if (!validatePeriod(device, measurement)) {
            throw new IllegalArgumentException(
                    "El timestamp no cumple el periodo de muestreo y su tolerancia"
            );
        }

        measurementRepository.save(measurement);
    }

    @Override
    public Optional<Measurement> findById(int id) {
        return measurementRepository.findById(id);
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public boolean validateValue(Device device, double value) {
        return value >= device.getMinValue()
                && value <= device.getMaxValue();
    }

    @Override
    public boolean validateTimeStamp(Measurement measurement) {

        List<Measurement> registeredMeasurements =
                measurementRepository.findAll();

        for (Measurement registered : registeredMeasurements) {

            boolean sameDevice =
                    registered.getDeviceId() == measurement.getDeviceId();

            boolean sameTimeStamp =
                    registered.getTimeStamp() == measurement.getTimeStamp();

            if (sameDevice && sameTimeStamp) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean validatePeriod(
            Device device,
            Measurement measurement) {

        Measurement previous = null;

        for (Measurement registered : measurementRepository.findAll()) {

            boolean sameDevice =
                    registered.getDeviceId() == measurement.getDeviceId();

            boolean isMoreRecent =
                    previous == null
                    || registered.getTimeStamp() > previous.getTimeStamp();

            if (sameDevice && isMoreRecent) {
                previous = registered;
            }
        }

        // La primera medición del dispositivo no tiene una anterior.
        if (previous == null) {
            return true;
        }

        long difference =
                measurement.getTimeStamp() - previous.getTimeStamp();

        long minimum =
                device.getSamplingPeriod() - device.getTimeTolerance();

        long maximum =
                device.getSamplingPeriod() + device.getTimeTolerance();

        return difference >= minimum && difference <= maximum;
    }
}