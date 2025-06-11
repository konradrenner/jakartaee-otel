package org.kore.jakartaee.otel;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
        var lukesAnswere = luke.looseHand();
        LOG.log(Level.INFO, "There is no escape, don't make me destroy you: {0}", lukesAnswere.getEntity());
        return Response.ok("Now that I cut off your hand, I can tell you the truth: I am your father!").build();
    }
}
