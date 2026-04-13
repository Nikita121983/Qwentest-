package org.apache.commons.compress.archivers.dump;

import org.apache.commons.compress.archivers.ArchiveException;

/* loaded from: classes9.dex */
public class DumpArchiveException extends ArchiveException {
    private static final long serialVersionUID = 1;

    public DumpArchiveException() {
    }

    public DumpArchiveException(String message) {
        super(message);
    }

    public DumpArchiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DumpArchiveException(Throwable cause) {
        super(cause);
    }
}
