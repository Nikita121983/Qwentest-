package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.IntStream;

/* loaded from: classes9.dex */
public abstract class BandSet {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final int[] effortThresholds = {0, 0, 1000, 500, 100, 100, 100, 100, 100, 0};
    private long[] canonicalLargest;
    private long[] canonicalSmallest;
    final int effort;
    protected final SegmentHeader segmentHeader;

    public abstract void pack(OutputStream outputStream) throws IOException, Pack200Exception;

    /* loaded from: classes9.dex */
    public class BandAnalysisResults {
        private Codec betterCodec;
        private byte[] encodedBand;
        private int[] extraMetadata;
        private int numCodecsTried;
        private int saved;

        static /* synthetic */ int access$408(BandAnalysisResults x0) {
            int i = x0.numCodecsTried;
            x0.numCodecsTried = i + 1;
            return i;
        }

        static /* synthetic */ int access$412(BandAnalysisResults x0, int x1) {
            int i = x0.numCodecsTried + x1;
            x0.numCodecsTried = i;
            return i;
        }

        static /* synthetic */ int access$612(BandAnalysisResults x0, int x1) {
            int i = x0.saved + x1;
            x0.saved = i;
            return i;
        }

        public BandAnalysisResults() {
        }
    }

    /* loaded from: classes9.dex */
    public class BandData {
        private double averageAbsoluteDelta;
        private double averageAbsoluteValue;
        private final int[] band;
        private int deltaIsAscending;
        private Map<Integer, Integer> distinctValues;
        private int largest;
        private int largestDelta;
        private int smallDeltaCount;
        private int smallest;
        private int smallestDelta;

        public BandData(int[] band) {
            Integer count;
            this.smallest = Integer.MAX_VALUE;
            this.largest = Integer.MIN_VALUE;
            this.band = band;
            for (int i = 0; i < band.length; i++) {
                if (band[i] < this.smallest) {
                    this.smallest = band[i];
                }
                if (band[i] > this.largest) {
                    this.largest = band[i];
                }
                if (i != 0) {
                    int delta = band[i] - band[i - 1];
                    if (delta < this.smallestDelta) {
                        this.smallestDelta = delta;
                    }
                    if (delta > this.largestDelta) {
                        this.largestDelta = delta;
                    }
                    if (delta >= 0) {
                        this.deltaIsAscending++;
                    }
                    this.averageAbsoluteDelta += Math.abs(delta) / (band.length - 1);
                    if (Math.abs(delta) < 256) {
                        this.smallDeltaCount++;
                    }
                } else {
                    this.smallestDelta = band[0];
                    this.largestDelta = band[0];
                }
                this.averageAbsoluteValue += Math.abs(band[i]) / band.length;
                if (BandSet.this.effort > 3) {
                    if (this.distinctValues == null) {
                        this.distinctValues = new HashMap();
                    }
                    Integer value = Integer.valueOf(band[i]);
                    Integer count2 = this.distinctValues.get(value);
                    if (count2 != null) {
                        count = Integer.valueOf(count2.intValue() + 1);
                    } else {
                        count = 1;
                    }
                    this.distinctValues.put(value, count);
                }
            }
        }

        public boolean anyNegatives() {
            return this.smallest < 0;
        }

        public boolean mainlyPositiveDeltas() {
            return ((float) this.deltaIsAscending) / ((float) this.band.length) > 0.95f;
        }

        public boolean mainlySmallDeltas() {
            return ((float) this.smallDeltaCount) / ((float) this.band.length) > 0.7f;
        }

        public int numDistinctValues() {
            if (this.distinctValues == null) {
                return this.band.length;
            }
            return this.distinctValues.size();
        }

        public boolean wellCorrelated() {
            return this.averageAbsoluteDelta * 3.1d < this.averageAbsoluteValue;
        }
    }

    public BandSet(int effort, SegmentHeader header) {
        this.effort = effort;
        this.segmentHeader = header;
    }

