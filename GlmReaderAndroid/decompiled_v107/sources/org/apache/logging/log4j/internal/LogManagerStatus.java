package org.apache.logging.log4j.internal;

/* loaded from: classes10.dex */
public class LogManagerStatus {
    private static boolean initialized = false;

    public static void setInitialized(final boolean managerStatus) {
        initialized = managerStatus;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}
