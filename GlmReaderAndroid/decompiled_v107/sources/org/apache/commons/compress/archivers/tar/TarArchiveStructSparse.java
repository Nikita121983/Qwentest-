package org.apache.commons.compress.archivers.tar;

import java.util.Objects;

/* loaded from: classes9.dex */
public final class TarArchiveStructSparse {
    private final long numbytes;
    private final long offset;

    public TarArchiveStructSparse(long offset, long numBytes) {
        if (offset < 0) {
            throw new IllegalArgumentException("offset must not be negative");
        }
        if (numBytes < 0) {
            throw new IllegalArgumentException("numbytes must not be negative");
        }
        this.offset = offset;
        this.numbytes = numBytes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TarArchiveStructSparse that = (TarArchiveStructSparse) o;
        if (this.offset == that.offset && this.numbytes == that.numbytes) {
            return true;
        }
        return false;
    }

    public long getNumbytes() {
        return this.numbytes;
    }

    public long getOffset() {
        return this.offset;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.offset), Long.valueOf(this.numbytes));
    }

    public String toString() {
        return "TarArchiveStructSparse{offset=" + this.offset + ", numbytes=" + this.numbytes + '}';
    }
}
