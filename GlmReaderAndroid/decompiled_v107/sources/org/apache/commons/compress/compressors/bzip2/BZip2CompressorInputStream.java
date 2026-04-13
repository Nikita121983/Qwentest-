package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.apache.commons.io.input.CloseShieldInputStream;

/* loaded from: classes9.dex */
public class BZip2CompressorInputStream extends CompressorInputStream implements BZip2Constants, InputStreamStatistics {
    private static final int EOF = 0;
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int START_BLOCK_STATE = 1;
    private BitInputStream bin;
    private boolean blockRandomised;
    private int blockSize100k;
    private int computedCombinedCRC;
    private final CRC crc;
    private int currentState;
    private Data data;
    private final boolean decompressConcatenated;
    private int last;
    private int nInUse;
    private int origPtr;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    private int su_ch2;
    private int su_chPrev;
    private int su_count;
    private int su_i2;
    private int su_j2;
    private int su_rNToGo;
    private int su_rTPos;
    private int su_tPos;
    private char su_z;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Data {
        final byte[] ll8;
        int[] tt;
        final boolean[] inUse = new boolean[256];
        final byte[] seqToUnseq = new byte[256];
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final int[] unzftab = new int[256];
        final int[][] limit = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final int[][] base = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final int[][] perm = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final int[] minLens = new int[6];
        final int[] cftab = new int[257];
        final char[] getAndMoveToFrontDecode_yy = new char[256];
        final char[][] temp_charArray2d = (char[][]) Array.newInstance((Class<?>) Character.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final byte[] recvDecodingTables_pos = new byte[6];

        Data(int blockSize100k) {
            this.ll8 = new byte[BZip2Constants.BASEBLOCKSIZE * blockSize100k];
        }

        int[] initTT(int length) {
            int[] ttShadow = this.tt;
            if (ttShadow == null || ttShadow.length < length) {
                int[] ttShadow2 = new int[length];
                this.tt = ttShadow2;
                return ttShadow2;
            }
            return ttShadow;
        }
    }

    private static boolean bsGetBit(BitInputStream bin) throws IOException {
        return bsR(bin, 1) != 0;
    }

    private static int bsGetInt(BitInputStream bin) throws IOException {
        return bsR(bin, 32);
    }

    private static char bsGetUByte(BitInputStream bin) throws IOException {
        return (char) bsR(bin, 8);
    }

    private static int bsR(BitInputStream bin, int n) throws IOException {
        long thech = bin.readBits(n);
        if (thech < 0) {
            throw new IOException("Unexpected end of stream");
        }
        return (int) thech;
    }

    private static void checkBounds(int checkVal, int limitExclusive, String name) throws IOException {
        if (checkVal < 0) {
            throw new IOException("Corrupted input, " + name + " value negative");
        }
        if (checkVal >= limitExclusive) {
            throw new IOException("Corrupted input, " + name + " value too big");
        }
    }

    private static void hbCreateDecodeTables(int[] limit, int[] base, int[] perm, char[] length, int minLen, int maxLen, int alphaSize) throws IOException {
        int pp = 0;
        for (int i = minLen; i <= maxLen; i++) {
            for (int j = 0; j < alphaSize; j++) {
                if (length[j] == i) {
                    perm[pp] = j;
                    pp++;
                }
            }
        }
        int i2 = 23;
        while (true) {
            i2--;
            if (i2 <= 0) {
                break;
            }
            base[i2] = 0;
            limit[i2] = 0;
        }
        for (int i3 = 0; i3 < alphaSize; i3++) {
            char c = length[i3];
            checkBounds(c, BZip2Constants.MAX_ALPHA_SIZE, "length");
            int i4 = c + 1;
            base[i4] = base[i4] + 1;
        }
        int b = base[0];
        for (int i5 = 1; i5 < 23; i5++) {
            b += base[i5];
            base[i5] = b;
        }
        int i6 = minLen;
        int vec = 0;
        int b2 = base[i6];
        while (i6 <= maxLen) {
            int nb = base[i6 + 1];
            int vec2 = vec + (nb - b2);
            b2 = nb;
            limit[i6] = vec2 - 1;
            vec = vec2 << 1;
            i6++;
        }
        for (int i7 = minLen + 1; i7 <= maxLen; i7++) {
            base[i7] = ((limit[i7 - 1] + 1) << 1) - base[i7];
        }
    }

    public static boolean matches(byte[] signature, int length) {
        return length >= 3 && signature[0] == 66 && signature[1] == 90 && signature[2] == 104;
    }

    public BZip2CompressorInputStream(InputStream in) throws IOException {
        this(in, false);
    }

    public BZip2CompressorInputStream(InputStream in, boolean decompressConcatenated) throws IOException {
        this.crc = new CRC();
        this.currentState = 1;
        this.bin = new BitInputStream(in == System.in ? CloseShieldInputStream.wrap(in) : in, ByteOrder.BIG_ENDIAN);
        this.decompressConcatenated = decompressConcatenated;
        init(true);
        initBlock();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        BitInputStream bitInputStream = this.bin;
        if (bitInputStream != null) {
            try {
                bitInputStream.close();
            } finally {
                this.data = null;
                this.bin = null;
            }
        }
    }

    private boolean complete() throws IOException {
        this.storedCombinedCRC = bsGetInt(this.bin);
        this.currentState = 0;
        this.data = null;
        if (this.storedCombinedCRC == this.computedCombinedCRC) {
            return (this.decompressConcatenated && init(false)) ? false : true;
        }
        throw new IOException("BZip2 CRC error");
    }

    private void createHuffmanDecodingTables(int alphaSize, int nGroups) throws IOException {
        Data dataShadow = this.data;
        char[][] len = dataShadow.temp_charArray2d;
        int[] minLens = dataShadow.minLens;
        int[][] limit = dataShadow.limit;
        int[][] base = dataShadow.base;
        int[][] perm = dataShadow.perm;
        for (int t = 0; t < nGroups; t++) {
            int minLen = 32;
            int maxLen = 0;
            char[] len_t = len[t];
            int i = alphaSize;
            while (true) {
                i--;
                if (i >= 0) {
                    char lent = len_t[i];
                    if (lent > maxLen) {
                        maxLen = lent;
                    }
                    if (lent < minLen) {
                        minLen = lent;
                    }
                }
            }
            int minLen2 = minLen;
            hbCreateDecodeTables(limit[t], base[t], perm[t], len[t], minLen2, maxLen, alphaSize);
            minLens[t] = minLen2;
        }
    }

    private void endBlock() throws IOException {
        int computedBlockCRC = this.crc.getValue();
        if (this.storedBlockCRC != computedBlockCRC) {
            this.computedCombinedCRC = (this.storedCombinedCRC << 1) | (this.storedCombinedCRC >>> 31);
            this.computedCombinedCRC ^= this.storedBlockCRC;
            throw new IOException("BZip2 CRC error");
        }
        this.computedCombinedCRC = (this.computedCombinedCRC << 1) | (this.computedCombinedCRC >>> 31);
        this.computedCombinedCRC ^= computedBlockCRC;
    }

    private void getAndMoveToFrontDecode() throws IOException {
        int nextSym;
        char[] yy;
        String str;
        char c;
        BitInputStream bin = this.bin;
        this.origPtr = bsR(bin, 24);
        recvDecodingTables();
        Data dataShadow = this.data;
        byte[] ll8 = dataShadow.ll8;
        int[] unzftab = dataShadow.unzftab;
        byte[] selector = dataShadow.selector;
        byte[] seqToUnseq = dataShadow.seqToUnseq;
        char[] yy2 = dataShadow.getAndMoveToFrontDecode_yy;
        int[] minLens = dataShadow.minLens;
        int[][] limit = dataShadow.limit;
        int[][] base = dataShadow.base;
        int[][] perm = dataShadow.perm;
        int limitLast = this.blockSize100k * BZip2Constants.BASEBLOCKSIZE;
        int i = 256;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            yy2[i] = (char) i;
            unzftab[i] = 0;
        }
        int groupNo = 0;
        int groupPos = 49;
        int eob = this.nInUse + 1;
        int nextSym2 = getAndMoveToFrontDecode0();
        int zt = selector[0] & UByte.MAX_VALUE;
        String str2 = "zt";
        checkBounds(zt, 6, "zt");
        int[] base_zt = base[zt];
        int[] limit_zt = limit[zt];
        int[] perm_zt = perm[zt];
        int minLens_zt = minLens[zt];
        int lastShadow = zt;
        int lastShadow2 = -1;
        int zn = nextSym2;
        while (zn != eob) {
            byte[] seqToUnseq2 = seqToUnseq;
            int[] minLens2 = minLens;
            int[][] limit2 = limit;
            int[][] base2 = base;
            int[][] perm2 = perm;
            int groupNo2 = groupNo;
            if (zn == 0) {
                nextSym = zn;
            } else if (zn == 1) {
                nextSym = zn;
            } else {
                lastShadow2++;
                if (lastShadow2 >= limitLast) {
                    throw new IOException("Block overrun in MTF, " + lastShadow2 + " exceeds " + limitLast);
                }
                checkBounds(zn, 257, "nextSym");
                char tmp = yy2[zn - 1];
                checkBounds(tmp, 256, "yy");
                int i2 = seqToUnseq2[tmp] & UByte.MAX_VALUE;
                unzftab[i2] = unzftab[i2] + 1;
                ll8[lastShadow2] = seqToUnseq2[tmp];
                if (zn <= 16) {
                    int j = zn - 1;
                    while (j > 0) {
                        int j2 = j - 1;
                        yy2[j] = yy2[j2];
                        j = j2;
                    }
                    c = 0;
                } else {
                    c = 0;
                    System.arraycopy(yy2, 0, yy2, 1, zn - 1);
                }
                yy2[c] = tmp;
                if (groupPos == 0) {
                    groupNo = groupNo2 + 1;
                    checkBounds(groupNo, BZip2Constants.MAX_SELECTORS, "groupNo");
                    int zt2 = selector[groupNo] & UByte.MAX_VALUE;
                    checkBounds(zt2, 6, str2);
                    int[] base_zt2 = base2[zt2];
                    int[] limit_zt2 = limit2[zt2];
                    int[] perm_zt2 = perm2[zt2];
                    limit_zt = limit_zt2;
                    perm_zt = perm_zt2;
                    minLens_zt = minLens2[zt2];
                    groupPos = 49;
                    lastShadow = zt2;
                    base_zt = base_zt2;
                } else {
                    groupPos--;
                    groupNo = groupNo2;
                }
                int zn2 = minLens_zt;
                checkBounds(zn2, BZip2Constants.MAX_ALPHA_SIZE, "zn");
                int zvec = bsR(bin, zn2);
                while (zvec > limit_zt[zn2]) {
                    zn2++;
                    checkBounds(zn2, BZip2Constants.MAX_ALPHA_SIZE, "zn");
                    zvec = (zvec << 1) | bsR(bin, 1);
                }
                int idx = zvec - base_zt[zn2];
                checkBounds(idx, BZip2Constants.MAX_ALPHA_SIZE, "zvec");
                zn = perm_zt[idx];
                seqToUnseq = seqToUnseq2;
                minLens = minLens2;
                limit = limit2;
                base = base2;
                perm = perm2;
            }
            int s = -1;
            int zvec2 = nextSym;
            int minLens_zt2 = minLens_zt;
            int[] perm_zt3 = perm_zt;
            int[] limit_zt3 = limit_zt;
            int[] base_zt3 = base_zt;
            int zt3 = lastShadow;
            int groupPos2 = groupPos;
            int groupPos3 = 1;
            while (true) {
                if (zvec2 == 0) {
                    s += groupPos3;
                    yy = yy2;
                } else {
                    yy = yy2;
                    if (zvec2 != 1) {
                        break;
                    } else {
                        s += groupPos3 << 1;
                    }
                }
                if (groupPos2 == 0) {
                    int nextSym3 = groupNo2 + 1;
                    groupPos2 = 49;
                    checkBounds(nextSym3, BZip2Constants.MAX_SELECTORS, "groupNo");
                    int zt4 = selector[nextSym3] & UByte.MAX_VALUE;
                    groupNo2 = nextSym3;
                    checkBounds(zt4, 6, str2);
                    base_zt3 = base2[zt4];
                    limit_zt3 = limit2[zt4];
                    perm_zt3 = perm2[zt4];
                    minLens_zt2 = minLens2[zt4];
                    zt3 = zt4;
                } else {
                    groupPos2--;
                }
                int zn3 = minLens_zt2;
                checkBounds(zn3, BZip2Constants.MAX_ALPHA_SIZE, "zn");
                int zvec3 = bsR(bin, zn3);
                while (true) {
                    str = str2;
                    if (zvec3 > limit_zt3[zn3]) {
                        zn3++;
                        checkBounds(zn3, BZip2Constants.MAX_ALPHA_SIZE, "zn");
                        zvec3 = (zvec3 << 1) | bsR(bin, 1);
                        str2 = str;
                    }
                }
                int tmp2 = zvec3 - base_zt3[zn3];
                checkBounds(tmp2, BZip2Constants.MAX_ALPHA_SIZE, "zvec");
                zvec2 = perm_zt3[tmp2];
                groupPos3 <<= 1;
                bin = bin;
                yy2 = yy;
                str2 = str;
            }
            BitInputStream bin2 = bin;
            int nextSym4 = zvec2;
            String str3 = str2;
            checkBounds(s, this.data.ll8.length, "s");
            char c2 = yy[0];
            checkBounds(c2, 256, "yy");
            byte ch = seqToUnseq2[c2];
            int i3 = ch & UByte.MAX_VALUE;
            unzftab[i3] = unzftab[i3] + s + 1;
            int lastShadow3 = lastShadow2 + 1;
            lastShadow2 = lastShadow3 + s;
            checkBounds(lastShadow2, this.data.ll8.length, "lastShadow");
            Arrays.fill(ll8, lastShadow3, lastShadow2 + 1, ch);
            if (lastShadow2 >= limitLast) {
                throw new IOException("Block overrun while expanding RLE in MTF, " + lastShadow2 + " exceeds " + limitLast);
            }
            groupPos = groupPos2;
            bin = bin2;
            lastShadow = zt3;
            base_zt = base_zt3;
            limit_zt = limit_zt3;
            perm_zt = perm_zt3;
            seqToUnseq = seqToUnseq2;
            minLens = minLens2;
            limit = limit2;
            base = base2;
            perm = perm2;
            groupNo = groupNo2;
            minLens_zt = minLens_zt2;
            yy2 = yy;
            zn = nextSym4;
            str2 = str3;
        }
        this.last = lastShadow2;
    }

