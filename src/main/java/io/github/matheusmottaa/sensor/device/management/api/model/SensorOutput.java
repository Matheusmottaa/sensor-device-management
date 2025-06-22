package io.github.matheusmottaa.sensor.device.management.api.model;

import io.github.matheusmottaa.sensor.device.management.domain.model.Sensor;
import io.hypersistence.tsid.TSID;

public record SensorOutput(
        TSID id,
        String name,
        String ip,
        String location ,
        String protocol,
        String model,
        Boolean enabled
) {

    public SensorOutput(Sensor sensor) {
        this(
                sensor.getId().getValue(),
                sensor.getName(),
                sensor.getIp(),
                sensor.getLocation(),
                sensor.getProtocol(),
                sensor.getModel(),
                sensor.getEnabled()
        );
    }
}
