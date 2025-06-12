package org.kore.jakartaee.otel;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/loose")
@RegisterRestClient(configKey = "luke")
@RegisterClientHeaders
public interface LukeClient {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response looseHand();
}
