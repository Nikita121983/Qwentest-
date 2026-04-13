package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class CodecEncoding {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final BHSDCodec[] canonicalCodec = {null, new BHSDCodec(1, 256), new BHSDCodec(1, 256, 1), new BHSDCodec(1, 256, 0, 1), new BHSDCodec(1, 256, 1, 1), new BHSDCodec(2, 256), new BHSDCodec(2, 256, 1), new BHSDCodec(2, 256, 0, 1), new BHSDCodec(2, 256, 1, 1), new BHSDCodec(3, 256), new BHSDCodec(3, 256, 1), new BHSDCodec(3, 256, 0, 1), new BHSDCodec(3, 256, 1, 1), new BHSDCodec(4, 256), new BHSDCodec(4, 256, 1), new BHSDCodec(4, 256, 0, 1), new BHSDCodec(4, 256, 1, 1), new BHSDCodec(5, 4), new BHSDCodec(5, 4, 1), new BHSDCodec(5, 4, 2), new BHSDCodec(5, 16), new BHSDCodec(5, 16, 1), new BHSDCodec(5, 16, 2), new BHSDCodec(5, 32), new BHSDCodec(5, 32, 1), new BHSDCodec(5, 32, 2), new BHSDCodec(5, 64), new BHSDCodec(5, 64, 1), new BHSDCodec(5, 64, 2), new BHSDCodec(5, 128), new BHSDCodec(5, 128, 1), new BHSDCodec(5, 128, 2), new BHSDCodec(5, 4, 0, 1), new BHSDCodec(5, 4, 1, 1), new BHSDCodec(5, 4, 2, 1), new BHSDCodec(5, 16, 0, 1), new BHSDCodec(5, 16, 1, 1), new BHSDCodec(5, 16, 2, 1), new BHSDCodec(5, 32, 0, 1), new BHSDCodec(5, 32, 1, 1), new BHSDCodec(5, 32, 2, 1), new BHSDCodec(5, 64, 0, 1), new BHSDCodec(5, 64, 1, 1), new BHSDCodec(5, 64, 2, 1), new BHSDCodec(5, 128, 0, 1), new BHSDCodec(5, 128, 1, 1), new BHSDCodec(5, 128, 2, 1), new BHSDCodec(2, 192), new BHSDCodec(2, 224), new BHSDCodec(2, 240), new BHSDCodec(2, 248), new BHSDCodec(2, 252), new BHSDCodec(2, 8, 0, 1), new BHSDCodec(2, 8, 1, 1), new BHSDCodec(2, 16, 0, 1), new BHSDCodec(2, 16, 1, 1), new BHSDCodec(2, 32, 0, 1), new BHSDCodec(2, 32, 1, 1), new BHSDCodec(2, 64, 0, 1), new BHSDCodec(2, 64, 1, 1), new BHSDCodec(2, 128, 0, 1), new BHSDCodec(2, 128, 1, 1), new BHSDCodec(2, 192, 0, 1), new BHSDCodec(2, 192, 1, 1), new BHSDCodec(2, 224, 0, 1), new BHSDCodec(2, 224, 1, 1), new BHSDCodec(2, 240, 0, 1), new BHSDCodec(2, 240, 1, 1), new BHSDCodec(2, 248, 0, 1), new BHSDCodec(2, 248, 1, 1), new BHSDCodec(3, 192), new BHSDCodec(3, 224), new BHSDCodec(3, 240), new BHSDCodec(3, 248), new BHSDCodec(3, 252), new BHSDCodec(3, 8, 0, 1), new BHSDCodec(3, 8, 1, 1), new BHSDCodec(3, 16, 0, 1), new BHSDCodec(3, 16, 1, 1), new BHSDCodec(3, 32, 0, 1), new BHSDCodec(3, 32, 1, 1), new BHSDCodec(3, 64, 0, 1), new BHSDCodec(3, 64, 1, 1), new BHSDCodec(3, 128, 0, 1), new BHSDCodec(3, 128, 1, 1), new BHSDCodec(3, 192, 0, 1), new BHSDCodec(3, 192, 1, 1), new BHSDCodec(3, 224, 0, 1), new BHSDCodec(3, 224, 1, 1), new BHSDCodec(3, 240, 0, 1), new BHSDCodec(3, 240, 1, 1), new BHSDCodec(3, 248, 0, 1), new BHSDCodec(3, 248, 1, 1), new BHSDCodec(4, 192), new BHSDCodec(4, 224), new BHSDCodec(4, 240), new BHSDCodec(4, 248), new BHSDCodec(4, 252), new BHSDCodec(4, 8, 0, 1), new BHSDCodec(4, 8, 1, 1), new BHSDCodec(4, 16, 0, 1), new BHSDCodec(4, 16, 1, 1), new BHSDCodec(4, 32, 0, 1), new BHSDCodec(4, 32, 1, 1), new BHSDCodec(4, 64, 0, 1), new BHSDCodec(4, 64, 1, 1), new BHSDCodec(4, 128, 0, 1), new BHSDCodec(4, 128, 1, 1), new BHSDCodec(4, 192, 0, 1), new BHSDCodec(4, 192, 1, 1), new BHSDCodec(4, 224, 0, 1), new BHSDCodec(4, 224, 1, 1), new BHSDCodec(4, 240, 0, 1), new BHSDCodec(4, 240, 1, 1), new BHSDCodec(4, 248, 0, 1), new BHSDCodec(4, 248, 1, 1)};
    private static Map<BHSDCodec, Integer> canonicalCodecsToSpecifiers;

    static {
        HashMap<BHSDCodec, Integer> reverseMap = new HashMap<>(canonicalCodec.length);
        for (int i = 0; i < canonicalCodec.length; i++) {
            reverseMap.put(canonicalCodec[i], Integer.valueOf(i));
        }
        canonicalCodecsToSpecifiers = reverseMap;
    }

    public static BHSDCodec getCanonicalCodec(int i) {
        return canonicalCodec[i];
    }

    public static Codec getCodec(int value, InputStream in, Codec defaultCodec) throws IOException, Pack200Exception {
        boolean bdef;
        Codec aCodec;
        Codec bCodec;
        if (canonicalCodec.length != 116) {
            throw new Error("Canonical encodings have been incorrectly modified");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Encoding cannot be less than zero");
        }
        if (value == 0) {
            return defaultCodec;
        }
        if (value <= 115) {
            return canonicalCodec[value];
        }
        if (value == 116) {
            int code = in.read();
            if (code == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            int d = code & 1;
            int s = 3 & (code >> 1);
            int b = ((code >> 3) & 7) + 1;
            int code2 = in.read();
            if (code2 == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            int h = code2 + 1;
            return new BHSDCodec(b, h, s, d);
        }
        if (value >= 117 && value <= 140) {
            int offset = value - 117;
            int kx = offset & 3;
            boolean kbflag = ((offset >> 2) & 1) == 1;
            boolean adef = ((offset >> 3) & 1) == 1;
            bdef = ((offset >> 4) & 1) == 1;
            if (adef && bdef) {
                throw new Pack200Exception("ADef and BDef should never both be true");
            }
            int kb = kbflag ? in.read() : 3;
            int k = (kb + 1) * ((int) Math.pow(16.0d, kx));
            if (adef) {
                aCodec = defaultCodec;
            } else {
                aCodec = getCodec(in.read(), in, defaultCodec);
            }
            if (bdef) {
                bCodec = defaultCodec;
            } else {
                bCodec = getCodec(in.read(), in, defaultCodec);
            }
            return new RunCodec(k, aCodec, bCodec);
        }
        if (value < 141 || value > 188) {
            throw new Pack200Exception("Invalid codec encoding byte (" + value + ") found");
        }
        int offset2 = value - 141;
        boolean fdef = (offset2 & 1) == 1;
        boolean udef = ((offset2 >> 1) & 1) == 1;
        int tdefl = offset2 >> 2;
        bdef = tdefl != 0;
        int[] tdefToL = {0, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252};
        int l = tdefToL[tdefl];
        if (bdef) {
            Codec fCodec = fdef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
            Codec uCodec = udef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
            return new PopulationCodec(fCodec, l, uCodec);
        }
        Codec fCodec2 = fdef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
        Codec tCodec = getCodec(in.read(), in, defaultCodec);
        Codec uCodec2 = udef ? defaultCodec : getCodec(in.read(), in, defaultCodec);
        return new PopulationCodec(fCodec2, tCodec, uCodec2);
    }

    public static int[] getSpecifier(Codec codec, Codec codec2) {
        int binarySearch;
        int i;
        int i2;
        if (canonicalCodecsToSpecifiers.containsKey(codec)) {
            return new int[]{canonicalCodecsToSpecifiers.get(codec).intValue()};
        }
        if (codec instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) codec;
            return new int[]{116, (bHSDCodec.isDelta() ? 1 : 0) + (bHSDCodec.getS() * 2) + ((bHSDCodec.getB() - 1) * 8), bHSDCodec.getH() - 1};
        }
        if (!(codec instanceof RunCodec)) {
            if (codec instanceof PopulationCodec) {
                PopulationCodec populationCodec = (PopulationCodec) codec;
                Codec tokenCodec = populationCodec.getTokenCodec();
                Codec favouredCodec = populationCodec.getFavouredCodec();
                Codec unfavouredCodec = populationCodec.getUnfavouredCodec();
                boolean equals = favouredCodec.equals(codec2);
                boolean equals2 = unfavouredCodec.equals(codec2);
                int i3 = 0;
                if (populationCodec.getFavoured() != null) {
                    if (tokenCodec == Codec.BYTE1) {
                        i3 = 1;
                    } else if (tokenCodec instanceof BHSDCodec) {
                        BHSDCodec bHSDCodec2 = (BHSDCodec) tokenCodec;
                        if (bHSDCodec2.getS() == 0 && (binarySearch = Arrays.binarySearch(new int[]{4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252}, 256 - bHSDCodec2.getH())) != -1) {
                            int i4 = binarySearch + 1;
                            i3 = binarySearch;
                        }
                    }
                }
                int i5 = (equals ? 1 : 0) + 141 + ((equals2 ? 1 : 0) * 2) + (i3 * 4);
                int[] specifier = equals ? EMPTY_INT_ARRAY : getSpecifier(favouredCodec, codec2);
                int[] specifier2 = i3 != 0 ? EMPTY_INT_ARRAY : getSpecifier(tokenCodec, codec2);
                int[] specifier3 = equals2 ? EMPTY_INT_ARRAY : getSpecifier(unfavouredCodec, codec2);
                int[] iArr = new int[specifier.length + 1 + specifier3.length + specifier2.length];
                iArr[0] = i5;
                int i6 = 1;
                for (int i7 : specifier) {
                    iArr[i6] = i7;
                    i6++;
                }
                for (int i8 : specifier2) {
                    iArr[i6] = i8;
                    i6++;
                }
                for (int i9 : specifier3) {
                    iArr[i6] = i9;
                    i6++;
                }
                return iArr;
            }
            return null;
        }
        RunCodec runCodec = (RunCodec) codec;
        int k = runCodec.getK();
        if (k <= 256) {
            i = 0;
            i2 = k - 1;
        } else if (k <= 4096) {
            i = 1;
            i2 = (k / 16) - 1;
        } else if (k <= 65536) {
            i = 2;
            i2 = (k / 256) - 1;
        } else {
            i = 3;
            i2 = (k / 4096) - 1;
        }
        Codec aCodec = runCodec.getACodec();
        Codec bCodec = runCodec.getBCodec();
        int i10 = 0;
        if (aCodec.equals(codec2)) {
            i10 = 1;
        } else if (bCodec.equals(codec2)) {
            i10 = 2;
        }
        int i11 = i + 117 + (i2 == 3 ? 0 : 4) + (i10 * 8);
        int[] specifier4 = i10 == 1 ? EMPTY_INT_ARRAY : getSpecifier(aCodec, codec2);
        int[] specifier5 = i10 == 2 ? EMPTY_INT_ARRAY : getSpecifier(bCodec, codec2);
        int[] iArr2 = new int[(i2 == 3 ? 0 : 1) + 1 + specifier4.length + specifier5.length];
        iArr2[0] = i11;
        int i12 = 1;
        if (i2 != 3) {
            iArr2[1] = i2;
            i12 = 1 + 1;
        }
        for (int i13 : specifier4) {
            iArr2[i12] = i13;
            i12++;
        }
        for (int i14 : specifier5) {
            iArr2[i12] = i14;
            i12++;
        }
        return iArr2;
    }

    public static int getSpecifierForDefaultCodec(BHSDCodec defaultCodec) {
        return getSpecifier(defaultCodec, null)[0];
    }
}
