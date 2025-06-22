package io.github.matheusmottaa.sensor.device.management.api.controller;

import io.github.matheusmottaa.sensor.device.management.api.model.SensorInput;
import io.github.matheusmottaa.sensor.device.management.api.model.SensorOutput;
import io.github.matheusmottaa.sensor.device.management.common.IdGenerator;
import io.github.matheusmottaa.sensor.device.management.domain.model.Sensor;
import io.github.matheusmottaa.sensor.device.management.domain.model.SensorId;
import io.github.matheusmottaa.sensor.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SensorOutput> search(@PageableDefault Pageable pageable) {
        Page<Sensor> sensors = sensorRepository.findAll(pageable);
        return sensors.map(SensorOutput::new);
    }

    @GetMapping("{sensorId}")
    @ResponseStatus(HttpStatus.OK)
    public SensorOutput getOne(@PathVariable TSID sensorId) {
        Sensor sensor = getSensor(sensorId);
        return new SensorOutput(sensor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput input) {
        Sensor sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.name())
                .ip(input.ip())
                .model(input.model())
                .location(input.location())
                .protocol(input.protocol())
                .enabled(false)
                .build();

         sensor = sensorRepository.saveAndFlush(sensor);
        return new SensorOutput(sensor);
    }

    @PutMapping("{sensorId}")
    @ResponseStatus(HttpStatus.OK)
    public SensorOutput update(
            @PathVariable TSID sensorId,
            @RequestBody @Valid  SensorInput sensorInput) {
        Sensor sensor = getSensor(sensorId);

        BeanUtils.copyProperties(sensorInput, sensor, "id");
        sensor = sensorRepository.saveAndFlush(sensor);
        return new SensorOutput(sensor);
    }

    @DeleteMapping("{sensorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable TSID sensorId) {
        Sensor sensor = getSensor(sensorId);
        sensorRepository.delete(sensor);
    }

    private Sensor getSensor(TSID tsid) {
        return sensorRepository.findById(new SensorId(tsid))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{sensorId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableSensor(@PathVariable TSID sensorId) {
        Sensor sensor = getSensor(sensorId);
        sensor.setEnabled(true);
        sensorRepository.saveAndFlush(sensor);
    }

    @DeleteMapping("{sensorId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateSensor(@PathVariable TSID sensorId) {
        Sensor sensor = getSensor(sensorId);
        sensor.setEnabled(false);
        sensorRepository.saveAndFlush(sensor);
    }

}
