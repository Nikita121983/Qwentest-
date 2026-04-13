package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.function.IntFunction;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.CodecEncoding;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PopulationCodec;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.utils.ExactMath;
import org.apache.commons.lang3.ArrayUtils;

/* loaded from: classes9.dex */
public abstract class BandSet {
    protected SegmentHeader header;
    protected Segment segment;

    public abstract void read(InputStream inputStream) throws IOException, Pack200Exception;

    public abstract void unpack() throws IOException, Pack200Exception;

    public BandSet(Segment segment) {
        this.segment = segment;
        this.header = segment.getSegmentHeader();
    }

    public int[] decodeBandInt(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] band;
        Codec codecUsed;
        int[] getFirst;
        int first;
        if (count < 0) {
            throw new Pack200Exception("count < 0");
        }
        Codec codecUsed2 = codec;
        boolean z = true;
        if (codec.getB() == 1 || count == 0) {
            return codec.decodeInts(count, in);
        }
        int[] getFirst2 = codec.decodeInts(1, in);
        if (getFirst2.length == 0) {
            return getFirst2;
        }
        boolean z2 = false;
        int first2 = getFirst2[0];
        int i = -1;
        if (codec.isSigned() && first2 >= -256 && first2 <= -1) {
            codecUsed2 = CodecEncoding.getCodec((-1) - first2, this.header.getBandHeadersInputStream(), codec);
            band = codecUsed2.decodeInts(count, in);
        } else if (!codec.isSigned() && first2 >= codec.getL() && first2 <= codec.getL() + 255) {
            codecUsed2 = CodecEncoding.getCodec(first2 - codec.getL(), this.header.getBandHeadersInputStream(), codec);
            band = codecUsed2.decodeInts(count, in);
        } else {
            band = codec.decodeInts(count - 1, in, first2);
        }
        if (codecUsed2 instanceof PopulationCodec) {
            PopulationCodec popCodec = (PopulationCodec) codecUsed2;
            int[] favoured = (int[]) popCodec.getFavoured().clone();
            Arrays.sort(favoured);
            int i2 = 0;
            while (i2 < band.length) {
                boolean favouredValue = Arrays.binarySearch(favoured, band[i2]) > i ? z : z2;
                Codec theCodec = favouredValue ? popCodec.getFavouredCodec() : popCodec.getUnfavouredCodec();
                if (!(theCodec instanceof BHSDCodec) || !((BHSDCodec) theCodec).isDelta()) {
                    codecUsed = codecUsed2;
                    getFirst = getFirst2;
                    first = first2;
                } else {
                    BHSDCodec bhsd = (BHSDCodec) theCodec;
                    first = first2;
                    long cardinality = bhsd.cardinality();
                    while (true) {
                        codecUsed = codecUsed2;
                        getFirst = getFirst2;
                        if (band[i2] <= bhsd.largest()) {
                            break;
                        }
                        band[i2] = (int) (band[i2] - cardinality);
                        getFirst2 = getFirst;
                        codecUsed2 = codecUsed;
                    }
                    while (band[i2] < bhsd.smallest()) {
                        band[i2] = ExactMath.add(band[i2], cardinality);
                    }
                }
                i2++;
                first2 = first;
                getFirst2 = getFirst;
                codecUsed2 = codecUsed;
                z = true;
                z2 = false;
                i = -1;
            }
        }
        return band;
    }

    public int[][] decodeBandInt(String name, InputStream in, BHSDCodec defaultCodec, int[] counts) throws IOException, Pack200Exception {
        int[][] result = new int[counts.length];
        int totalCount = 0;
        for (int count : counts) {
            totalCount += count;
        }
        int[] twoDResult = decodeBandInt(name, in, defaultCodec, totalCount);
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            if (counts[i] > twoDResult.length) {
                throw new IOException("Counts value exceeds length of twoDResult");
            }
            result[i] = new int[counts[i]];
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = twoDResult[index];
                index++;
            }
        }
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getReferences$0(String[] reference, int[] ints, int i) {
        return reference[ints[i]];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[] getReferences(final int[] ints, final String[] reference) {
        return (String[]) ArrayUtils.setAll(new String[ints.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return BandSet.lambda$getReferences$0(reference, ints, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String[][] getReferences(int[][] ints, String[] reference) {
        String[][] result = new String[ints.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = new String[ints[i].length];
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = reference[ints[i][j]];
            }
        }
        return result;
    }

    public CPClass[] parseCPClassReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPClass[]) ArrayUtils.setAll(new CPClass[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPClass cpClassValue;
                cpClassValue = CpBands.this.cpClassValue(indices[i]);
                return cpClassValue;
            }
        });
    }

    public CPNameAndType[] parseCPDescriptorReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPNameAndType[]) ArrayUtils.setAll(new CPNameAndType[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda8
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPNameAndType cpNameAndTypeValue;
                cpNameAndTypeValue = CpBands.this.cpNameAndTypeValue(indices[i]);
                return cpNameAndTypeValue;
            }
        });
    }

    public CPDouble[] parseCPDoubleReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPDouble[]) ArrayUtils.setAll(new CPDouble[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda7
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPDouble cpDoubleValue;
                cpDoubleValue = CpBands.this.cpDoubleValue(indices[i]);
                return cpDoubleValue;
            }
        });
    }

    public CPFieldRef[] parseCPFieldRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPFieldRef[]) ArrayUtils.setAll(new CPFieldRef[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPFieldRef cpFieldValue;
                cpFieldValue = CpBands.this.cpFieldValue(indices[i]);
                return cpFieldValue;
            }
        });
    }

    public CPFloat[] parseCPFloatReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPFloat[]) ArrayUtils.setAll(new CPFloat[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda12
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPFloat cpFloatValue;
                cpFloatValue = CpBands.this.cpFloatValue(indices[i]);
                return cpFloatValue;
            }
        });
    }

    public CPInterfaceMethodRef[] parseCPInterfaceMethodRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPInterfaceMethodRef[]) ArrayUtils.setAll(new CPInterfaceMethodRef[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda4
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPInterfaceMethodRef cpIMethodValue;
                cpIMethodValue = CpBands.this.cpIMethodValue(indices[i]);
                return cpIMethodValue;
            }
        });
    }

    public CPInteger[] parseCPIntReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] reference = cpBands.getCpInt();
        int[] indices = decodeBandInt(name, in, codec, count);
        CPInteger[] result = new CPInteger[indices.length];
        for (int i = 0; i < count; i++) {
            int index = indices[i];
            if (index < 0 || index >= reference.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            }
            result[i] = cpBands.cpIntegerValue(index);
        }
        return result;
    }

    public CPLong[] parseCPLongReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        long[] reference = cpBands.getCpLong();
        int[] indices = decodeBandInt(name, in, codec, count);
        CPLong[] result = new CPLong[indices.length];
        for (int i = 0; i < count; i++) {
            int index = indices[i];
            if (index < 0 || index >= reference.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            }
            result[i] = cpBands.cpLongValue(index);
        }
        return result;
    }

    public CPMethodRef[] parseCPMethodRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPMethodRef[]) ArrayUtils.setAll(new CPMethodRef[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda5
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPMethodRef cpMethodValue;
                cpMethodValue = CpBands.this.cpMethodValue(indices[i]);
                return cpMethodValue;
            }
        });
    }

    public CPUTF8[] parseCPSignatureReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPUTF8[]) ArrayUtils.setAll(new CPUTF8[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda9
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPUTF8 cpSignatureValue;
                cpSignatureValue = CpBands.this.cpSignatureValue(indices[i]);
                return cpSignatureValue;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CPUTF8[][] parseCPSignatureReferences(String name, InputStream in, BHSDCodec codec, int[] counts) throws IOException, Pack200Exception {
        int sum = 0;
        for (int count : counts) {
            sum += count;
        }
        final int[] indices = decodeBandInt(name, in, codec, sum);
        final CpBands cpBands = this.segment.getCpBands();
        CPUTF8[] result1 = (CPUTF8[]) ArrayUtils.setAll(new CPUTF8[sum], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPUTF8 cpSignatureValue;
                cpSignatureValue = CpBands.this.cpSignatureValue(indices[i]);
                return cpSignatureValue;
            }
        });
        int pos = 0;
        CPUTF8[][] result = new CPUTF8[counts.length];
        for (int i = 0; i < counts.length; i++) {
            int num = counts[i];
            result[i] = new CPUTF8[num];
            System.arraycopy(result1, pos, result[i], 0, num);
            pos += num;
        }
        return result;
    }

    public CPString[] parseCPStringReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPString[]) ArrayUtils.setAll(new CPString[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda11
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPString cpStringValue;
                cpStringValue = CpBands.this.cpStringValue(indices[i]);
                return cpStringValue;
            }
        });
    }

    public CPUTF8[] parseCPUTF8References(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        final int[] indices = decodeBandInt(name, in, codec, count);
        final CpBands cpBands = this.segment.getCpBands();
        return (CPUTF8[]) ArrayUtils.setAll(new CPUTF8[indices.length], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda3
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                CPUTF8 cpUTF8Value;
                cpUTF8Value = CpBands.this.cpUTF8Value(indices[i]);
                return cpUTF8Value;
            }
        });
    }

    public CPUTF8[][] parseCPUTF8References(String name, InputStream in, BHSDCodec codec, int[] counts) throws IOException, Pack200Exception {
        CPUTF8[][] result = new CPUTF8[counts.length];
        int sum = 0;
        for (int i = 0; i < counts.length; i++) {
            result[i] = new CPUTF8[counts[i]];
            sum += counts[i];
        }
        final int[] indices = decodeBandInt(name, in, codec, sum);
        final CpBands cpBands = this.segment.getCpBands();
        CPUTF8[] result1 = (CPUTF8[]) ArrayUtils.setAll(new CPUTF8[sum], new IntFunction() { // from class: org.apache.commons.compress.harmony.unpack200.BandSet$$ExternalSyntheticLambda10
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                CPUTF8 cpUTF8Value;
                cpUTF8Value = CpBands.this.cpUTF8Value(indices[i2]);
                return cpUTF8Value;
            }
        });
        int pos = 0;
        for (int i2 = 0; i2 < counts.length; i2++) {
            int num = counts[i2];
            result[i2] = new CPUTF8[num];
            System.arraycopy(result1, pos, result[i2], 0, num);
            pos += num;
        }
        return result;
    }

    public long[] parseFlags(String name, InputStream in, int count, BHSDCodec hiCodec, BHSDCodec loCodec) throws IOException, Pack200Exception {
        return parseFlags(name, in, new int[]{count}, hiCodec, loCodec)[0];
    }

    public long[] parseFlags(String name, InputStream in, int count, BHSDCodec codec, boolean hasHi) throws IOException, Pack200Exception {
        return parseFlags(name, in, new int[]{count}, hasHi ? codec : null, codec)[0];
    }

    public long[][] parseFlags(String name, InputStream in, int[] counts, BHSDCodec hiCodec, BHSDCodec loCodec) throws IOException, Pack200Exception {
        int count = counts.length;
        if (count == 0) {
            return new long[][]{new long[0]};
        }
        int sum = 0;
        long[][] result = new long[count];
        for (int i : counts) {
            sum += i;
        }
        int[] hi = null;
        if (hiCodec != null) {
            hi = decodeBandInt(name, in, hiCodec, sum);
        }
        int[] lo = decodeBandInt(name, in, loCodec, sum);
        int index = 0;
        for (int i2 = 0; i2 < count; i2++) {
            result[i2] = new long[counts[i2]];
            for (int j = 0; j < result[i2].length; j++) {
                if (hi != null) {
                    result[i2][j] = (hi[index] << 32) | (lo[index] & 4294967295L);
                } else {
                    result[i2][j] = lo[index];
                }
                index++;
            }
        }
        return result;
    }

    public long[][] parseFlags(String name, InputStream in, int[] counts, BHSDCodec codec, boolean hasHi) throws IOException, Pack200Exception {
        return parseFlags(name, in, counts, hasHi ? codec : null, codec);
    }

    public String[] parseReferences(String name, InputStream in, BHSDCodec codec, int count, String[] reference) throws IOException, Pack200Exception {
        return parseReferences(name, in, codec, new int[]{count}, reference)[0];
    }

    public String[][] parseReferences(String name, InputStream in, BHSDCodec codec, int[] counts, String[] reference) throws IOException, Pack200Exception {
        int count = counts.length;
        if (count == 0) {
            return new String[][]{new String[0]};
        }
        int sum = 0;
        for (int i : counts) {
            sum += i;
        }
        int[] indices = decodeBandInt(name, in, codec, sum);
        String[] result1 = new String[sum];
        for (int i1 = 0; i1 < sum; i1++) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            }
            result1[i1] = reference[index];
        }
        String[][] result = new String[count];
        int pos = 0;
        for (int i2 = 0; i2 < count; i2++) {
            int num = counts[i2];
            result[i2] = new String[num];
            System.arraycopy(result1, pos, result[i2], 0, num);
            pos += num;
        }
        return result;
    }

    public void unpack(InputStream in) throws IOException, Pack200Exception {
        read(in);
        unpack();
    }
}
