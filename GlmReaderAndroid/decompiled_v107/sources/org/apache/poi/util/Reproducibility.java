package org.apache.poi.util;

import java.io.IOException;
import org.apache.commons.io.function.IORunnable;

/* loaded from: classes10.dex */
public class Reproducibility {
    private static boolean IS_SOURCE_DATE_EPOCH;

    static {
        IS_SOURCE_DATE_EPOCH = System.getenv("SOURCE_DATE_EPOCH") != null;
    }

    public static boolean isSourceDateEpoch() {
        return IS_SOURCE_DATE_EPOCH;
    }

    public static void runWithSourceDateEpoch(IORunnable r) throws IOException {
        boolean before = IS_SOURCE_DATE_EPOCH;
        IS_SOURCE_DATE_EPOCH = true;
        try {
            r.run();
        } finally {
            IS_SOURCE_DATE_EPOCH = before;
        }
    }
}
