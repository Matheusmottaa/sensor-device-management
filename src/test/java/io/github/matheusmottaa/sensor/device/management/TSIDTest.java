package io.github.matheusmottaa.sensor.device.management;

import io.github.matheusmottaa.sensor.device.management.common.IdGenerator;
import io.hypersistence.tsid.TSID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class TSIDTest {

    @Test
    @SuppressWarnings({"squid:S2699", "squid:S1186"})
    void shouldGenerateTSID() {
        TSID tsid = TSID.fast(); // Only for test
        TSID tsid1 = IdGenerator.generateTSID();
        Assertions.assertThat(tsid1.getInstant()).isCloseTo(Instant.now(), Assertions.within(1, ChronoUnit.MINUTES));
    }
}
