package org.apache.commons.compress.compressors.bzip2;

import java.util.BitSet;
import kotlin.UByte;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

/* loaded from: classes9.dex */
final class BlockSort {
    private static final int CLEARMASK = -2097153;
    private static final int DEPTH_THRESH = 10;
    private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    private static final int FALLBACK_QSORT_STACK_SIZE = 100;
    private static final int FTAB_LENGTH = 65537;
    private static final int QSORT_STACK_SIZE = 1000;
    private static final int SETMASK = 2097152;
    private static final int SMALL_THRESH = 20;
    private static final int WORK_FACTOR = 30;
    private int[] eclass;
    private boolean firstAttempt;
    private final char[] quadrant;
    private int workDone;
    private int workLimit;
    private static final int STACK_SIZE = Math.max(1000, 100);
    private static final int[] INCS = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private final int[] stack_ll = new int[STACK_SIZE];
    private final int[] stack_hh = new int[STACK_SIZE];
    private final int[] stack_dd = new int[1000];
    private final int[] mainSort_runningOrder = new int[256];
    private final int[] mainSort_copy = new int[256];
    private final boolean[] mainSort_bigDone = new boolean[256];
    private final int[] ftab = new int[FTAB_LENGTH];

    /* JADX WARN: Code restructure failed: missing block: B:12:0x000c, code lost:
    
        if (r1 > r3) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0005, code lost:
    
        if (r1 < r3) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:?, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int med3(int r1, int r2, int r3) {
        /*
            if (r1 >= r2) goto L8
            if (r2 >= r3) goto L5
            goto La
        L5:
            if (r1 >= r3) goto L10
            goto Le
        L8:
            if (r2 <= r3) goto Lc
        La:
            r0 = r2
            goto L11
        Lc:
            if (r1 <= r3) goto L10
        Le:
            r0 = r3
            goto L11
        L10:
            r0 = r1
        L11:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.med3(int, int, int):int");
    }

    private static void vswap(int[] fmap, int p2, int p22, int n) {
        int n2 = n + p2;
        while (p2 < n2) {
            int t = fmap[p2];
            int p1 = p2 + 1;
            fmap[p2] = fmap[p22];
            fmap[p22] = t;
            p22++;
            p2 = p1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BlockSort(BZip2CompressorOutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void blockSort(BZip2CompressorOutputStream.Data data, int last) {
        this.workLimit = last * 30;
        this.workDone = 0;
        this.firstAttempt = true;
        if (last + 1 < 10000) {
            fallbackSort(data, last);
        } else {
            mainSort(data, last);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                fallbackSort(data, last);
            }
        }
        int[] fmap = data.fmap;
        data.origPtr = -1;
        for (int i = 0; i <= last; i++) {
            if (fmap[i] == 0) {
                data.origPtr = i;
                return;
            }
        }
    }

    private void fallbackQSort3(int[] fmap, int[] eclass, int loSt, int hiSt) {
        long med;
        int gtHi;
        int sp;
        int[] iArr = eclass;
        long r = 0;
        int ltLo = 0 + 1;
        fpush(0, loSt, hiSt);
        while (ltLo > 0) {
            int sp2 = ltLo - 1;
            int[] s = fpop(sp2);
            int lo = s[0];
            int hi = s[1];
            if (hi - lo < 10) {
                fallbackSimpleSort(fmap, iArr, lo, hi);
                ltLo = sp2;
            } else {
                long r2 = ((7621 * r) + 1) % 32768;
                long r3 = r2 % 3;
                if (r3 == 0) {
                    med = iArr[fmap[lo]];
                } else if (r3 == 1) {
                    med = iArr[fmap[(lo + hi) >>> 1]];
                } else {
                    med = iArr[fmap[hi]];
                }
                int ltLo2 = lo;
                int unLo = lo;
                int gtHi2 = hi;
                int unHi = hi;
                while (true) {
                    if (unLo <= unHi) {
                        int n = iArr[fmap[unLo]] - ((int) med);
                        if (n == 0) {
                            fswap(fmap, unLo, ltLo2);
                            ltLo2++;
                            unLo++;
                            iArr = eclass;
                        } else if (n <= 0) {
                            unLo++;
                            iArr = eclass;
                        }
                    }
                    gtHi = gtHi2;
                    while (unLo <= unHi) {
                        int n2 = eclass[fmap[unHi]] - ((int) med);
                        if (n2 == 0) {
                            fswap(fmap, unHi, gtHi);
                            gtHi--;
                            unHi--;
                        } else if (n2 < 0) {
                            break;
                        } else {
                            unHi--;
                        }
                    }
                    if (unLo > unHi) {
                        break;
                    }
                    int gtHi3 = gtHi;
                    fswap(fmap, unLo, unHi);
                    unLo++;
                    unHi--;
                    iArr = eclass;
                    gtHi2 = gtHi3;
                }
                if (gtHi < ltLo2) {
                    iArr = eclass;
                    ltLo = sp2;
                    r = r2;
                } else {
                    int gtHi4 = gtHi;
                    int gtHi5 = unLo - ltLo2;
                    int n3 = Math.min(ltLo2 - lo, gtHi5);
                    fvswap(fmap, lo, unLo - n3, n3);
                    int m = Math.min(hi - gtHi4, gtHi4 - unHi);
                    fvswap(fmap, unHi + 1, (hi - m) + 1, m);
                    int n4 = ((lo + unLo) - ltLo2) - 1;
                    int m2 = (hi - (gtHi4 - unHi)) + 1;
                    if (n4 - lo > hi - m2) {
                        int sp3 = sp2 + 1;
                        fpush(sp2, lo, n4);
                        sp = sp3 + 1;
                        fpush(sp3, m2, hi);
                    } else {
                        int sp4 = sp2 + 1;
                        fpush(sp2, m2, hi);
                        sp = sp4 + 1;
                        fpush(sp4, lo, n4);
                    }
                    iArr = eclass;
                    ltLo = sp;
                    r = r2;
                }
            }
        }
    }

    private void fallbackSimpleSort(int[] fmap, int[] eclass, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        if (hi - lo > 3) {
            for (int i = hi - 4; i >= lo; i--) {
                int tmp = fmap[i];
                int ec_tmp = eclass[tmp];
                int j = i + 4;
                while (j <= hi && ec_tmp > eclass[fmap[j]]) {
                    fmap[j - 4] = fmap[j];
                    j += 4;
                }
                fmap[j - 4] = tmp;
            }
        }
        for (int i2 = hi - 1; i2 >= lo; i2--) {
            int tmp2 = fmap[i2];
            int ec_tmp2 = eclass[tmp2];
            int j2 = i2 + 1;
            while (j2 <= hi && ec_tmp2 > eclass[fmap[j2]]) {
                fmap[j2 - 1] = fmap[j2];
                j2++;
            }
            fmap[j2 - 1] = tmp2;
        }
    }

    void fallbackSort(BZip2CompressorOutputStream.Data data, int last) {
        data.block[0] = data.block[last + 1];
        fallbackSort(data.fmap, data.block, last + 1);
        for (int i = 0; i < last + 1; i++) {
            data.fmap[i] = r1[i] - 1;
        }
        for (int i2 = 0; i2 < last + 1; i2++) {
            if (data.fmap[i2] == -1) {
                data.fmap[i2] = last;
                return;
            }
        }
    }

    void fallbackSort(int[] fmap, byte[] block, int nblock) {
        int nNotDone;
        int[] ftab = new int[257];
        int[] eclass = getEclass();
        for (int i = 0; i < nblock; i++) {
            eclass[i] = 0;
        }
        for (int i2 = 0; i2 < nblock; i2++) {
            int i3 = block[i2] & UByte.MAX_VALUE;
            ftab[i3] = ftab[i3] + 1;
        }
        for (int i4 = 1; i4 < 257; i4++) {
            ftab[i4] = ftab[i4] + ftab[i4 - 1];
        }
        for (int i5 = 0; i5 < nblock; i5++) {
            int j = block[i5] & 255;
            int k = ftab[j] - 1;
            ftab[j] = k;
            fmap[k] = i5;
        }
        int j2 = nblock + 64;
        BitSet bhtab = new BitSet(j2);
        for (int i6 = 0; i6 < 256; i6++) {
            bhtab.set(ftab[i6]);
        }
        for (int i7 = 0; i7 < 32; i7++) {
            bhtab.set((i7 * 2) + nblock);
            bhtab.clear((i7 * 2) + nblock + 1);
        }
        int H = 1;
        do {
            int j3 = 0;
            for (int i8 = 0; i8 < nblock; i8++) {
                if (bhtab.get(i8)) {
                    j3 = i8;
                }
                int k2 = fmap[i8] - H;
                if (k2 < 0) {
                    k2 += nblock;
                }
                eclass[k2] = j3;
            }
            nNotDone = 0;
            int r = -1;
            while (true) {
                int l = bhtab.nextClearBit(r + 1) - 1;
                if (l < nblock && bhtab.nextSetBit(r11 + 1) - 1 < nblock) {
                    if (r > l) {
                        nNotDone += (r - l) + 1;
                        fallbackQSort3(fmap, eclass, l, r);
                        int cc = -1;
                        for (int i9 = l; i9 <= r; i9++) {
                            int cc1 = eclass[fmap[i9]];
                            if (cc != cc1) {
                                bhtab.set(i9);
                                cc = cc1;
                            }
                        }
                    }
                }
            }
            H *= 2;
            if (H > nblock) {
                return;
            }
        } while (nNotDone != 0);
    }

    private int[] fpop(int sp) {
        return new int[]{this.stack_ll[sp], this.stack_hh[sp]};
    }

    private void fpush(int sp, int lz, int hz) {
        this.stack_ll[sp] = lz;
        this.stack_hh[sp] = hz;
    }

    private void fswap(int[] fmap, int zz1, int zz2) {
        int zztmp = fmap[zz1];
        fmap[zz1] = fmap[zz2];
        fmap[zz2] = zztmp;
    }

    private void fvswap(int[] fmap, int yyp1, int yyp2, int yyn) {
        while (yyn > 0) {
            fswap(fmap, yyp1, yyp2);
            yyp1++;
            yyp2++;
            yyn--;
        }
    }

    private int[] getEclass() {
        if (this.eclass == null) {
            this.eclass = new int[this.quadrant.length / 2];
        }
        return this.eclass;
    }

    private void mainQSort3(BZip2CompressorOutputStream.Data dataShadow, int loSt, int hiSt, int dSt, int last) {
        int gtHi;
        int[] stack_ll = this.stack_ll;
        int[] stack_hh = this.stack_hh;
        int[] stack_dd = this.stack_dd;
        int[] fmap = dataShadow.fmap;
        byte[] block = dataShadow.block;
        stack_ll[0] = loSt;
        stack_hh[0] = hiSt;
        stack_dd[0] = dSt;
        int lo = 1;
        while (true) {
            int sp = lo - 1;
            if (sp >= 0) {
                int lo2 = stack_ll[sp];
                int hi = stack_hh[sp];
                int d = stack_dd[sp];
                if (hi - lo2 < 20 || d > 10) {
                    if (mainSimpleSort(dataShadow, lo2, hi, d, last)) {
                        return;
                    }
                } else {
                    int d1 = d + 1;
                    int med = med3(block[fmap[lo2] + d1] & UByte.MAX_VALUE, block[fmap[hi] + d1] & UByte.MAX_VALUE, block[fmap[(lo2 + hi) >>> 1] + d1] & UByte.MAX_VALUE);
                    int unLo = lo2;
                    int gtHi2 = hi;
                    int ltLo = lo2;
                    int gtHi3 = hi;
                    while (true) {
                        if (unLo <= gtHi2) {
                            int n = (block[fmap[unLo] + d1] & UByte.MAX_VALUE) - med;
                            if (n == 0) {
                                int temp = fmap[unLo];
                                fmap[unLo] = fmap[ltLo];
                                fmap[ltLo] = temp;
                                ltLo++;
                                unLo++;
                            } else if (n < 0) {
                                unLo++;
                            }
                        }
                        gtHi = gtHi3;
                        while (unLo <= gtHi2) {
                            int n2 = (block[fmap[gtHi2] + d1] & UByte.MAX_VALUE) - med;
                            if (n2 != 0) {
                                if (n2 <= 0) {
                                    break;
                                } else {
                                    gtHi2--;
                                }
                            } else {
                                int temp2 = fmap[gtHi2];
                                int unHi = gtHi2 - 1;
                                fmap[gtHi2] = fmap[gtHi];
                                fmap[gtHi] = temp2;
                                gtHi--;
                                gtHi2 = unHi;
                            }
                        }
                        if (unLo > gtHi2) {
                            break;
                        }
                        gtHi3 = gtHi;
                        int gtHi4 = fmap[unLo];
                        int unLo2 = unLo + 1;
                        fmap[unLo] = fmap[gtHi2];
                        int unHi2 = gtHi2 - 1;
                        fmap[gtHi2] = gtHi4;
                        gtHi2 = unHi2;
                        unLo = unLo2;
                    }
                    if (gtHi < ltLo) {
                        stack_ll[sp] = lo2;
                        stack_hh[sp] = hi;
                        stack_dd[sp] = d1;
                    } else {
                        int gtHi5 = gtHi;
                        int gtHi6 = unLo - ltLo;
                        int n3 = Math.min(ltLo - lo2, gtHi6);
                        vswap(fmap, lo2, unLo - n3, n3);
                        int m = Math.min(hi - gtHi5, gtHi5 - gtHi2);
                        vswap(fmap, unLo, (hi - m) + 1, m);
                        int n4 = ((lo2 + unLo) - ltLo) - 1;
                        int m2 = (hi - (gtHi5 - gtHi2)) + 1;
                        stack_ll[sp] = lo2;
                        stack_hh[sp] = n4;
                        stack_dd[sp] = d;
                        int sp2 = sp + 1;
                        stack_ll[sp2] = n4 + 1;
                        stack_hh[sp2] = m2 - 1;
                        stack_dd[sp2] = d1;
                        sp = sp2 + 1;
                        stack_ll[sp] = m2;
                        stack_hh[sp] = hi;
                        stack_dd[sp] = d;
                    }
                    sp++;
                }
                lo = sp;
            } else {
                return;
            }
        }
    }

    private boolean mainSimpleSort(BZip2CompressorOutputStream.Data dataShadow, int lo, int hi, int d, int lastShadow) {
        int bigN;
        int i = 1;
        int bigN2 = (hi - lo) + 1;
        if (bigN2 < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int hp = 0;
        while (INCS[hp] < bigN2) {
            hp++;
        }
        int[] fmap = dataShadow.fmap;
        char[] quadrant = this.quadrant;
        byte[] block = dataShadow.block;
        int lastPlus1 = lastShadow + 1;
        boolean firstAttemptShadow = this.firstAttempt;
        int workLimitShadow = this.workLimit;
        int workDoneShadow = this.workDone;
        loop1: while (true) {
            hp--;
            if (hp < 0) {
                break;
            }
            int h = INCS[hp];
            int mj = (lo + h) - i;
            int i2 = lo + h;
            while (i2 <= hi) {
                int k = 3;
                while (i2 <= hi) {
                    k--;
                    if (k < 0) {
                        break;
                    }
                    int v = fmap[i2];
                    int vd = v + d;
                    int j = i2;
                    boolean onceRunned = false;
                    int a = 0;
                    while (true) {
                        if (onceRunned) {
                            fmap[j] = a;
                            int i3 = j - h;
                            j = i3;
                            if (i3 <= mj) {
                                bigN = bigN2;
                                break;
                            }
                        } else {
                            onceRunned = true;
                        }
                        a = fmap[j - h];
                        int i1 = a + d;
                        bigN = bigN2;
                        if (block[i1 + 1] == block[vd + 1]) {
                            if (block[i1 + 2] == block[vd + 2]) {
                                if (block[i1 + 3] == block[vd + 3]) {
                                    if (block[i1 + 4] == block[vd + 4]) {
                                        if (block[i1 + 5] != block[vd + 5]) {
                                            if ((block[i1 + 5] & UByte.MAX_VALUE) > (block[vd + 5] & UByte.MAX_VALUE)) {
                                                bigN2 = bigN;
                                            }
                                        } else {
                                            int i12 = i1 + 6;
                                            int i22 = vd + 6;
                                            if (block[i12] == block[i22]) {
                                                int x = lastShadow;
                                                while (true) {
                                                    if (x > 0) {
                                                        int x2 = x - 4;
                                                        int i4 = block[i12 + 1];
                                                        int x3 = block[i22 + 1];
                                                        if (i4 == x3) {
                                                            if (quadrant[i12] == quadrant[i22]) {
                                                                if (block[i12 + 2] == block[i22 + 2]) {
                                                                    if (quadrant[i12 + 1] == quadrant[i22 + 1]) {
                                                                        if (block[i12 + 3] == block[i22 + 3]) {
                                                                            if (quadrant[i12 + 2] == quadrant[i22 + 2]) {
                                                                                if (block[i12 + 4] == block[i22 + 4]) {
                                                                                    if (quadrant[i12 + 3] == quadrant[i22 + 3]) {
                                                                                        i12 += 4;
                                                                                        if (i12 >= lastPlus1) {
                                                                                            i12 -= lastPlus1;
                                                                                        }
                                                                                        int i23 = i22 + 4;
                                                                                        if (i23 >= lastPlus1) {
                                                                                            i23 -= lastPlus1;
                                                                                        }
                                                                                        i22 = i23;
                                                                                        workDoneShadow++;
                                                                                        x = x2;
                                                                                    } else if (quadrant[i12 + 3] > quadrant[i22 + 3]) {
                                                                                    }
                                                                                } else if ((block[i12 + 4] & UByte.MAX_VALUE) > (block[i22 + 4] & UByte.MAX_VALUE)) {
                                                                                }
                                                                            } else if (quadrant[i12 + 2] > quadrant[i22 + 2]) {
                                                                            }
                                                                        } else if ((block[i12 + 3] & UByte.MAX_VALUE) > (block[i22 + 3] & UByte.MAX_VALUE)) {
                                                                        }
                                                                    } else if (quadrant[i12 + 1] > quadrant[i22 + 1]) {
                                                                    }
                                                                } else if ((block[i12 + 2] & UByte.MAX_VALUE) > (block[i22 + 2] & UByte.MAX_VALUE)) {
                                                                }
                                                            } else if (quadrant[i12] > quadrant[i22]) {
                                                            }
                                                        } else if ((block[i12 + 1] & UByte.MAX_VALUE) > (block[i22 + 1] & UByte.MAX_VALUE)) {
                                                        }
                                                    }
                                                }
                                                bigN2 = bigN;
                                            } else if ((block[i12] & UByte.MAX_VALUE) > (block[i22] & UByte.MAX_VALUE)) {
                                                bigN2 = bigN;
                                            }
                                        }
                                    } else if ((block[i1 + 4] & UByte.MAX_VALUE) > (block[vd + 4] & UByte.MAX_VALUE)) {
                                        bigN2 = bigN;
                                    }
                                } else if ((block[i1 + 3] & UByte.MAX_VALUE) > (block[vd + 3] & UByte.MAX_VALUE)) {
                                    bigN2 = bigN;
                                }
                            } else if ((block[i1 + 2] & UByte.MAX_VALUE) > (block[vd + 2] & UByte.MAX_VALUE)) {
                                bigN2 = bigN;
                            }
                        } else if ((block[i1 + 1] & UByte.MAX_VALUE) > (block[vd + 1] & UByte.MAX_VALUE)) {
                            bigN2 = bigN;
                        }
                    }
                    fmap[j] = v;
                    i2++;
                    bigN2 = bigN;
                }
                int bigN3 = bigN2;
                if (firstAttemptShadow && i2 <= hi && workDoneShadow > workLimitShadow) {
                    break loop1;
                }
                bigN2 = bigN3;
            }
            i = 1;
        }
        this.workDone = workDoneShadow;
        return firstAttemptShadow && workDoneShadow > workLimitShadow;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01e0, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01e5, code lost:
    
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01e6, code lost:
    
        if (r2 >= r3) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01e8, code lost:
    
        r5 = r11[r1 + r2];
        r0 = (char) (r2 >> r4);
        r12[r5] = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01f5, code lost:
    
        if (r5 >= 20) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x01f7, code lost:
    
        r12[(r5 + r27) + 1] = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01fd, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0164, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x016f, code lost:
    
        if (r1 > 255) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0171, code lost:
    
        r7[r1] = r9[(r1 << 8) + r18] & org.apache.commons.compress.compressors.bzip2.BlockSort.CLEARMASK;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x017d, code lost:
    
        r1 = r18 << 8;
        r1 = r9[r1] & org.apache.commons.compress.compressors.bzip2.BlockSort.CLEARMASK;
        r2 = r9[(r18 + 1) << 8] & org.apache.commons.compress.compressors.bzip2.BlockSort.CLEARMASK;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0189, code lost:
    
        if (r1 >= r2) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x018b, code lost:
    
        r4 = r11[r1];
        r19 = r3;
        r5 = r10[r4] & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0196, code lost:
    
        if (r8[r5] != false) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0198, code lost:
    
        r3 = r7[r5];
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x019a, code lost:
    
        if (r4 != 0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x019c, code lost:
    
        r17 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01a1, code lost:
    
        r11[r3] = r17;
        r7[r5] = r7[r5] + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01a9, code lost:
    
        r1 = r1 + 1;
        r3 = r19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x019f, code lost:
    
        r17 = r4 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b0, code lost:
    
        r19 = r3;
        r1 = 256;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01b4, code lost:
    
        r1 = r1 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01b6, code lost:
    
        if (r1 < 0) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01b8, code lost:
    
        r2 = (r1 << 8) + r18;
        r9[r2] = r9[r2] | 2097152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01c3, code lost:
    
        r8[r18] = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01c7, code lost:
    
        if (r15 >= 255) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01c9, code lost:
    
        r1 = r9[r18 << 8] & r19;
        r3 = (r9[(r18 + 1) << 8] & r19) - r1;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01de, code lost:
    
        if ((r3 >> r4) <= 65534) goto L114;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void mainSort(org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.Data r26, int r27) {
        /*
            Method dump skipped, instructions count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.mainSort(org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data, int):void");
    }
}
