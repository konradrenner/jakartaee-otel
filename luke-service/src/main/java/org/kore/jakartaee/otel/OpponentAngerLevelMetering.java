/*

 */
package org.kore.jakartaee.otel;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.LongGauge;
import io.opentelemetry.api.metrics.Meter;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class OpponentAngerLevelMetering {

    //tag::metering[]
    @Inject
    Meter metering;

    LongGauge gauge;

    @PostConstruct
    void init() {
        gauge = metering.gaugeBuilder("opponent.angerlevel")
                .ofLongs()
                .setDescription("Meters the anger level of lukes oppnents during a fight")
                .setUnit("percentage")
                .build();
    }

    public void addNewAngerLevel(Long level, Attributes attr) {
        // be aware and careful of dimensions of metric time series (different values of different attributes will create additional dimensions!)
        gauge.set(level, attr);
    }
    //end:metering[]
}
