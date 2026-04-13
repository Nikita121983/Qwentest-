package org.apache.poi.ooxml.util;

import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class NumberHelper {
    private NumberHelper() {
    }

    public static double toDouble(Object number) {
        if (number instanceof Number) {
            return ((Number) number).doubleValue();
        }
        if (number instanceof String) {
            return Double.parseDouble((String) number);
        }
        throw new IllegalArgumentException("Cannot convert of class" + number.getClass().getName() + " to double");
    }
}
