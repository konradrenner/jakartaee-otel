/*

 */
package org.kore.jakartaee.otel;

import io.opentelemetry.api.baggage.Baggage;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.ext.Provider;

/**
 * Workaround because OpenLiberty does not create the baggage header itself in
 * the current version
 *
 * @author Konrad Renner
 */
//tag::filter[]
@Provider
public class BaggageClientRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) {
        Baggage baggage = Baggage.current();

        final var baggageHeader = new StringBuilder();
        baggage.asMap().forEach((key, entry) -> {
            baggageHeader.append(key).append('=').append(entry.getValue()).append(',');
        });

        if (baggageHeader.length() > 0) {
            requestContext.getHeaders().putSingle("baggage", baggageHeader.substring(0, baggageHeader.length() - 1));
        }
    }
}
//end::filter[]