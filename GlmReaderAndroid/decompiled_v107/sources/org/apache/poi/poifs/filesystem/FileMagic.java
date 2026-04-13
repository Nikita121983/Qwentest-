package org.apache.poi.poifs.filesystem;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.usermodel.HSSFShapeTypes;
import org.apache.poi.poifs.storage.HeaderBlockConstants;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LocaleUtil;

/* loaded from: classes10.dex */
public enum FileMagic {
    OLE2(HeaderBlockConstants._signature),
    OOXML(80, 75, 3, 4),
    XML(60, 63, 120, 109, 108),
    BIFF2(9, 0, 4, 0, 0, 0, 63, 0),
    BIFF3(9, 2, 6, 0, 0, 0, 63, 0),
    BIFF4(new byte[]{9, 4, 6, 0, 0, 0, 63, 0}, new byte[]{9, 4, 6, 0, 0, 0, 0, 1}),
    MSWRITE(new byte[]{TarConstants.LF_LINK, -66, 0, 0}, new byte[]{TarConstants.LF_SYMLINK, -66, 0, 0}),
    RTF("{\\rtf"),
    PDF("%PDF"),
    HTML("<!DOCTYP", "<html", "\n\r<html", "\r\n<html", "\r<html", "\n<html", "<HTML", "\r\n<HTML", "\n\r<HTML", "\r<HTML", "\n<HTML"),
    WORD2(219, 165, 45, 0),
    JPEG(new byte[]{-1, -40, -1, -37}, new byte[]{-1, -40, -1, -32, 63, 63, 74, 70, 73, 70, 0, 1}, new byte[]{-1, -40, -1, -18}, new byte[]{-1, -40, -1, -31, 63, 63, 69, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 105, 102, 0, 0}),
    GIF("GIF87a", "GIF89a"),
    PNG(137, 80, 78, 71, 13, 10, 26, 10),
    TIFF("II*\u0000", "MM\u0000*"),
    WMF(215, 205, HSSFShapeTypes.ActionButtonDocument, 154),
    EMF(1, 0, 0, 0, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 32, 69, 77, 70),
    BMP(66, 77),
    UNKNOWN(new byte[0]);

    static final int MAX_PATTERN_LENGTH = 44;
    final byte[][] magic;

    FileMagic(long magic) {
        this.magic = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 1, 8);
        LittleEndian.putLong(this.magic[0], 0, magic);
    }

    FileMagic(int... magic) {
        byte[] one = new byte[magic.length];
        for (int i = 0; i < magic.length; i++) {
            one[i] = (byte) (magic[i] & 255);
        }
        this.magic = new byte[][]{one};
    }

    FileMagic(byte[]... magic) {
        this.magic = magic;
    }

    FileMagic(String... magic) {
        this.magic = new byte[magic.length];
        int i = 0;
        int length = magic.length;
        int i2 = 0;
        while (i2 < length) {
            String s = magic[i2];
            this.magic[i] = s.getBytes(LocaleUtil.CHARSET_1252);
            i2++;
            i++;
        }
    }

    public static FileMagic valueOf(byte[] magic) {
        for (FileMagic fm : values()) {
            for (byte[] ma : fm.magic) {
                if (magic.length >= ma.length && findMagic(ma, magic)) {
                    return fm;
                }
            }
        }
        return UNKNOWN;
    }

    private static boolean findMagic(byte[] expected, byte[] actual) {
        int i = 0;
        int length = expected.length;
        int i2 = 0;
        while (i2 < length) {
            int expectedByte = expected[i2];
            int i3 = i + 1;
            if (actual[i] != expectedByte && expectedByte != 63) {
                return false;
            }
            i2++;
            i = i3;
        }
        return true;
    }

    public static FileMagic valueOf(File inp) throws IOException {
        InputStream fis = Files.newInputStream(inp.toPath(), new OpenOption[0]);
        try {
            byte[] data = new byte[44];
            int read = IOUtils.readFully(fis, data, 0, 44);
            if (read == -1) {
                FileMagic fileMagic = UNKNOWN;
                if (fis != null) {
                    fis.close();
                }
                return fileMagic;
            }
            FileMagic valueOf = valueOf(Arrays.copyOf(data, read));
            if (fis != null) {
                fis.close();
            }
            return valueOf;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static FileMagic valueOf(InputStream inp) throws IOException {
        if (!inp.markSupported()) {
            throw new IOException("getFileMagic() only operates on streams which support mark(int)");
        }
        byte[] data = IOUtils.peekFirstNBytes(inp, 44);
        return valueOf(data);
    }

    public static InputStream prepareToCheckMagic(InputStream stream) {
        if (stream.markSupported()) {
            return stream;
        }
        return new BufferedInputStream(stream);
    }
}
