/*

 */
package org.kore.jakartaee.otel;

import jakarta.enterprise.event.ObservesAsync;
import java.util.logging.Logger;

/**
 *
 * @author Konrad Renner
 */
public class EscapeBespin {

    private static final Logger LOG = Logger.getLogger(EscapeBespin.class.getName());

    public void onHandLost(@ObservesAsync CutOffHandEvent event) {
        LOG.info("I am jumping in the deep, maybe Leia rescues me");
    }
}
