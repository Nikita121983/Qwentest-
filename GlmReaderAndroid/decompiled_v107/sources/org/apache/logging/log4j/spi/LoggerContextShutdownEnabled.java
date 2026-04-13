package org.apache.logging.log4j.spi;

import java.util.List;

/* loaded from: classes10.dex */
public interface LoggerContextShutdownEnabled {
    void addShutdownListener(LoggerContextShutdownAware listener);

    List<LoggerContextShutdownAware> getListeners();
}
