package io.github.matheusmottaa.sensor.device.management.domain.repository;

import io.github.matheusmottaa.sensor.device.management.domain.model.Sensor;
import io.github.matheusmottaa.sensor.device.management.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
