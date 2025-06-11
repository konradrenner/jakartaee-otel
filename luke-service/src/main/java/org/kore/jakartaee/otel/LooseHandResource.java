package org.kore.jakartaee.otel;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/loose")
public class LooseHandResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response looseHand() {
        return Response.ok("Aaaah!").build();
    }
}
