package org.apache.commons.compress.archivers.zip;

import java.util.zip.ZipException;
import kotlin.jvm.internal.ShortCompanionObject;

/* loaded from: classes9.dex */
public class ResourceAlignmentExtraField implements ZipExtraField {
    private static final int ALLOW_METHOD_MESSAGE_CHANGE_FLAG = 32768;
    public static final int BASE_SIZE = 2;
    public static final ZipShort ID = new ZipShort(41246);
    private static final int MAX_ALIGNMENT = 32767;
    private short alignment;
    private boolean allowMethodChange;
    private int padding;

    public ResourceAlignmentExtraField() {
    }

    public ResourceAlignmentExtraField(int alignment) {
        this(alignment, false);
    }

    public ResourceAlignmentExtraField(int alignment, boolean allowMethodChange) {
        this(alignment, allowMethodChange, 0);
    }

    public ResourceAlignmentExtraField(int alignment, boolean allowMethodChange, int padding) {
        if (alignment < 0 || alignment > MAX_ALIGNMENT) {
            throw new IllegalArgumentException("Alignment must be between 0 and 0x7fff, was: " + alignment);
        }
        if (padding < 0) {
            throw new IllegalArgumentException("Padding must not be negative, was: " + padding);
        }
        this.alignment = (short) alignment;
        this.allowMethodChange = allowMethodChange;
        this.padding = padding;
    }

    public boolean allowMethodChange() {
        return this.allowMethodChange;
    }

    public short getAlignment() {
        return this.alignment;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getCentralDirectoryData() {
        return ZipShort.getBytes(this.alignment | (this.allowMethodChange ? ShortCompanionObject.MIN_VALUE : (short) 0));
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort(2);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getHeaderId() {
        return ID;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] content = new byte[this.padding + 2];
        ZipShort.putShort(this.alignment | (this.allowMethodChange ? ShortCompanionObject.MIN_VALUE : (short) 0), content, 0);
        return content;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.padding + 2);
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        if (length < 2) {
            throw new ZipException("Too short content for ResourceAlignmentExtraField (0xa11e): " + length);
        }
        int alignmentValue = ZipShort.getValue(buffer, offset);
        this.alignment = (short) (alignmentValue & MAX_ALIGNMENT);
        this.allowMethodChange = (32768 & alignmentValue) != 0;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipExtraField
    public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {
        parseFromCentralDirectoryData(buffer, offset, length);
        this.padding = length - 2;
    }
}
