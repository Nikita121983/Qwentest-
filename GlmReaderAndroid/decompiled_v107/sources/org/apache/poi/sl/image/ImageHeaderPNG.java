package org.apache.poi.sl.image;

import java.util.Arrays;
import org.apache.poi.poifs.filesystem.FileMagic;

/* loaded from: classes10.dex */
public final class ImageHeaderPNG {
    private static final int MAGIC_OFFSET = 16;
    private final byte[] data;

    public ImageHeaderPNG(byte[] data) {
        this.data = data;
    }

    public byte[] extractPNG() {
        if (this.data.length >= 16) {
            byte[] newData = Arrays.copyOfRange(this.data, 16, this.data.length);
            if (FileMagic.valueOf(newData) == FileMagic.PNG) {
                return newData;
            }
        }
        return this.data;
    }
}
