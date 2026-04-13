package org.apache.logging.log4j.spi;

import java.io.Closeable;

/* loaded from: classes10.dex */
public interface LoggerAdapter<L> extends Closeable {
    L getLogger(String name);
}
