package io.github.matheusmottaa.sensor.device.management.domain.model;

import io.github.matheusmottaa.sensor.device.management.api.model.SensorInput;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "tb_sensors")
@NoArgsConstructor @AllArgsConstructor
public class Sensor {

    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private SensorId id;

    private String name;

    private String location;

    private String ip;

    private String protocol;

    private String model;

    private Boolean enabled;
}
