package org.kore.jakartaee.otel;

import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.context.Scope;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Random;

@Path("/cutoff")
public class CutHandResource {

    private static final Logger LOG = Logger.getLogger(CutHandResource.class.getName());

    @Inject
    @RestClient
    LukeClient luke;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response cutOffTheHand() {

        LOG.info("It is useless to resist");

        //tag::fun-with-baggage[]
        var vadersAngerLevel = new Random();

        try (final Scope scope = Baggage.current().toBuilder()
                .put("vadersAngerLevel", Integer.toString(vadersAngerLevel.nextInt(100))).build().makeCurrent()) {

            var lukesAnswere = luke.looseHand().readEntity(String.class);

            reactToLuke(lukesAnswere);

            return Response.ok("Now that I cut off your hand, I can tell you the truth: I am your father!").build();
        } finally {
            Span.current().addEvent("Vader told Luke the truth");
        }
        //end::fun-with-baggage[]
    }

    //tag::fun-with-traces[]
    @WithSpan
    private void reactToLuke(@SpanAttribute("lukesReaction") String lukesAnswere) {
        LOG.log(Level.INFO, "There is no escape, don''t make me destroy you:{0}", lukesAnswere);
    }
    //end::fun-with-traces[]
}
