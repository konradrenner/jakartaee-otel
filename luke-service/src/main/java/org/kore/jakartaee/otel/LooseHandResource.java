package org.kore.jakartaee.otel;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.common.Attributes;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;
import java.util.logging.Logger;

@Path("/loose")
public class LooseHandResource {

    private static final Logger LOG = Logger.getLogger(LooseHandResource.class.getName());

    @Inject
    Baggage currentBaggage;

    @Inject
    OpponentAngerLevelMetering metering;

    @Inject
    Event<CutOffHandEvent> handCutOff;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response looseHand() {

        LOG.info("Angry Level of Darth Vader:" + currentBaggage.getEntryValue("vadersAngerLevel"));

        Optional.ofNullable(currentBaggage.getEntryValue("vadersAngerLevel"))
                .map(Long::valueOf)
                .ifPresent(level -> metering.addNewAngerLevel(level, Attributes.of(AttributeKey.stringKey("luke.opponent"), "Darth Vader"))); // be careful with attributes, they add dimensions to your metric!

        LOG.info("I never join you!");

        handCutOff.fireAsync(new CutOffHandEvent("Darth Vader"));

        return Response.ok("Aaaah!").build();
    }
}
