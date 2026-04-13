package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.ExactMath;

/* loaded from: classes9.dex */
public class RunCodec extends Codec {
    private final Codec aCodec;
    private final Codec bCodec;
    private int k;
    private int last;

    public RunCodec(int k, Codec aCodec, Codec bCodec) throws Pack200Exception {
        if (k <= 0) {
            throw new Pack200Exception("Cannot have a RunCodec for a negative number of numbers");
        }
        if (aCodec == null || bCodec == null) {
            throw new Pack200Exception("Must supply both codecs for a RunCodec");
        }
        this.k = k;
        this.aCodec = aCodec;
        this.bCodec = bCodec;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream in) throws IOException, Pack200Exception {
        return decode(in, this.last);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int decode(InputStream in, long last) throws IOException, Pack200Exception {
        int i = this.k - 1;
        this.k = i;
        if (i >= 0) {
            int value = this.aCodec.decode(in, this.last);
            this.last = this.k == 0 ? 0 : value;
            return normalise(value, this.aCodec);
        }
        this.last = this.bCodec.decode(in, this.last);
        return normalise(this.last, this.bCodec);
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public int[] decodeInts(int n, InputStream in) throws IOException, Pack200Exception {
        int[] aValues = this.aCodec.decodeInts(this.k, in);
        normalise(aValues, this.aCodec);
        int[] bValues = this.bCodec.decodeInts(n - this.k, in);
        normalise(bValues, this.bCodec);
        int[] band = new int[check(n, in)];
        System.arraycopy(aValues, 0, band, 0, this.k);
        System.arraycopy(bValues, 0, band, this.k, n - this.k);
        this.lastBandLength = this.aCodec.lastBandLength + this.bCodec.lastBandLength;
        return band;
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int value) throws Pack200Exception {
        throw new Pack200Exception("Must encode entire band at once with a RunCodec");
    }

    @Override // org.apache.commons.compress.harmony.pack200.Codec
    public byte[] encode(int value, int last) throws Pack200Exception {
        throw new Pack200Exception("Must encode entire band at once with a RunCodec");
    }

    public Codec getACodec() {
        return this.aCodec;
    }

    public Codec getBCodec() {
        return this.bCodec;
    }

    public int getK() {
        return this.k;
    }

    private int normalise(int value, Codec codecUsed) {
        if (codecUsed instanceof BHSDCodec) {
            BHSDCodec bhsd = (BHSDCodec) codecUsed;
            if (bhsd.isDelta()) {
                long cardinality = bhsd.cardinality();
                while (value > bhsd.largest()) {
                    value = (int) (value - cardinality);
                }
                while (value < bhsd.smallest()) {
                    value = ExactMath.add(value, cardinality);
                }
            }
        }
        return value;
    }

    private void normalise(int[] band, Codec codecUsed) {
        if (codecUsed instanceof BHSDCodec) {
            BHSDCodec bhsd = (BHSDCodec) codecUsed;
            if (bhsd.isDelta()) {
                long cardinality = bhsd.cardinality();
                for (int i = 0; i < band.length; i++) {
                    while (band[i] > bhsd.largest()) {
                        band[i] = (int) (band[i] - cardinality);
                    }
                    while (band[i] < bhsd.smallest()) {
                        band[i] = ExactMath.add(band[i], cardinality);
                    }
                }
                return;
            }
            return;
        }
        if (codecUsed instanceof PopulationCodec) {
            PopulationCodec popCodec = (PopulationCodec) codecUsed;
            int[] favoured = (int[]) popCodec.getFavoured().clone();
            Arrays.sort(favoured);
            for (int i2 = 0; i2 < band.length; i2++) {
                boolean favouredValue = Arrays.binarySearch(favoured, band[i2]) > -1;
                Codec theCodec = favouredValue ? popCodec.getFavouredCodec() : popCodec.getUnfavouredCodec();
                if (theCodec instanceof BHSDCodec) {
                    BHSDCodec bhsd2 = (BHSDCodec) theCodec;
                    if (bhsd2.isDelta()) {
                        long cardinality2 = bhsd2.cardinality();
                        while (band[i2] > bhsd2.largest()) {
                            band[i2] = (int) (band[i2] - cardinality2);
                        }
                        while (band[i2] < bhsd2.smallest()) {
                            band[i2] = ExactMath.add(band[i2], cardinality2);
                        }
                    }
                }
            }
        }
    }

    public String toString() {
        return "RunCodec[k=" + this.k + ";aCodec=" + this.aCodec + "bCodec=" + this.bCodec + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
