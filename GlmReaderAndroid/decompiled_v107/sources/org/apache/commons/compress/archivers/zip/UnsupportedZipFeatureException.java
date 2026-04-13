package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.util.zip.ZipException;

/* loaded from: classes9.dex */
public class UnsupportedZipFeatureException extends ZipException {
    private static final long serialVersionUID = 20161219;
    private final transient ZipArchiveEntry entry;
    private final Feature reason;

    /* loaded from: classes9.dex */
    public static class Feature implements Serializable {
        private static final long serialVersionUID = 4112582948775420359L;
        private final String name;
        public static final Feature ENCRYPTION = new Feature("encryption");
        public static final Feature METHOD = new Feature("compression method");
        public static final Feature DATA_DESCRIPTOR = new Feature("data descriptor");
        public static final Feature SPLITTING = new Feature("splitting");
        public static final Feature UNKNOWN_COMPRESSED_SIZE = new Feature("unknown compressed size");

        private Feature(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }

    public UnsupportedZipFeatureException(Feature reason) {
        super("Unsupported feature " + reason + " used in archive.");
        this.reason = reason;
        this.entry = null;
    }

    public UnsupportedZipFeatureException(Feature reason, ZipArchiveEntry entry) {
        super("Unsupported feature " + reason + " used in entry " + entry.getName());
        this.reason = reason;
        this.entry = entry;
    }

    public UnsupportedZipFeatureException(ZipMethod method, ZipArchiveEntry entry) {
        super("Unsupported compression method " + entry.getMethod() + " (" + method.name() + ") used in entry " + entry.getName());
        this.reason = Feature.METHOD;
        this.entry = entry;
    }

    public ZipArchiveEntry getEntry() {
        return this.entry;
    }

    public Feature getFeature() {
        return this.reason;
    }
}
