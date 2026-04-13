package kotlin.io.encoding;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.hssf.record.PaletteRecord;
import org.apache.poi.ss.formula.ptg.AreaErrPtg;

/* compiled from: Base64.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"base64EncodeMap", "", "base64DecodeMap", "", "base64UrlEncodeMap", "base64UrlDecodeMap", "isInMimeAlphabet", "", "symbol", "", "kotlin-stdlib"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class Base64Kt {
    private static final int[] base64DecodeMap;
    private static final byte[] base64EncodeMap = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_MULTIVOLUME, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, AreaErrPtg.sid, 47};
    private static final int[] base64UrlDecodeMap;
    private static final byte[] base64UrlEncodeMap;

    static {
        int[] $this$base64DecodeMap_u24lambda_u241 = new int[256];
        ArraysKt.fill$default($this$base64DecodeMap_u24lambda_u241, -1, 0, 0, 6, (Object) null);
        $this$base64DecodeMap_u24lambda_u241[61] = -2;
        byte[] $this$forEachIndexed$iv = base64EncodeMap;
        int index$iv = 0;
        int length = $this$forEachIndexed$iv.length;
        int i = 0;
        while (i < length) {
            byte item$iv = $this$forEachIndexed$iv[i];
            $this$base64DecodeMap_u24lambda_u241[item$iv] = index$iv;
            i++;
            index$iv++;
        }
        base64DecodeMap = $this$base64DecodeMap_u24lambda_u241;
        base64UrlEncodeMap = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, TarConstants.LF_GNUTYPE_LONGLINK, TarConstants.LF_GNUTYPE_LONGNAME, TarConstants.LF_MULTIVOLUME, 78, 79, 80, 81, 82, TarConstants.LF_GNUTYPE_SPARSE, 84, 85, 86, 87, TarConstants.LF_PAX_EXTENDED_HEADER_UC, 89, 90, 97, 98, 99, 100, 101, 102, TarConstants.LF_PAX_GLOBAL_EXTENDED_HEADER, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, TarConstants.LF_PAX_EXTENDED_HEADER_LC, 121, 122, TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, PaletteRecord.STANDARD_PALETTE_SIZE, 57, 45, 95};
        int[] $this$base64UrlDecodeMap_u24lambda_u243 = new int[256];
        ArraysKt.fill$default($this$base64UrlDecodeMap_u24lambda_u243, -1, 0, 0, 6, (Object) null);
        $this$base64UrlDecodeMap_u24lambda_u243[61] = -2;
        byte[] $this$forEachIndexed$iv2 = base64UrlEncodeMap;
        int index$iv2 = 0;
        int length2 = $this$forEachIndexed$iv2.length;
        int i2 = 0;
        while (i2 < length2) {
            byte item$iv2 = $this$forEachIndexed$iv2[i2];
            $this$base64UrlDecodeMap_u24lambda_u243[item$iv2] = index$iv2;
            i2++;
            index$iv2++;
        }
        base64UrlDecodeMap = $this$base64UrlDecodeMap_u24lambda_u243;
    }

    public static final boolean isInMimeAlphabet(int symbol) {
        return (symbol >= 0 && symbol < base64DecodeMap.length) && base64DecodeMap[symbol] != -1;
    }
}
