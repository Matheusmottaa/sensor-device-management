package io.github.matheusmottaa.sensor.device.management.api.model;

import jakarta.validation.constraints.NotEmpty;

public record SensorInput(

        @NotEmpty
        String name,

        @NotEmpty
        String ip,

        @NotEmpty
        String location,

        @NotEmpty
        String protocol,

        @NotEmpty
        String model
) {
}