    private BandAnalysisResults analyseBand(String name, int[] band, BHSDCodec defaultCodec) throws Pack200Exception {
        BandAnalysisResults results = new BandAnalysisResults();
        if (this.canonicalLargest == null) {
            this.canonicalLargest = new long[116];
            this.canonicalSmallest = new long[116];
            for (int i = 1; i < this.canonicalLargest.length; i++) {
                this.canonicalLargest[i] = CodecEncoding.getCanonicalCodec(i).largest();
                this.canonicalSmallest[i] = CodecEncoding.getCanonicalCodec(i).smallest();
            }
        }
        BandData bandData = new BandData(band);
        byte[] encoded = defaultCodec.encode(band);
        results.encodedBand = encoded;
        if (encoded.length <= (band.length + 23) - (this.effort * 2)) {
            return results;
        }
        if (!bandData.anyNegatives() && bandData.largest <= Codec.BYTE1.largest()) {
            results.encodedBand = Codec.BYTE1.encode(band);
            results.betterCodec = Codec.BYTE1;
            return results;
        }
        if (this.effort > 3 && !name.equals("POPULATION")) {
            int numDistinctValues = bandData.numDistinctValues();
            float distinctValuesAsProportion = numDistinctValues / band.length;
            if (numDistinctValues < 100 || distinctValuesAsProportion < 0.02d || (this.effort > 6 && distinctValuesAsProportion < 0.04d)) {
                encodeWithPopulationCodec(band, defaultCodec, bandData, results);
                if (timeToStop(results)) {
                    return results;
                }
            }
        }
        List<BHSDCodec[]> codecFamiliesToTry = new ArrayList<>();
        if (bandData.mainlyPositiveDeltas() && bandData.mainlySmallDeltas()) {
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs2);
        }
        if (bandData.wellCorrelated()) {
            if (bandData.mainlyPositiveDeltas()) {
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs3);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs4);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs5);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs3);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs4);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs5);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs2);
            } else {
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs3);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs2);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs4);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs5);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs2);
            }
        } else if (bandData.anyNegatives()) {
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs2);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs2);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs3);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs4);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs5);
        } else {
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs3);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs4);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs5);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs2);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs3);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs4);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs5);
        }
        for (BHSDCodec[] family : codecFamiliesToTry) {
            int[] band2 = band;
            BHSDCodec defaultCodec2 = defaultCodec;
            tryCodecs(band2, defaultCodec2, bandData, results, encoded, family);
            if (timeToStop(results)) {
                break;
            }
            band = band2;
            defaultCodec = defaultCodec2;
        }
        return results;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] cpEntryListToArray(List<? extends ConstantPoolEntry> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i).getIndex();
            if (array[i] < 0) {
                throw new IllegalArgumentException("Index should be > 0");
            }
        }
        return array;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] cpEntryOrNullListToArray(List<? extends ConstantPoolEntry> list) {
        int[] array = new int[list.size()];
        for (int j = 0; j < array.length; j++) {
            ConstantPoolEntry cpEntry = list.get(j);
            array[j] = cpEntry == null ? 0 : cpEntry.getIndex() + 1;
            if (cpEntry != null && cpEntry.getIndex() < 0) {
                throw new IllegalArgumentException("Index should be > 0");
            }
        }
        return array;
    }

    public byte[] encodeBandInt(String name, int[] ints, BHSDCodec defaultCodec) throws Pack200Exception {
        int specifier;
        byte[] encodedBand = null;
        if (this.effort > 1 && ints.length >= effortThresholds[this.effort]) {
            BandAnalysisResults results = analyseBand(name, ints, defaultCodec);
            Codec betterCodec = results.betterCodec;
            encodedBand = results.encodedBand;
            if (betterCodec != null) {
                if (betterCodec instanceof BHSDCodec) {
                    int[] specifierBand = CodecEncoding.getSpecifier(betterCodec, defaultCodec);
                    int specifier2 = specifierBand[0];
                    if (specifierBand.length > 1) {
                        for (int i = 1; i < specifierBand.length; i++) {
                            this.segmentHeader.appendBandCodingSpecifier(specifierBand[i]);
                        }
                    }
                    if (defaultCodec.isSigned()) {
                        specifier = (-1) - specifier2;
                    } else {
                        int specifier3 = defaultCodec.getL();
                        specifier = specifier3 + specifier2;
                    }
                    byte[] specifierEncoded = defaultCodec.encode(new int[]{specifier});
                    byte[] band = new byte[specifierEncoded.length + encodedBand.length];
                    System.arraycopy(specifierEncoded, 0, band, 0, specifierEncoded.length);
                    System.arraycopy(encodedBand, 0, band, specifierEncoded.length, encodedBand.length);
                    return band;
                }
                if (betterCodec instanceof PopulationCodec) {
                    IntStream of = IntStream.of(results.extraMetadata);
                    final SegmentHeader segmentHeader = this.segmentHeader;
                    Objects.requireNonNull(segmentHeader);
                    of.forEach(new IntConsumer() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda0
                        @Override // java.util.function.IntConsumer
                        public final void accept(int i2) {
                            SegmentHeader.this.appendBandCodingSpecifier(i2);
                        }
                    });
                    return encodedBand;
                }
                boolean z = betterCodec instanceof RunCodec;
            }
        }
        if (ints.length > 0) {
            if (encodedBand == null) {
                encodedBand = defaultCodec.encode(ints);
            }
            int first = ints[0];
            if (defaultCodec.getB() != 1) {
                if (defaultCodec.isSigned() && first >= -256 && first <= -1) {
                    int specifier4 = (-1) - CodecEncoding.getSpecifierForDefaultCodec(defaultCodec);
                    byte[] specifierEncoded2 = defaultCodec.encode(new int[]{specifier4});
                    byte[] band2 = new byte[specifierEncoded2.length + encodedBand.length];
                    System.arraycopy(specifierEncoded2, 0, band2, 0, specifierEncoded2.length);
                    System.arraycopy(encodedBand, 0, band2, specifierEncoded2.length, encodedBand.length);
                    return band2;
                }
                if (!defaultCodec.isSigned() && first >= defaultCodec.getL() && first <= defaultCodec.getL() + 255) {
                    int specifier5 = CodecEncoding.getSpecifierForDefaultCodec(defaultCodec) + defaultCodec.getL();
                    byte[] specifierEncoded3 = defaultCodec.encode(new int[]{specifier5});
                    byte[] band3 = new byte[specifierEncoded3.length + encodedBand.length];
                    System.arraycopy(specifierEncoded3, 0, band3, 0, specifierEncoded3.length);
                    System.arraycopy(encodedBand, 0, band3, specifierEncoded3.length, encodedBand.length);
                    return band3;
                }
            }
            return encodedBand;
        }
        return EMPTY_BYTE_ARRAY;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] encodeFlags(String name, final long[] flags, BHSDCodec loCodec, BHSDCodec hiCodec, boolean haveHiFlags) throws Pack200Exception {
        if (!haveHiFlags) {
            int[] loBits = new int[flags.length];
            Arrays.setAll(loBits, new IntUnaryOperator() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda2
                @Override // java.util.function.IntUnaryOperator
                public final int applyAsInt(int i) {
                    return BandSet.lambda$encodeFlags$0(flags, i);
                }
            });
            return encodeBandInt(name, loBits, loCodec);
        }
        int[] hiBits = new int[flags.length];
        int[] loBits2 = new int[flags.length];
        for (int i = 0; i < flags.length; i++) {
            long l = flags[i];
            hiBits[i] = (int) (l >> 32);
            loBits2[i] = (int) l;
        }
        byte[] hi = encodeBandInt(name, hiBits, hiCodec);
        byte[] lo = encodeBandInt(name, loBits2, loCodec);
        byte[] total = new byte[hi.length + lo.length];
        System.arraycopy(hi, 0, total, 0, hi.length);
        System.arraycopy(lo, 0, total, hi.length + 1, lo.length);
        return total;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$encodeFlags$0(long[] flags, int i) {
        return (int) flags[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] encodeFlags(String name, long[][] flags, BHSDCodec loCodec, BHSDCodec hiCodec, boolean haveHiFlags) throws Pack200Exception {
        return encodeFlags(name, flatten(flags), loCodec, hiCodec, haveHiFlags);
    }

    public byte[] encodeScalar(int value, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(value);
    }

    public byte[] encodeScalar(int[] band, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(band);
    }

    private void encodeWithPopulationCodec(int[] band, BHSDCodec defaultCodec, BandData bandData, BandAnalysisResults results) throws Pack200Exception {
        Codec tokenCodec;
        int l;
        byte[] tokensEncoded;
        int specifier;
        BandAnalysisResults.access$412(results, 3);
        final Map<Integer, Integer> distinctValues = bandData.distinctValues;
        final List<Integer> favored = new ArrayList<>();
        distinctValues.forEach(new BiConsumer() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda4
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                BandSet.lambda$encodeWithPopulationCodec$1(distinctValues, favored, (Integer) obj, (Integer) obj2);
            }
        });
        if (distinctValues.size() > 255) {
            favored.sort(new Comparator() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda5
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compareTo;
                    compareTo = ((Integer) r0.get((Integer) obj2)).compareTo((Integer) distinctValues.get((Integer) obj));
                    return compareTo;
                }
            });
        }
        Map<Integer, Integer> favoredToIndex = new HashMap<>();
        for (int i = 0; i < favored.size(); i++) {
            favoredToIndex.put(favored.get(i), Integer.valueOf(i));
        }
        IntList unfavoured = new IntList();
        int[] tokens = new int[band.length];
        for (int i2 = 0; i2 < band.length; i2++) {
            Integer favouredIndex = favoredToIndex.get(Integer.valueOf(band[i2]));
            if (favouredIndex == null) {
                tokens[i2] = 0;
                unfavoured.add(band[i2]);
            } else {
                tokens[i2] = favouredIndex.intValue() + 1;
            }
        }
        int i3 = favored.size();
        favored.add(favored.get(i3 - 1));
        int[] favouredBand = integerListToArray(favored);
        int[] unfavouredBand = unfavoured.toArray();
        BandAnalysisResults favouredResults = analyseBand("POPULATION", favouredBand, defaultCodec);
        BandAnalysisResults unfavouredResults = analyseBand("POPULATION", unfavouredBand, defaultCodec);
        int tdefL = 0;
        int k = favored.size() - 1;
        if (k < 256) {
            tdefL = 1;
            tokensEncoded = Codec.BYTE1.encode(tokens);
            l = 0;
            tokenCodec = null;
        } else {
            BandAnalysisResults tokenResults = analyseBand("POPULATION", tokens, defaultCodec);
            tokenCodec = tokenResults.betterCodec;
            byte[] tokensEncoded2 = tokenResults.encodedBand;
            if (tokenCodec == null) {
                tokenCodec = defaultCodec;
            }
            int l2 = ((BHSDCodec) tokenCodec).getL();
            int h = ((BHSDCodec) tokenCodec).getH();
            int s = ((BHSDCodec) tokenCodec).getS();
            int b = ((BHSDCodec) tokenCodec).getB();
            boolean isDelta = ((BHSDCodec) tokenCodec).isDelta();
            if (s == 0 && !isDelta) {
                boolean canUseTDefL = true;
                if (b > 1) {
                    BHSDCodec oneLowerB = new BHSDCodec(b - 1, h);
                    if (oneLowerB.largest() >= k) {
                        canUseTDefL = false;
                    }
                }
                if (canUseTDefL) {
                    switch (l2) {
                        case 4:
                            tdefL = 1;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 8:
                            tdefL = 2;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 16:
                            tdefL = 3;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 32:
                            tdefL = 4;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 64:
                            tdefL = 5;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 128:
                            tdefL = 6;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 192:
                            tdefL = 7;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 224:
                            tdefL = 8;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 240:
                            tdefL = 9;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 248:
                            tdefL = 10;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                        case 252:
                            tdefL = 11;
                            l = l2;
                            tokensEncoded = tokensEncoded2;
                            break;
                    }
                }
            }
            l = l2;
            tokensEncoded = tokensEncoded2;
        }
        byte[] favouredEncoded = favouredResults.encodedBand;
        byte[] unfavouredEncoded = unfavouredResults.encodedBand;
        Codec favouredCodec = favouredResults.betterCodec;
        Codec unfavouredCodec = unfavouredResults.betterCodec;
        int specifier2 = (favouredCodec == null ? 1 : 0) + 141 + (tdefL * 4) + (unfavouredCodec == null ? 2 : 0);
        final IntList extraBandMetadata = new IntList(3);
        if (favouredCodec != null) {
            IntStream of = IntStream.of(CodecEncoding.getSpecifier(favouredCodec, null));
            Objects.requireNonNull(extraBandMetadata);
            of.forEach(new IntConsumer() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda6
                @Override // java.util.function.IntConsumer
                public final void accept(int i4) {
                    IntList.this.add(i4);
                }
            });
        }
        if (tdefL == 0) {
            IntStream of2 = IntStream.of(CodecEncoding.getSpecifier(tokenCodec, null));
            Objects.requireNonNull(extraBandMetadata);
            of2.forEach(new IntConsumer() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda6
                @Override // java.util.function.IntConsumer
                public final void accept(int i4) {
                    IntList.this.add(i4);
                }
            });
        }
        if (unfavouredCodec != null) {
            IntStream of3 = IntStream.of(CodecEncoding.getSpecifier(unfavouredCodec, null));
            Objects.requireNonNull(extraBandMetadata);
            of3.forEach(new IntConsumer() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda6
                @Override // java.util.function.IntConsumer
                public final void accept(int i4) {
                    IntList.this.add(i4);
                }
            });
        }
        int[] extraMetadata = extraBandMetadata.toArray();
        byte[] extraMetadataEncoded = Codec.UNSIGNED5.encode(extraMetadata);
        if (!defaultCodec.isSigned()) {
            specifier = specifier2 + defaultCodec.getL();
        } else {
            specifier = (-1) - specifier2;
        }
        byte[] firstValueEncoded = defaultCodec.encode(new int[]{specifier});
        int totalBandLength = firstValueEncoded.length + favouredEncoded.length + tokensEncoded.length + unfavouredEncoded.length;
        int length = extraMetadataEncoded.length + totalBandLength;
        int[] unfavouredBand2 = results.encodedBand;
        if (length < unfavouredBand2.length) {
            BandAnalysisResults.access$612(results, results.encodedBand.length - (extraMetadataEncoded.length + totalBandLength));
            byte[] encodedBand = new byte[totalBandLength];
            System.arraycopy(firstValueEncoded, 0, encodedBand, 0, firstValueEncoded.length);
            System.arraycopy(favouredEncoded, 0, encodedBand, firstValueEncoded.length, favouredEncoded.length);
            System.arraycopy(tokensEncoded, 0, encodedBand, firstValueEncoded.length + favouredEncoded.length, tokensEncoded.length);
            System.arraycopy(unfavouredEncoded, 0, encodedBand, firstValueEncoded.length + favouredEncoded.length + tokensEncoded.length, unfavouredEncoded.length);
            results.encodedBand = encodedBand;
            results.extraMetadata = extraMetadata;
            if (l != 0) {
                results.betterCodec = new PopulationCodec(favouredCodec, l, unfavouredCodec);
            } else {
                results.betterCodec = new PopulationCodec(favouredCodec, tokenCodec, unfavouredCodec);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$encodeWithPopulationCodec$1(Map distinctValues, List favored, Integer k, Integer v) {
        if (v.intValue() > 2 || distinctValues.size() < 256) {
            favored.add(k);
        }
    }

    private long[] flatten(long[][] flags) {
        int totalSize = 0;
        for (long[] jArr : flags) {
            totalSize += jArr.length;
        }
        long[] flatArray = new long[totalSize];
        int index = 0;
        for (long[] flag : flags) {
            for (long element : flag) {
                flatArray[index] = element;
                index++;
            }
        }
        return flatArray;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int[] integerListToArray(List<Integer> integerList) {
        return integerList.stream().mapToInt(new ToIntFunction() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda3
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int intValue;
                intValue = ((Integer) obj).intValue();
                return intValue;
            }
        }).toArray();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long[] longListToArray(List<Long> longList) {
        return longList.stream().mapToLong(new ToLongFunction() { // from class: org.apache.commons.compress.harmony.pack200.BandSet$$ExternalSyntheticLambda1
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                long longValue;
                longValue = ((Long) obj).longValue();
                return longValue;
            }
        }).toArray();
    }

    private boolean timeToStop(BandAnalysisResults results) {
        return this.effort > 6 ? results.numCodecsTried >= this.effort * 2 : results.numCodecsTried >= this.effort;
    }

    private void tryCodecs(int[] band, BHSDCodec defaultCodec, BandData bandData, BandAnalysisResults results, byte[] encoded, BHSDCodec[] potentialCodecs) throws Pack200Exception {
        for (BHSDCodec potential : potentialCodecs) {
            if (potential.equals(defaultCodec)) {
                return;
            }
            if (potential.isDelta()) {
                if (potential.largest() >= bandData.largestDelta && potential.smallest() <= bandData.smallestDelta && potential.largest() >= bandData.largest && potential.smallest() <= bandData.smallest) {
                    byte[] encoded2 = potential.encode(band);
                    BandAnalysisResults.access$408(results);
                    byte[] specifierEncoded = defaultCodec.encode(CodecEncoding.getSpecifier(potential, null));
                    int saved = (encoded.length - encoded2.length) - specifierEncoded.length;
                    if (saved > results.saved) {
                        results.betterCodec = potential;
                        results.encodedBand = encoded2;
                        results.saved = saved;
                    }
                }
            } else if (potential.largest() >= bandData.largest && potential.smallest() <= bandData.smallest) {
                byte[] encoded22 = potential.encode(band);
                BandAnalysisResults.access$408(results);
                byte[] specifierEncoded2 = defaultCodec.encode(CodecEncoding.getSpecifier(potential, null));
                int saved2 = (encoded.length - encoded22.length) - specifierEncoded2.length;
                if (saved2 > results.saved) {
                    results.betterCodec = potential;
                    results.encodedBand = encoded22;
                    results.saved = saved2;
                }
            }
            if (timeToStop(results)) {
                return;
            }
        }
    }
}
