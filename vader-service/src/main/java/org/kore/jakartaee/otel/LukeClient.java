package org.kore.jakartaee.otel;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/loose")
@RegisterRestClient(configKey = "luke")
@RegisterClientHeaders
//@RegisterProvider(BaggageClientRequestFilter.class)
public interface LukeClient {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response looseHand();
}