    private int getAndMoveToFrontDecode0() throws IOException {
        Data dataShadow = this.data;
        int zt = dataShadow.selector[0] & UByte.MAX_VALUE;
        checkBounds(zt, 6, "zt");
        int[] limit_zt = dataShadow.limit[zt];
        int zn = dataShadow.minLens[zt];
        checkBounds(zn, BZip2Constants.MAX_ALPHA_SIZE, "zn");
        int zvec = bsR(this.bin, zn);
        while (zvec > limit_zt[zn]) {
            zn++;
            checkBounds(zn, BZip2Constants.MAX_ALPHA_SIZE, "zn");
            zvec = (zvec << 1) | bsR(this.bin, 1);
        }
        int tmp = zvec - dataShadow.base[zt][zn];
        checkBounds(tmp, BZip2Constants.MAX_ALPHA_SIZE, "zvec");
        return dataShadow.perm[zt][tmp];
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.bin.getBytesRead();
    }

    private boolean init(boolean isFirstStream) throws IOException {
        if (this.bin == null) {
            throw new IOException("No InputStream");
        }
        if (!isFirstStream) {
            this.bin.clearBitCache();
        }
        int magic0 = readNextByte(this.bin);
        if (magic0 == -1 && !isFirstStream) {
            return false;
        }
        int magic1 = readNextByte(this.bin);
        int magic2 = readNextByte(this.bin);
        if (magic0 != 66 || magic1 != 90 || magic2 != 104) {
            throw new IOException(isFirstStream ? "Stream is not in the BZip2 format" : "Unexpected data after a valid BZip2 stream");
        }
        int blockSize = readNextByte(this.bin);
        if (blockSize < 49 || blockSize > 57) {
            throw new IOException("BZip2 block size is invalid");
        }
        this.blockSize100k = blockSize - 48;
        this.computedCombinedCRC = 0;
        return true;
    }

