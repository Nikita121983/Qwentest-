package org.apache.logging.log4j.message;

import java.io.Serializable;

/* loaded from: classes10.dex */
public interface Message extends Serializable {
    String getFormattedMessage();

    Object[] getParameters();

    Throwable getThrowable();

    @Deprecated
    default String getFormat() {
        return null;
    }
}
