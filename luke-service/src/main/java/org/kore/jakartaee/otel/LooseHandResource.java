package org.kore.jakartaee.otel;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/loose")
public class LooseHandResource {

    private static final Logger LOG = Logger.getLogger(LooseHandResource.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response looseHand() {
        LOG.info("I never join you!");
        return Response.ok("Aaaah!").build();
    }
}
