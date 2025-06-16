/*

 */
package org.kore.jakartaee.otel;

import io.opentelemetry.api.common.AttributeKey;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;
import java.util.logging.Logger;

/**
 *
 * @author Konrad Renner
 */
public class EscapeBespin {

    private static final Logger LOG = Logger.getLogger(EscapeBespin.class.getName());

    @Inject
    Tracer tracer;

    @Inject
    Span currentSpan;


    public void onHandLost(@ObservesAsync CutOffHandEvent event) {
        Span forkedSpan = tracer.spanBuilder("handLost")
                .setNoParent()
                .addLink(currentSpan.getSpanContext())
                .setAttribute(AttributeKey.stringKey("luke.opponent"), event.opponent())
                .setSpanKind(SpanKind.INTERNAL)
                .startSpan();
        try (Scope scope = forkedSpan.makeCurrent()) {
            LOG.info("I am jumping in the deep, maybe Leia rescues me");
            forkedSpan.addEvent("Luke got rescued");
        } finally {
            forkedSpan.end();
        }

    }
}
