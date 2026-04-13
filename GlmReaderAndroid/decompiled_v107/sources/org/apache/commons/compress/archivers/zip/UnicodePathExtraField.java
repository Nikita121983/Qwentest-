package org.apache.commons.compress.archivers.zip;

/* loaded from: classes9.dex */
public class UnicodePathExtraField extends AbstractUnicodeExtraField {
    public static final ZipShort UPATH_ID = new ZipShort(28789);

    public UnicodePathExtraField() {
    }

    public UnicodePathExtraField(String name, byte[] bytes) {
        super(name, bytes);
    }

    public UnicodePathExtraField(String text, byte[] bytes, int off, int len) {
        super(text, bytes, off, len);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return UPATH_ID;
    }
}