    private void initBlock() throws IOException {
        BitInputStream bin = this.bin;
        do {
            char magic0 = bsGetUByte(bin);
            char magic1 = bsGetUByte(bin);
            char magic2 = bsGetUByte(bin);
            char magic3 = bsGetUByte(bin);
            char magic4 = bsGetUByte(bin);
            char magic5 = bsGetUByte(bin);
            if (magic0 != 23 || magic1 != 'r' || magic2 != 'E' || magic3 != '8' || magic4 != 'P' || magic5 != 144) {
                if (magic0 != '1' || magic1 != 'A' || magic2 != 'Y' || magic3 != '&' || magic4 != 'S' || magic5 != 'Y') {
                    this.currentState = 0;
                    throw new IOException("Bad block header");
                }
                this.storedBlockCRC = bsGetInt(bin);
                this.blockRandomised = bsR(bin, 1) == 1;
                if (this.data == null) {
                    this.data = new Data(this.blockSize100k);
                }
                getAndMoveToFrontDecode();
                this.crc.reset();
                this.currentState = 1;
                return;
            }
        } while (!complete());
    }

    private void makeMaps() {
        boolean[] inUse = this.data.inUse;
        byte[] seqToUnseq = this.data.seqToUnseq;
        int nInUseShadow = 0;
        for (int i = 0; i < 256; i++) {
            if (inUse[i]) {
                seqToUnseq[nInUseShadow] = (byte) i;
                nInUseShadow++;
            }
        }
        this.nInUse = nInUseShadow;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.bin != null) {
            int r = read0();
            count(r < 0 ? -1 : 1);
            return r;
        }
        throw new IOException("Stream closed");
    }

    @Override // java.io.InputStream
    public int read(byte[] dest, int offs, int len) throws IOException {
        if (offs < 0) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
        }
        if (len < 0) {
            throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
        }
        if (offs + len > dest.length) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > dest.length(" + dest.length + ").");
        }
        if (this.bin == null) {
            throw new IOException("Stream closed");
        }
        if (len == 0) {
            return 0;
        }
        int hi = offs + len;
        int destOffs = offs;
        while (destOffs < hi) {
            int b = read0();
            if (b < 0) {
                break;
            }
            dest[destOffs] = (byte) b;
            count(1);
            destOffs++;
        }
        if (destOffs == offs) {
            return -1;
        }
        return destOffs - offs;
    }

    private int read0() throws IOException {
        switch (this.currentState) {
            case 0:
                return -1;
            case 1:
                return setupBlock();
            case 2:
                throw new IllegalStateException();
            case 3:
                return setupRandPartB();
            case 4:
                return setupRandPartC();
            case 5:
                throw new IllegalStateException();
            case 6:
                return setupNoRandPartB();
            case 7:
                return setupNoRandPartC();
            default:
                throw new IllegalStateException();
        }
    }

    private int readNextByte(BitInputStream in) throws IOException {
        long b = in.readBits(8);
        return (int) b;
    }

    private void recvDecodingTables() throws IOException {
        BitInputStream bin = this.bin;
        Data dataShadow = this.data;
        boolean[] inUse = dataShadow.inUse;
        byte[] pos = dataShadow.recvDecodingTables_pos;
        byte[] selector = dataShadow.selector;
        byte[] selectorMtf = dataShadow.selectorMtf;
        int inUse16 = 0;
        for (int i = 0; i < 16; i++) {
            if (bsGetBit(bin)) {
                inUse16 |= 1 << i;
            }
        }
        int i2 = 0;
        Arrays.fill(inUse, false);
        for (int i3 = 0; i3 < 16; i3++) {
            if (((1 << i3) & inUse16) != 0) {
                int i16 = i3 << 4;
                for (int j = 0; j < 16; j++) {
                    if (bsGetBit(bin)) {
                        inUse[i16 + j] = true;
                    }
                }
            }
        }
        makeMaps();
        int alphaSize = this.nInUse + 2;
        int nGroups = bsR(bin, 3);
        int selectors = bsR(bin, 15);
        if (selectors < 0) {
            throw new IOException("Corrupted input, nSelectors value negative");
        }
        checkBounds(alphaSize, 259, "alphaSize");
        checkBounds(nGroups, 7, "nGroups");
        for (int i4 = 0; i4 < selectors; i4++) {
            int j2 = 0;
            while (bsGetBit(bin)) {
                j2++;
            }
            if (i4 < 18002) {
                selectorMtf[i4] = (byte) j2;
            }
        }
        int nSelectors = Math.min(selectors, BZip2Constants.MAX_SELECTORS);
        int v = nGroups;
        while (true) {
            v--;
            if (v < 0) {
                break;
            } else {
                pos[v] = (byte) v;
            }
        }
        int i5 = 0;
        while (i5 < nSelectors) {
            int i6 = i2;
            int v2 = selectorMtf[i5] & UByte.MAX_VALUE;
            checkBounds(v2, 6, "selectorMtf");
            byte tmp = pos[v2];
            while (v2 > 0) {
                pos[v2] = pos[v2 - 1];
                v2--;
            }
            pos[i6] = tmp;
            selector[i5] = tmp;
            i5++;
            i2 = i6;
        }
        char[][] len = dataShadow.temp_charArray2d;
        int t = 0;
        while (t < nGroups) {
            int curr = bsR(bin, 5);
            char[] len_t = len[t];
            BitInputStream bin2 = bin;
            int i7 = 0;
            while (i7 < alphaSize) {
                while (bsGetBit(bin2)) {
                    curr += bsGetBit(bin2) ? -1 : 1;
                }
                int i8 = i7;
                len_t[i8] = (char) curr;
                i7 = i8 + 1;
            }
            t++;
            bin = bin2;
        }
        createHuffmanDecodingTables(alphaSize, nGroups);
    }

    private int setupBlock() throws IOException {
        if (this.currentState == 0 || this.data == null) {
            return -1;
        }
        int[] cftab = this.data.cftab;
        int ttLen = this.last + 1;
        int[] tt = this.data.initTT(ttLen);
        byte[] ll8 = this.data.ll8;
        cftab[0] = 0;
        System.arraycopy(this.data.unzftab, 0, cftab, 1, 256);
        int c = cftab[0];
        for (int i = 1; i <= 256; i++) {
            c += cftab[i];
            cftab[i] = c;
        }
        int lastShadow = this.last;
        for (int i2 = 0; i2 <= lastShadow; i2++) {
            int i3 = ll8[i2] & UByte.MAX_VALUE;
            int tmp = cftab[i3];
            cftab[i3] = tmp + 1;
            checkBounds(tmp, ttLen, "tt index");
            tt[tmp] = i2;
        }
        int i4 = this.origPtr;
        if (i4 < 0 || this.origPtr >= tt.length) {
            throw new IOException("Stream corrupted");
        }
        this.su_tPos = tt[this.origPtr];
        this.su_count = 0;
        this.su_i2 = 0;
        this.su_ch2 = 256;
        if (this.blockRandomised) {
            this.su_rNToGo = 0;
            this.su_rTPos = 0;
            return setupRandPartA();
        }
        return setupNoRandPartA();
    }

    private int setupNoRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            int su_ch2Shadow = this.data.ll8[this.su_tPos] & UByte.MAX_VALUE;
            this.su_ch2 = su_ch2Shadow;
            checkBounds(this.su_tPos, this.data.tt.length, "su_tPos");
            this.su_tPos = this.data.tt[this.su_tPos];
            this.su_i2++;
            this.currentState = 6;
            this.crc.update(su_ch2Shadow);
            return su_ch2Shadow;
        }
        this.currentState = 5;
        endBlock();
        initBlock();
        return setupBlock();
    }

    private int setupNoRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.su_count = 1;
            return setupNoRandPartA();
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i >= 4) {
            checkBounds(this.su_tPos, this.data.ll8.length, "su_tPos");
            this.su_z = (char) (this.data.ll8[this.su_tPos] & UByte.MAX_VALUE);
            this.su_tPos = this.data.tt[this.su_tPos];
            this.su_j2 = 0;
            return setupNoRandPartC();
        }
        return setupNoRandPartA();
    }

    private int setupNoRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            int su_ch2Shadow = this.su_ch2;
            this.crc.update(su_ch2Shadow);
            this.su_j2++;
            this.currentState = 7;
            return su_ch2Shadow;
        }
        this.su_i2++;
        this.su_count = 0;
        return setupNoRandPartA();
    }

    private int setupRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            int su_ch2Shadow = this.data.ll8[this.su_tPos] & UByte.MAX_VALUE;
            checkBounds(this.su_tPos, this.data.tt.length, "su_tPos");
            this.su_tPos = this.data.tt[this.su_tPos];
            if (this.su_rNToGo == 0) {
                this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
                int i = this.su_rTPos + 1;
                this.su_rTPos = i;
                if (i == 512) {
                    this.su_rTPos = 0;
                }
            } else {
                this.su_rNToGo--;
            }
            int su_ch2Shadow2 = su_ch2Shadow ^ (this.su_rNToGo == 1 ? 1 : 0);
            this.su_ch2 = su_ch2Shadow2;
            this.su_i2++;
            this.currentState = 3;
            this.crc.update(su_ch2Shadow2);
            return su_ch2Shadow2;
        }
        endBlock();
        initBlock();
        return setupBlock();
    }

    private int setupRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.currentState = 2;
            this.su_count = 1;
            return setupRandPartA();
        }
        int i = this.su_count + 1;
        this.su_count = i;
        if (i < 4) {
            this.currentState = 2;
            return setupRandPartA();
        }
        this.su_z = (char) (this.data.ll8[this.su_tPos] & UByte.MAX_VALUE);
        checkBounds(this.su_tPos, this.data.tt.length, "su_tPos");
        this.su_tPos = this.data.tt[this.su_tPos];
        if (this.su_rNToGo == 0) {
            this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
            int i2 = this.su_rTPos + 1;
            this.su_rTPos = i2;
            if (i2 == 512) {
                this.su_rTPos = 0;
            }
        } else {
            this.su_rNToGo--;
        }
        this.su_j2 = 0;
        this.currentState = 4;
        if (this.su_rNToGo == 1) {
            this.su_z = (char) (this.su_z ^ 1);
        }
        return setupRandPartC();
    }

    private int setupRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            this.crc.update(this.su_ch2);
            this.su_j2++;
            return this.su_ch2;
        }
        this.currentState = 2;
        this.su_i2++;
        this.su_count = 0;
        return setupRandPartA();
    }
}
