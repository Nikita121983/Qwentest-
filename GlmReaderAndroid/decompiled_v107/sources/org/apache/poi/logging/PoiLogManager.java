package org.apache.poi.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.ExceptionUtil;

/* loaded from: classes10.dex */
public final class PoiLogManager {
    private static long LAST_TIME = 0;
    private static final long SLEEP_TIME = 600000;

    private PoiLogManager() {
    }

    public static Logger getLogger(Class<?> clz) {
        try {
            Logger logger = LogManager.getLogger(clz);
            if (logger == null) {
                if (shouldLog()) {
                    System.err.println("[PoiLogManager] Log4J returned null logger. Falling back to No-Op logger.");
                }
                return NoOpLogger.INSTANCE;
            }
            return logger;
        } catch (Throwable t) {
            if (!ExceptionUtil.isFatal(t) && shouldLog()) {
                System.err.println("[PoiLogManager] Issue loading Log4J. Falling back to No-Op logger.");
                t.printStackTrace();
            }
            return NoOpLogger.INSTANCE;
        }
    }

    public static Logger getLogger(String name) {
        try {
            Logger logger = LogManager.getLogger(name);
            if (logger == null) {
                if (shouldLog()) {
                    System.err.println("[PoiLogManager] Log4J returned null logger. Falling back to No-Op logger.");
                }
                return NoOpLogger.INSTANCE;
            }
            return logger;
        } catch (Throwable t) {
            if (!ExceptionUtil.isFatal(t) && shouldLog()) {
                System.err.println("[PoiLogManager] Issue loading Log4J. Falling back to No-Op logger.");
                t.printStackTrace();
            }
            return NoOpLogger.INSTANCE;
        }
    }

    private static synchronized boolean shouldLog() {
        synchronized (PoiLogManager.class) {
            long time = System.currentTimeMillis();
            if (time <= LAST_TIME + SLEEP_TIME) {
                return false;
            }
            LAST_TIME = time;
            return true;
        }
    }
}
