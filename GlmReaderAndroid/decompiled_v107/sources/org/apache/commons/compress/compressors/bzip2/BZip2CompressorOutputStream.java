package org.apache.commons.compress.compressors.bzip2;

import androidx.core.view.InputDeviceCompat;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import kotlin.UByte;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;

/* loaded from: classes9.dex */
public class BZip2CompressorOutputStream extends CompressorOutputStream<OutputStream> implements BZip2Constants {
    private static final int GREATER_ICOST = 15;
    private static final int LESSER_ICOST = 0;
    public static final int MAX_BLOCKSIZE = 9;
    public static final int MIN_BLOCKSIZE = 1;
    private final int allowableBlockSize;
    private final int blockSize100k;
    private BlockSort blockSorter;
    private int bsBuff;
    private int bsLive;
    private int combinedCRC;
    private final CRC crc;
    private int currentChar;
    private Data data;
    private int last;
    private int nInUse;
    private int nMTF;
    private int runLength;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class Data {
        final byte[] block;
        final int[] fmap;
        int origPtr;
        final char[] sfmap;
        final boolean[] inUse = new boolean[256];
        final byte[] unseqToSeq = new byte[256];
        final int[] mtfFreq = new int[BZip2Constants.MAX_ALPHA_SIZE];
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] generateMTFValues_yy = new byte[256];
        final byte[][] sendMTFValues_len = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final int[][] sendMTFValues_rfreq = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final int[] sendMTFValues_fave = new int[6];
        final short[] sendMTFValues_cost = new short[6];
        final int[][] sendMTFValues_code = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, BZip2Constants.MAX_ALPHA_SIZE);
        final byte[] sendMTFValues2_pos = new byte[6];
        final boolean[] sentMTFValues4_inUse16 = new boolean[16];
        final int[] heap = new int[260];
        final int[] weight = new int[516];
        final int[] parent = new int[516];

        Data(int blockSize100k) {
            int n = BZip2Constants.BASEBLOCKSIZE * blockSize100k;
            this.block = new byte[n + 1 + 20];
            this.fmap = new int[n];
            this.sfmap = new char[n * 2];
        }
    }

    public static int chooseBlockSize(long inputLength) {
        if (inputLength > 0) {
            return (int) Math.min((inputLength / 132000) + 1, 9L);
        }
        return 9;
    }

    private static void hbAssignCodes(int[] code, byte[] length, int minLen, int maxLen, int alphaSize) {
        int vec = 0;
        for (int n = minLen; n <= maxLen; n++) {
            for (int i = 0; i < alphaSize; i++) {
                if ((length[i] & 255) == n) {
                    code[i] = vec;
                    vec++;
                }
            }
            vec <<= 1;
        }
    }

    private static void hbMakeCodeLengths(byte[] len, int[] freq, Data dat, int alphaSize, int maxLen) {
        int i;
        int i2;
        int[] heap = dat.heap;
        int[] weight = dat.weight;
        int[] parent = dat.parent;
        int i3 = alphaSize;
        while (true) {
            i = -1;
            i3--;
            i2 = 1;
            if (i3 < 0) {
                break;
            }
            int i4 = i3 + 1;
            if (freq[i3] != 0) {
                i2 = freq[i3];
            }
            weight[i4] = i2 << 8;
        }
        int i5 = 1;
        while (i5 != 0) {
            i5 = 0;
            int nNodes = alphaSize;
            int nHeap = 0;
            heap[0] = 0;
            weight[0] = 0;
            parent[0] = -2;
            for (int i6 = 1; i6 <= alphaSize; i6++) {
                parent[i6] = i;
                nHeap++;
                heap[nHeap] = i6;
                int zz = nHeap;
                int tmp = heap[zz];
                while (weight[tmp] < weight[heap[zz >> 1]]) {
                    heap[zz] = heap[zz >> 1];
                    zz >>= 1;
                }
                heap[zz] = tmp;
            }
            while (nHeap > i2) {
                int n1 = heap[i2];
                heap[i2] = heap[nHeap];
                int nHeap2 = nHeap - 1;
                int zz2 = 1;
                int tmp2 = heap[i2];
                while (true) {
                    int yy = zz2 << 1;
                    if (yy > nHeap2) {
                        break;
                    }
                    if (yy < nHeap2 && weight[heap[yy + 1]] < weight[heap[yy]]) {
                        yy++;
                    }
                    if (weight[tmp2] < weight[heap[yy]]) {
                        break;
                    }
                    heap[zz2] = heap[yy];
                    zz2 = yy;
                }
                heap[zz2] = tmp2;
                int n2 = heap[i2];
                heap[i2] = heap[nHeap2];
                int nHeap3 = nHeap2 - 1;
                int zz3 = 1;
                int tmp3 = heap[i2];
                while (true) {
                    int yy2 = zz3 << 1;
                    if (yy2 > nHeap3) {
                        break;
                    }
                    if (yy2 < nHeap3 && weight[heap[yy2 + 1]] < weight[heap[yy2]]) {
                        yy2++;
                    }
                    if (weight[tmp3] < weight[heap[yy2]]) {
                        break;
                    }
                    heap[zz3] = heap[yy2];
                    zz3 = yy2;
                }
                heap[zz3] = tmp3;
                nNodes++;
                parent[n2] = nNodes;
                parent[n1] = nNodes;
                int weight_n1 = weight[n1];
                int weight_n2 = weight[n2];
                int i7 = i;
                int i8 = i2;
                weight[nNodes] = (Math.max(weight_n1 & 255, weight_n2 & 255) + 1) | ((weight_n1 & InputDeviceCompat.SOURCE_ANY) + (weight_n2 & InputDeviceCompat.SOURCE_ANY));
                parent[nNodes] = i7;
                int nHeap4 = nHeap3 + 1;
                heap[nHeap4] = nNodes;
                int zz4 = nHeap4;
                int tmp4 = heap[zz4];
                int weight_tmp = weight[tmp4];
                while (weight_tmp < weight[heap[zz4 >> 1]]) {
                    heap[zz4] = heap[zz4 >> 1];
                    zz4 >>= 1;
                }
                heap[zz4] = tmp4;
                nHeap = nHeap4;
                i = i7;
                i2 = i8;
            }
            int i9 = i;
            int i10 = i2;
            for (int i11 = 1; i11 <= alphaSize; i11++) {
                int j = 0;
                int k = i11;
                while (true) {
                    int parent_k = parent[k];
                    if (parent_k < 0) {
                        break;
                    }
                    k = parent_k;
                    j++;
                }
                len[i11 - 1] = (byte) j;
                if (j > maxLen) {
                    i5 = 1;
                }
            }
            if (i5 != 0) {
                for (int i12 = 1; i12 < alphaSize; i12++) {
                    int j2 = weight[i12] >> 8;
                    weight[i12] = ((j2 >> 1) + 1) << 8;
                }
            }
            i = i9;
            i2 = i10;
        }
    }

    public BZip2CompressorOutputStream(OutputStream out) throws IOException {
        this(out, 9);
    }

    public BZip2CompressorOutputStream(OutputStream out, int blockSize) throws IOException {
        super(out);
        this.crc = new CRC();
        this.currentChar = -1;
        if (blockSize < 1) {
            throw new IllegalArgumentException("blockSize(" + blockSize + ") < 1");
        }
        if (blockSize > 9) {
            throw new IllegalArgumentException("blockSize(" + blockSize + ") > 9");
        }
        this.blockSize100k = blockSize;
        this.allowableBlockSize = (this.blockSize100k * BZip2Constants.BASEBLOCKSIZE) - 20;
        init();
    }

    private void blockSort() {
        this.blockSorter.blockSort(this.data, this.last);
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            int ch = this.bsBuff >> 24;
            this.out.write(ch);
            this.bsBuff <<= 8;
            this.bsLive -= 8;
        }
    }

    private void bsPutInt(int u) throws IOException {
        bsW(8, (u >> 24) & 255);
        bsW(8, (u >> 16) & 255);
        bsW(8, (u >> 8) & 255);
        bsW(8, u & 255);
    }

    private void bsPutUByte(int c) throws IOException {
        bsW(8, c);
    }

    private void bsW(int n, int v) throws IOException {
        OutputStream outShadow = this.out;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        while (bsLiveShadow >= 8) {
            outShadow.write(bsBuffShadow >> 24);
            bsBuffShadow <<= 8;
            bsLiveShadow -= 8;
        }
        this.bsBuff = (v << ((32 - bsLiveShadow) - n)) | bsBuffShadow;
        this.bsLive = bsLiveShadow + n;
    }

    @Override // org.apache.commons.compress.CompressFilterOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!isClosed()) {
            try {
                finish();
            } finally {
                super.close();
            }
        }
    }

    private void endBlock() throws IOException {
        int blockCRC = this.crc.getValue();
        this.combinedCRC = (this.combinedCRC << 1) | (this.combinedCRC >>> 31);
        this.combinedCRC ^= blockCRC;
        if (this.last == -1) {
            return;
        }
        blockSort();
        bsPutUByte(49);
        bsPutUByte(65);
        bsPutUByte(89);
        bsPutUByte(38);
        bsPutUByte(83);
        bsPutUByte(89);
        bsPutInt(blockCRC);
        bsW(1, 0);
        moveToFrontCodeAndSend();
    }

    private void endCompression() throws IOException {
        bsPutUByte(23);
        bsPutUByte(114);
        bsPutUByte(69);
        bsPutUByte(56);
        bsPutUByte(80);
        bsPutUByte(144);
        bsPutInt(this.combinedCRC);
        bsFinishedWithStream();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.compress.CompressFilterOutputStream
    public void finish() throws IOException {
        if (!isClosed()) {
            try {
                if (this.runLength > 0) {
                    writeRun();
                }
                this.currentChar = -1;
                endBlock();
                endCompression();
            } finally {
                this.blockSorter = null;
                this.data = null;
            }
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        if (this.out != null) {
            super.flush();
        }
    }

    private void generateMTFValues() {
        char c;
        int lastShadow = this.last;
        Data dataShadow = this.data;
        boolean[] inUse = dataShadow.inUse;
        byte[] block = dataShadow.block;
        int[] fmap = dataShadow.fmap;
        char[] sfmap = dataShadow.sfmap;
        int[] mtfFreq = dataShadow.mtfFreq;
        byte[] unseqToSeq = dataShadow.unseqToSeq;
        byte[] yy = dataShadow.generateMTFValues_yy;
        int nInUseShadow = 0;
        for (int i = 0; i < 256; i++) {
            if (inUse[i]) {
                unseqToSeq[i] = (byte) nInUseShadow;
                nInUseShadow++;
            }
        }
        this.nInUse = nInUseShadow;
        int eob = nInUseShadow + 1;
        char c2 = 0;
        Arrays.fill(mtfFreq, 0, eob + 1, 0);
        int i2 = nInUseShadow;
        while (true) {
            i2--;
            if (i2 < 0) {
                break;
            } else {
                yy[i2] = (byte) i2;
            }
        }
        int wr = 0;
        int zPend = 0;
        int i3 = 0;
        while (true) {
            c = c2;
            if (i3 > lastShadow) {
                break;
            }
            byte ll_i = unseqToSeq[block[fmap[i3]] & UByte.MAX_VALUE];
            byte tmp = yy[c];
            int j = 0;
            int lastShadow2 = lastShadow;
            byte tmp2 = tmp;
            while (ll_i != tmp2) {
                j++;
                byte tmp22 = tmp2;
                tmp2 = yy[j];
                yy[j] = tmp22;
            }
            yy[c] = tmp2;
            if (j == 0) {
                zPend++;
            } else {
                if (zPend > 0) {
                    int zPend2 = zPend - 1;
                    while (true) {
                        if ((zPend2 & 1) == 0) {
                            sfmap[wr] = c;
                            wr++;
                            mtfFreq[c] = mtfFreq[c] + 1;
                        } else {
                            sfmap[wr] = 1;
                            wr++;
                            mtfFreq[1] = mtfFreq[1] + 1;
                        }
                        byte tmp3 = tmp2;
                        if (zPend2 < 2) {
                            break;
                        }
                        zPend2 = (zPend2 - 2) >> 1;
                        tmp2 = tmp3;
                    }
                    zPend = 0;
                }
                sfmap[wr] = (char) (j + 1);
                wr++;
                int i4 = j + 1;
                mtfFreq[i4] = mtfFreq[i4] + 1;
            }
            i3++;
            c2 = c;
            lastShadow = lastShadow2;
        }
        if (zPend > 0) {
            int zPend3 = zPend - 1;
            while (true) {
                if ((zPend3 & 1) == 0) {
                    sfmap[wr] = c;
                    wr++;
                    mtfFreq[c] = mtfFreq[c] + 1;
                } else {
                    sfmap[wr] = 1;
                    wr++;
                    mtfFreq[1] = mtfFreq[1] + 1;
                }
                if (zPend3 < 2) {
                    break;
                } else {
                    zPend3 = (zPend3 - 2) >> 1;
                }
            }
        }
        sfmap[wr] = (char) eob;
        mtfFreq[eob] = mtfFreq[eob] + 1;
        this.nMTF = wr + 1;
    }

    public final int getBlockSize() {
        return this.blockSize100k;
    }

    private void init() throws IOException {
        bsPutUByte(66);
        bsPutUByte(90);
        this.data = new Data(this.blockSize100k);
        this.blockSorter = new BlockSort(this.data);
        bsPutUByte(104);
        bsPutUByte(this.blockSize100k + 48);
        this.combinedCRC = 0;
        initBlock();
    }

    private void initBlock() {
        this.crc.reset();
        this.last = -1;
        boolean[] inUse = this.data.inUse;
        int i = 256;
        while (true) {
            i--;
            if (i >= 0) {
                inUse[i] = false;
            } else {
                return;
            }
        }
    }

    private void moveToFrontCodeAndSend() throws IOException {
        bsW(24, this.data.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    private void sendMTFValues() throws IOException {
        byte[][] len = this.data.sendMTFValues_len;
        int alphaSize = this.nInUse + 2;
        int t = 6;
        while (true) {
            t--;
            if (t < 0) {
                break;
            }
            byte[] len_t = len[t];
            int v = alphaSize;
            while (true) {
                v--;
                if (v >= 0) {
                    len_t[v] = IntersectionPtg.sid;
                }
            }
        }
        int t2 = this.nMTF;
        int nGroups = t2 >= 200 ? this.nMTF < 600 ? 3 : this.nMTF < 1200 ? 4 : this.nMTF < 2400 ? 5 : 6 : 2;
        sendMTFValues0(nGroups, alphaSize);
        int nSelectors = sendMTFValues1(nGroups, alphaSize);
        sendMTFValues2(nGroups, nSelectors);
        sendMTFValues3(nGroups, alphaSize);
        sendMTFValues4();
        sendMTFValues5(nGroups, nSelectors);
        sendMTFValues6(nGroups, alphaSize);
        sendMTFValues7();
    }

    private void sendMTFValues0(int nGroups, int alphaSize) {
        byte[][] len = this.data.sendMTFValues_len;
        int[] mtfFreq = this.data.mtfFreq;
        int remF = this.nMTF;
        int gs = 0;
        for (int nPart = nGroups; nPart > 0; nPart--) {
            int tFreq = remF / nPart;
            int ge = gs - 1;
            int aFreq = 0;
            int a = alphaSize - 1;
            while (aFreq < tFreq && ge < a) {
                ge++;
                aFreq += mtfFreq[ge];
            }
            if (ge > gs && nPart != nGroups && nPart != 1 && (1 & (nGroups - nPart)) != 0) {
                aFreq -= mtfFreq[ge];
                ge--;
            }
            int ge2 = nPart - 1;
            byte[] len_np = len[ge2];
            int v = alphaSize;
            while (true) {
                v--;
                if (v >= 0) {
                    if (v >= gs && v <= ge) {
                        len_np[v] = 0;
                    } else {
                        len_np[v] = IntersectionPtg.sid;
                    }
                }
            }
            gs = ge + 1;
            remF -= aFreq;
        }
    }

    private int sendMTFValues1(int nGroups, int alphaSize) {
        byte b;
        Data dataShadow = this.data;
        int[][] rfreq = dataShadow.sendMTFValues_rfreq;
        int[] fave = dataShadow.sendMTFValues_fave;
        short[] cost = dataShadow.sendMTFValues_cost;
        char[] sfmap = dataShadow.sfmap;
        byte[] selector = dataShadow.selector;
        byte[][] len = dataShadow.sendMTFValues_len;
        byte[] len_0 = len[0];
        byte[] len_1 = len[1];
        byte[] len_2 = len[2];
        byte mask = 3;
        byte[] len_3 = len[3];
        int i = 4;
        byte[] len_4 = len[4];
        byte[] len_5 = len[5];
        int nMTFShadow = this.nMTF;
        int nSelectors = 0;
        int iter = 0;
        while (iter < i) {
            int t = nGroups;
            while (true) {
                t--;
                if (t < 0) {
                    break;
                }
                fave[t] = 0;
                int[] rfreqt = rfreq[t];
                int i2 = alphaSize;
                while (true) {
                    i2--;
                    if (i2 >= 0) {
                        rfreqt[i2] = 0;
                    }
                }
            }
            nSelectors = 0;
            int i3 = i;
            int gs = 0;
            while (true) {
                b = mask;
                if (gs >= this.nMTF) {
                    break;
                }
                Data dataShadow2 = dataShadow;
                int ge = Math.min((gs + 50) - 1, nMTFShadow - 1);
                int[][] rfreq2 = rfreq;
                if (nGroups == 6) {
                    short cost1 = 0;
                    short cost2 = 0;
                    short cost3 = 0;
                    short cost4 = 0;
                    short cost5 = 0;
                    short cost12 = 0;
                    int i4 = gs;
                    while (i4 <= ge) {
                        char c = sfmap[i4];
                        int i5 = i4;
                        int i6 = len_0[c] & (-1);
                        short cost0 = (short) (cost12 + ((short) i6));
                        short cost02 = len_1[c];
                        short cost13 = (short) (cost1 + ((short) (cost02 & (-1))));
                        short cost14 = len_2[c];
                        cost2 = (short) (cost2 + ((short) (cost14 & (-1))));
                        cost3 = (short) (cost3 + ((short) (len_3[c] & (-1))));
                        cost4 = (short) (cost4 + ((short) (len_4[c] & (-1))));
                        cost5 = (short) (cost5 + ((short) (len_5[c] & (-1))));
                        i4 = i5 + 1;
                        cost1 = cost13;
                        cost12 = cost0;
                    }
                    cost[0] = cost12;
                    cost[1] = cost1;
                    cost[2] = cost2;
                    cost[b] = cost3;
                    cost[i3] = cost4;
                    cost[5] = cost5;
                } else {
                    int t2 = nGroups;
                    while (true) {
                        t2--;
                        if (t2 < 0) {
                            break;
                        }
                        cost[t2] = 0;
                    }
                    int i7 = gs;
                    while (i7 <= ge) {
                        char c2 = sfmap[i7];
                        int t3 = nGroups;
                        while (true) {
                            t3--;
                            if (t3 >= 0) {
                                short s = cost[t3];
                                int i8 = i7;
                                int i9 = len[t3][c2] & (-1);
                                cost[t3] = (short) (s + ((short) i9));
                                i7 = i8;
                            }
                        }
                        i7++;
                    }
                }
                int bt = -1;
                int t4 = nGroups;
                int[] fave2 = fave;
                int bc = 999999999;
                while (true) {
                    t4--;
                    if (t4 < 0) {
                        break;
                    }
                    short[] cost6 = cost;
                    short s2 = cost6[t4];
                    if (s2 < bc) {
                        bc = s2;
                        bt = t4;
                    }
                    cost = cost6;
                }
                short[] cost7 = cost;
                int bc2 = fave2[bt];
                fave2[bt] = bc2 + 1;
                selector[nSelectors] = (byte) bt;
                nSelectors++;
                int[] rfreq_bt = rfreq2[bt];
                for (int i10 = gs; i10 <= ge; i10++) {
                    char c3 = sfmap[i10];
                    rfreq_bt[c3] = rfreq_bt[c3] + 1;
                }
                gs = ge + 1;
                mask = b;
                dataShadow = dataShadow2;
                rfreq = rfreq2;
                fave = fave2;
                cost = cost7;
            }
            Data dataShadow3 = dataShadow;
            int[][] rfreq3 = rfreq;
            int[] fave3 = fave;
            short[] cost8 = cost;
            for (int t5 = 0; t5 < nGroups; t5++) {
                hbMakeCodeLengths(len[t5], rfreq3[t5], this.data, alphaSize, 20);
            }
            iter++;
            i = i3;
            mask = b;
            dataShadow = dataShadow3;
            rfreq = rfreq3;
            fave = fave3;
            cost = cost8;
        }
        return nSelectors;
    }

    private void sendMTFValues2(int nGroups, int nSelectors) {
        Data dataShadow = this.data;
        byte[] pos = dataShadow.sendMTFValues2_pos;
        int i = nGroups;
        while (true) {
            i--;
            if (i < 0) {
                break;
            } else {
                pos[i] = (byte) i;
            }
        }
        for (int i2 = 0; i2 < nSelectors; i2++) {
            byte ll_i = dataShadow.selector[i2];
            byte tmp = pos[0];
            int j = 0;
            while (ll_i != tmp) {
                j++;
                byte tmp2 = tmp;
                tmp = pos[j];
                pos[j] = tmp2;
            }
            pos[0] = tmp;
            dataShadow.selectorMtf[i2] = (byte) j;
        }
    }

    private void sendMTFValues3(int nGroups, int alphaSize) {
        int[][] code = this.data.sendMTFValues_code;
        byte[][] len = this.data.sendMTFValues_len;
        for (int t = 0; t < nGroups; t++) {
            int minLen = 32;
            int maxLen = 0;
            byte[] len_t = len[t];
            int i = alphaSize;
            while (true) {
                i--;
                if (i >= 0) {
                    int l = len_t[i] & UByte.MAX_VALUE;
                    if (l > maxLen) {
                        maxLen = l;
                    }
                    if (l < minLen) {
                        minLen = l;
                    }
                }
            }
            hbAssignCodes(code[t], len[t], minLen, maxLen, alphaSize);
        }
    }

    private void sendMTFValues4() throws IOException {
        boolean[] zArr = this.data.inUse;
        boolean[] zArr2 = this.data.sentMTFValues4_inUse16;
        int i = 16;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            zArr2[i] = false;
            int i2 = i * 16;
            int i3 = 16;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (zArr[i2 + i3]) {
                        zArr2[i] = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            bsW(1, zArr2[i4] ? 1 : 0);
        }
        OutputStream outputStream = this.out;
        int i5 = this.bsLive;
        int i6 = this.bsBuff;
        for (int i7 = 0; i7 < 16; i7++) {
            if (zArr2[i7]) {
                int i8 = i7 * 16;
                for (int i9 = 0; i9 < 16; i9++) {
                    while (i5 >= 8) {
                        outputStream.write(i6 >> 24);
                        i6 <<= 8;
                        i5 -= 8;
                    }
                    if (zArr[i8 + i9]) {
                        i6 |= 1 << ((32 - i5) - 1);
                    }
                    i5++;
                }
            }
        }
        this.bsBuff = i6;
        this.bsLive = i5;
    }

    private void sendMTFValues5(int nGroups, int nSelectors) throws IOException {
        bsW(3, nGroups);
        bsW(15, nSelectors);
        OutputStream outShadow = this.out;
        byte[] selectorMtf = this.data.selectorMtf;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        for (int i = 0; i < nSelectors; i++) {
            int hj = selectorMtf[i] & UByte.MAX_VALUE;
            for (int j = 0; j < hj; j++) {
                while (bsLiveShadow >= 8) {
                    outShadow.write(bsBuffShadow >> 24);
                    bsBuffShadow <<= 8;
                    bsLiveShadow -= 8;
                }
                bsBuffShadow |= 1 << ((32 - bsLiveShadow) - 1);
                bsLiveShadow++;
            }
            while (bsLiveShadow >= 8) {
                outShadow.write(bsBuffShadow >> 24);
                bsBuffShadow <<= 8;
                bsLiveShadow -= 8;
            }
            bsLiveShadow++;
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    private void sendMTFValues6(int nGroups, int alphaSize) throws IOException {
        byte[][] len = this.data.sendMTFValues_len;
        OutputStream outShadow = this.out;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        for (int t = 0; t < nGroups; t++) {
            byte[] len_t = len[t];
            int curr = len_t[0] & UByte.MAX_VALUE;
            while (bsLiveShadow >= 8) {
                outShadow.write(bsBuffShadow >> 24);
                bsBuffShadow <<= 8;
                bsLiveShadow -= 8;
            }
            bsBuffShadow |= curr << ((32 - bsLiveShadow) - 5);
            bsLiveShadow += 5;
            for (int i = 0; i < alphaSize; i++) {
                int lti = len_t[i] & UByte.MAX_VALUE;
                while (curr < lti) {
                    while (bsLiveShadow >= 8) {
                        outShadow.write(bsBuffShadow >> 24);
                        bsBuffShadow <<= 8;
                        bsLiveShadow -= 8;
                    }
                    bsBuffShadow |= 2 << ((32 - bsLiveShadow) - 2);
                    bsLiveShadow += 2;
                    curr++;
                }
                while (curr > lti) {
                    while (bsLiveShadow >= 8) {
                        outShadow.write(bsBuffShadow >> 24);
                        bsBuffShadow <<= 8;
                        bsLiveShadow -= 8;
                    }
                    bsBuffShadow |= 3 << ((32 - bsLiveShadow) - 2);
                    bsLiveShadow += 2;
                    curr--;
                }
                while (bsLiveShadow >= 8) {
                    outShadow.write(bsBuffShadow >> 24);
                    bsBuffShadow <<= 8;
                    bsLiveShadow -= 8;
                }
                bsLiveShadow++;
            }
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    private void sendMTFValues7() throws IOException {
        Data dataShadow;
        Data dataShadow2 = this.data;
        byte[][] len = dataShadow2.sendMTFValues_len;
        int[][] code = dataShadow2.sendMTFValues_code;
        OutputStream outShadow = this.out;
        byte[] selector = dataShadow2.selector;
        char[] sfmap = dataShadow2.sfmap;
        int nMTFShadow = this.nMTF;
        int selCtr = 0;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        int gs = 0;
        while (gs < nMTFShadow) {
            int ge = Math.min((gs + 50) - 1, nMTFShadow - 1);
            int selector_selCtr = selector[selCtr] & 255;
            int[] code_selCtr = code[selector_selCtr];
            byte[] len_selCtr = len[selector_selCtr];
            while (gs <= ge) {
                char c = sfmap[gs];
                while (true) {
                    dataShadow = dataShadow2;
                    if (bsLiveShadow >= 8) {
                        outShadow.write(bsBuffShadow >> 24);
                        bsBuffShadow <<= 8;
                        bsLiveShadow -= 8;
                        dataShadow2 = dataShadow;
                    }
                }
                int n = len_selCtr[c] & UByte.MAX_VALUE;
                bsBuffShadow |= code_selCtr[c] << ((32 - bsLiveShadow) - n);
                bsLiveShadow += n;
                gs++;
                dataShadow2 = dataShadow;
            }
            gs = ge + 1;
            selCtr++;
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] buf, int offs, int len) throws IOException {
        if (offs < 0) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
        }
        if (len < 0) {
            throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
        }
        if (offs + len > buf.length) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > buf.length(" + buf.length + ").");
        }
        checkOpen();
        int hi = offs + len;
        while (offs < hi) {
            write0(buf[offs]);
            offs++;
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int b) throws IOException {
        checkOpen();
        write0(b);
    }

    private void write0(int b) throws IOException {
        if (this.currentChar != -1) {
            int b2 = b & 255;
            if (this.currentChar == b2) {
                int i = this.runLength + 1;
                this.runLength = i;
                if (i > 254) {
                    writeRun();
                    this.currentChar = -1;
                    this.runLength = 0;
                    return;
                }
                return;
            }
            writeRun();
            this.runLength = 1;
            this.currentChar = b2;
            return;
        }
        this.currentChar = b & 255;
        this.runLength++;
    }

    private void writeRun() throws IOException {
        int lastShadow = this.last;
        if (lastShadow < this.allowableBlockSize) {
            int currentCharShadow = this.currentChar;
            Data dataShadow = this.data;
            dataShadow.inUse[currentCharShadow] = true;
            byte ch = (byte) currentCharShadow;
            int runLengthShadow = this.runLength;
            this.crc.update(currentCharShadow, runLengthShadow);
            switch (runLengthShadow) {
                case 1:
                    dataShadow.block[lastShadow + 2] = ch;
                    this.last = lastShadow + 1;
                    return;
                case 2:
                    dataShadow.block[lastShadow + 2] = ch;
                    dataShadow.block[lastShadow + 3] = ch;
                    this.last = lastShadow + 2;
                    return;
                case 3:
                    byte[] block = dataShadow.block;
                    block[lastShadow + 2] = ch;
                    block[lastShadow + 3] = ch;
                    block[lastShadow + 4] = ch;
                    this.last = lastShadow + 3;
                    return;
                default:
                    int runLengthShadow2 = runLengthShadow - 4;
                    dataShadow.inUse[runLengthShadow2] = true;
                    byte[] block2 = dataShadow.block;
                    block2[lastShadow + 2] = ch;
                    block2[lastShadow + 3] = ch;
                    block2[lastShadow + 4] = ch;
                    block2[lastShadow + 5] = ch;
                    block2[lastShadow + 6] = (byte) runLengthShadow2;
                    this.last = lastShadow + 5;
                    return;
            }
        }
        endBlock();
        initBlock();
        writeRun();
    }
}
