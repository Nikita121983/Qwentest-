package org.apache.commons.compress.compressors.gzip;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import kotlin.UByte;
import org.apache.commons.compress.compressors.gzip.ExtraField;

/* loaded from: classes9.dex */
public final class ExtraField implements Iterable<SubField> {
    private static final int MAX_SIZE = 65535;
    private static final byte[] ZERO_BYTES = new byte[0];
    private final List<SubField> subFields = new ArrayList();
    private int totalSize;

    /* loaded from: classes9.dex */
    public static final class SubField {
        private final byte[] payload;
        private final byte si1;
        private final byte si2;

        SubField(byte si1, byte si2, byte[] payload) {
            this.si1 = si1;
            this.si2 = si2;
            this.payload = payload;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SubField other = (SubField) obj;
            if (Arrays.equals(this.payload, other.payload) && this.si1 == other.si1 && this.si2 == other.si2) {
                return true;
            }
            return false;
        }

        public String getId() {
            return String.valueOf(new char[]{(char) (this.si1 & UByte.MAX_VALUE), (char) (this.si2 & UByte.MAX_VALUE)});
        }

        public byte[] getPayload() {
            return this.payload;
        }

        public int hashCode() {
            int result = (1 * 31) + Arrays.hashCode(this.payload);
            return (result * 31) + Objects.hash(Byte.valueOf(this.si1), Byte.valueOf(this.si2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExtraField fromBytes(byte[] bytes) throws IOException {
        if (bytes == null) {
            return null;
        }
        ExtraField extra = new ExtraField();
        int pos = 0;
        while (pos <= bytes.length - 4) {
            int pos2 = pos + 1;
            byte si1 = bytes[pos];
            int pos3 = pos2 + 1;
            byte si2 = bytes[pos2];
            int pos4 = pos3 + 1;
            int pos5 = pos4 + 1;
            int sublen = (bytes[pos3] & 255) | ((bytes[pos4] & 255) << 8);
            if (sublen > bytes.length - pos5) {
                throw new IOException("Extra subfield lenght exceeds remaining bytes in extra: " + sublen + " > " + (bytes.length - pos5));
            }
            byte[] payload = new byte[sublen];
            System.arraycopy(bytes, pos5, payload, 0, sublen);
            int pos6 = pos5 + sublen;
            extra.subFields.add(new SubField(si1, si2, payload));
            extra.totalSize = pos6;
            pos = pos6;
        }
        if (pos < bytes.length) {
            throw new IOException("" + (bytes.length - pos) + " remaining bytes not used to parse an extra subfield.");
        }
        return extra;
    }

    public ExtraField addSubField(String id, byte[] payload) throws IOException {
        Objects.requireNonNull(id, "payload");
        Objects.requireNonNull(payload, "payload");
        if (id.length() != 2) {
            throw new IllegalArgumentException("Subfield id must be a 2 character ISO-8859-1 string.");
        }
        char si1 = id.charAt(0);
        char si2 = id.charAt(1);
        if ((si1 & 65280) != 0 || (65280 & si2) != 0) {
            throw new IllegalArgumentException("Subfield id must be a 2 character ISO-8859-1 string.");
        }
        SubField f = new SubField((byte) (si1 & 255), (byte) (si2 & 255), payload);
        int len = payload.length + 4;
        if (this.totalSize + len > 65535) {
            throw new IOException("Extra subfield '" + f.getId() + "' too big (extras total size is already at " + this.totalSize + ")");
        }
        this.subFields.add(f);
        this.totalSize += len;
        return this;
    }

    public void clear() {
        this.subFields.clear();
        this.totalSize = 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExtraField other = (ExtraField) obj;
        if (Objects.equals(this.subFields, other.subFields) && this.totalSize == other.totalSize) {
            return true;
        }
        return false;
    }

    public SubField findFirstSubField(final String id) {
        return this.subFields.stream().filter(new Predicate() { // from class: org.apache.commons.compress.compressors.gzip.ExtraField$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((ExtraField.SubField) obj).getId().equals(id);
                return equals;
            }
        }).findFirst().orElse(null);
    }

    public int getEncodedSize() {
        return this.totalSize;
    }

    public SubField getSubField(int index) {
        return this.subFields.get(index);
    }

    public int hashCode() {
        return Objects.hash(this.subFields, Integer.valueOf(this.totalSize));
    }

    public boolean isEmpty() {
        return this.subFields.isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<SubField> iterator() {
        return Collections.unmodifiableList(this.subFields).iterator();
    }

    public int size() {
        return this.subFields.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] toByteArray() {
        if (this.subFields.isEmpty()) {
            return ZERO_BYTES;
        }
        byte[] ba = new byte[this.totalSize];
        int pos = 0;
        for (SubField f : this.subFields) {
            int pos2 = pos + 1;
            ba[pos] = f.si1;
            int pos3 = pos2 + 1;
            ba[pos2] = f.si2;
            int pos4 = pos3 + 1;
            ba[pos3] = (byte) (f.payload.length & 255);
            int pos5 = pos4 + 1;
            ba[pos4] = (byte) (f.payload.length >>> 8);
            System.arraycopy(f.payload, 0, ba, pos5, f.payload.length);
            pos = pos5 + f.payload.length;
        }
        return ba;
    }
}
