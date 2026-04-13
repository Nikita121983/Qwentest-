package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntToLongFunction;
import org.apache.commons.compress.utils.ExactMath;

/* loaded from: classes9.dex */
public final class BHSDCodec extends Codec {
    private final int b;
    private long cardinality;
    private final int d;
    private final int h;
    private final int l;
    private final long largest;
    private final long[] powers;
    private final int s;
    private final long smallest;

    public BHSDCodec(int b, int h) {
        this(b, h, 0, 0);
    }

    public BHSDCodec(int b, int h, int s) {
        this(b, h, s, 0);
    }

    public BHSDCodec(int b, final int h, int s, int d) {
        if (b < 1 || b > 5) {
            throw new IllegalArgumentException("1<=b<=5");
        }
        if (h < 1 || h > 256) {
            throw new IllegalArgumentException("1<=h<=256");
        }
        if (s < 0 || s > 2) {
            throw new IllegalArgumentException("0<=s<=2");
        }
        if (d < 0 || d > 1) {
            throw new IllegalArgumentException("0<=d<=1");
        }
        if (b == 1 && h != 256) {
            throw new IllegalArgumentException("b=1 -> h=256");
        }
        if (h == 256 && b == 5) {
            throw new IllegalArgumentException("h=256 -> b!=5");
        }
        this.b = b;
        this.h = h;
        this.s = s;
        this.d = d;
        this.l = 256 - h;
        if (h == 1) {
            this.cardinality = (b * 255) + 1;
        } else {
            this.cardinality = (long) (((long) ((this.l * (1.0d - Math.pow(h, b))) / (1 - h))) + Math.pow(h, b));
        }
        this.smallest = calculateSmallest();
        this.largest = calculateLargest();
        this.powers = new long[b];
        Arrays.setAll(this.powers, new IntToLongFunction() { // from class: org.apache.commons.compress.harmony.pack200.BHSDCodec$$ExternalSyntheticLambda0
            @Override // java.util.function.IntToLongFunction
            public final long applyAsLong(int i) {
                return BHSDCodec.lambda$new$0(h, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ long lambda$new$0(int h, int c) {
        return (long) Math.pow(h, c);
    }

    private long calculateLargest() {
        long result;
        if (this.d == 1) {
            return new BHSDCodec(this.b, this.h).largest();
        }
        switch (this.s) {
            case 0:
                result = cardinality() - 1;
                break;
            case 1:
                result = (cardinality() / 2) - 1;
                break;
            case 2:
                result = ((cardinality() * 3) / 4) - 1;
                break;
            default:
                throw new Error("Unknown s value");
        }
        return Math.min((this.s == 0 ? 4294967294L : 2147483647L) - 1, result);
    }

    private long calculateSmallest() {
        if (this.d == 1 || !isSigned()) {
            long result = this.cardinality;
            if (result >= 4294967296L) {
                return -2147483648L;
            }
            return 0L;
        }
        long result2 = Math.max(-2147483648L, (-cardinality()) / (1 << this.s));
        return result2;
    }

    public long cardinality() {
        return this.cardinality;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream in) throws IOException, Pack200Exception {
        if (this.d != 0) {
            throw new Pack200Exception("Delta encoding used without passing in last value; this is a coding error");
        }
        return decode(in, 0L);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream in, long last) throws IOException, Pack200Exception {
        long x;
        int n = 0;
        long z = 0;
        do {
            x = in.read();
            this.lastBandLength++;
            z += this.powers[n] * x;
            n++;
            if (x < this.l) {
                break;
            }
        } while (n < this.b);
        if (x == -1) {
            throw new EOFException("End of stream reached whilst decoding");
        }
        if (isSigned()) {
            int u = (1 << this.s) - 1;
            if ((u & z) == u) {
                z = ~(z >>> this.s);
            } else {
                z -= z >>> this.s;
            }
        }
        if (isDelta()) {
            z += last;
        }
        return (int) z;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int n, InputStream in) throws IOException, Pack200Exception {
        int[] band = super.decodeInts(n, in);
        if (isDelta()) {
            for (int i = 0; i < band.length; i++) {
                while (band[i] > this.largest) {
                    band[i] = (int) (band[i] - this.cardinality);
                }
                while (band[i] < this.smallest) {
                    band[i] = ExactMath.add(band[i], this.cardinality);
                }
            }
        }
        return band;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int n, InputStream in, int firstValue) throws IOException, Pack200Exception {
        int[] band = super.decodeInts(n, in, firstValue);
        if (isDelta()) {
            for (int i = 0; i < band.length; i++) {
                while (band[i] > this.largest) {
                    band[i] = (int) (band[i] - this.cardinality);
                }
                while (band[i] < this.smallest) {
                    band[i] = ExactMath.add(band[i], this.cardinality);
                }
            }
        }
        return band;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int value) throws Pack200Exception {
        return encode(value, 0);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int value, int last) throws Pack200Exception {
        long byteN;
        if (!encodes(value)) {
            throw new Pack200Exception("The codec " + this + " does not encode the value " + value);
        }
        long z = value;
        if (isDelta()) {
            z -= last;
        }
        if (isSigned()) {
            if (z < -2147483648L) {
                z += 4294967296L;
            } else if (z > 2147483647L) {
                z -= 4294967296L;
            }
            if (z < 0) {
                z = ((-z) << this.s) - 1;
            } else if (this.s == 1) {
                z <<= this.s;
            } else {
                z += (z - (z % 3)) / 3;
            }
        } else if (z < 0) {
            z += Math.min(this.cardinality, 4294967296L);
        }
        if (z < 0) {
            throw new Pack200Exception("unable to encode");
        }
        List<Byte> byteList = new ArrayList<>();
        for (int n = 0; n < this.b; n++) {
            if (z < this.l) {
                byteN = z;
            } else {
                byteN = z % this.h;
                while (byteN < this.l) {
                    byteN += this.h;
                }
            }
            byteList.add(Byte.valueOf((byte) byteN));
            if (byteN < this.l) {
                break;
            }
            z = (z - byteN) / this.h;
        }
        int n2 = byteList.size();
        byte[] bytes = new byte[n2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = byteList.get(i).byteValue();
        }
        return bytes;
    }

    public boolean encodes(long value) {
        return value >= this.smallest && value <= this.largest;
    }

    public boolean equals(Object o) {
        if (!(o instanceof BHSDCodec)) {
            return false;
        }
        BHSDCodec codec = (BHSDCodec) o;
        return codec.b == this.b && codec.h == this.h && codec.s == this.s && codec.d == this.d;
    }

    public int getB() {
        return this.b;
    }

    public int getH() {
        return this.h;
    }

    public int getL() {
        return this.l;
    }

    public int getS() {
        return this.s;
    }

    public int hashCode() {
        return (((((this.b * 37) + this.h) * 37) + this.s) * 37) + this.d;
    }

    public boolean isDelta() {
        return this.d != 0;
    }

    public boolean isSigned() {
        return this.s != 0;
    }

    public long largest() {
        return this.largest;
    }

    public long smallest() {
        return this.smallest;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(11);
        buffer.append('(');
        buffer.append(this.b);
        buffer.append(',');
        buffer.append(this.h);
        if (this.s != 0 || this.d != 0) {
            buffer.append(',');
            buffer.append(this.s);
        }
        if (this.d != 0) {
            buffer.append(',');
            buffer.append(this.d);
        }
        buffer.append(')');
        return buffer.toString();
    }
}
