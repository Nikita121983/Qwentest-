package org.apache.commons.io;

import java.nio.charset.Charset;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes9.dex */
public enum StandardLineSeparator {
    CR(StringUtils.CR),
    CRLF("\r\n"),
    LF(StringUtils.LF);

    private final String lineSeparator;

    StandardLineSeparator(String lineSeparator) {
        this.lineSeparator = (String) Objects.requireNonNull(lineSeparator, "lineSeparator");
    }

    public byte[] getBytes(Charset charset) {
        return this.lineSeparator.getBytes(charset);
    }

    public String getString() {
        return this.lineSeparator;
    }
}
