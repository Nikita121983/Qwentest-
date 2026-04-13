package org.apache.poi.util;

import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;

/* loaded from: classes10.dex */
public class Configurator {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) Configurator.class);

    public static int getIntValue(String systemProperty, int defaultValue) {
        String property = System.getProperty(systemProperty);
        if (property == null || "".equals(property) || "null".equals(property)) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(property);
        } catch (Exception e) {
            LOG.atError().log("System property -D{} does not contains a valid integer: {}", systemProperty, property);
            return defaultValue;
        }
    }
}
