package io.github.matheusmottaa.sensor.device.management.common;

import io.hypersistence.tsid.TSID;

import java.util.Optional;

public class IdGenerator {

    private static final TSID.Factory factory;

    private IdGenerator() {
    }

    static {
        Optional.ofNullable(System.getenv("tsid.node"))
                        .ifPresent(tsidNode -> System.setProperty("tsid.node", "2")); // Read environment configurations to generate TSID

        Optional.ofNullable(System.getenv("tsid.node"))
                        .ifPresent(nodeCount -> System.setProperty("tsid.node.count", "32"));

        factory = TSID.Factory.builder().build();
    }

    public static TSID generateTSID() {
        return factory.generate();
    }
}
