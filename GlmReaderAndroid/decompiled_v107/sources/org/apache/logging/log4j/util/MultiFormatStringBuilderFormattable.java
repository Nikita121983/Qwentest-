package org.apache.logging.log4j.util;

import org.apache.logging.log4j.message.MultiformatMessage;

/* loaded from: classes10.dex */
public interface MultiFormatStringBuilderFormattable extends MultiformatMessage, StringBuilderFormattable {
    void formatTo(String[] formats, StringBuilder buffer);
}
