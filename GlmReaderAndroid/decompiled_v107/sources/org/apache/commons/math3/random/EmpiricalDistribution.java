package org.apache.commons.math3.random;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ConstantRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.StatisticalSummary;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

/* loaded from: classes10.dex */
public class EmpiricalDistribution extends AbstractRealDistribution {
    public static final int DEFAULT_BIN_COUNT = 1000;
    private static final String FILE_CHARSET = "US-ASCII";
    private static final long serialVersionUID = 5729073523949762654L;
    private final int binCount;
    private final List<SummaryStatistics> binStats;
    private double delta;
    private boolean loaded;
    private double max;
    private double min;
    protected final RandomDataGenerator randomData;
    private SummaryStatistics sampleStats;
    private double[] upperBounds;

    public EmpiricalDistribution() {
        this(1000);
    }

    public EmpiricalDistribution(int binCount) {
        this(binCount, new RandomDataGenerator());
    }

    public EmpiricalDistribution(int binCount, RandomGenerator generator) {
        this(binCount, new RandomDataGenerator(generator));
    }

    public EmpiricalDistribution(RandomGenerator generator) {
        this(1000, generator);
    }

    @Deprecated
    public EmpiricalDistribution(int binCount, RandomDataImpl randomData) {
        this(binCount, randomData.getDelegate());
    }

    @Deprecated
    public EmpiricalDistribution(RandomDataImpl randomData) {
        this(1000, randomData);
    }

    private EmpiricalDistribution(int binCount, RandomDataGenerator randomData) {
        super(randomData.getRandomGenerator());
        this.sampleStats = null;
        this.max = Double.NEGATIVE_INFINITY;
        this.min = Double.POSITIVE_INFINITY;
        this.delta = 0.0d;
        this.loaded = false;
        this.upperBounds = null;
        if (binCount <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(binCount));
        }
        this.binCount = binCount;
        this.randomData = randomData;
        this.binStats = new ArrayList();
    }

    public void load(double[] in) throws NullArgumentException {
        DataAdapter da = new ArrayDataAdapter(in);
        try {
            da.computeStats();
            fillBinStats(new ArrayDataAdapter(in));
            this.loaded = true;
        } catch (IOException e) {
            throw new MathInternalError();
        }
    }

    public void load(URL url) throws IOException, NullArgumentException, ZeroException {
        MathUtils.checkNotNull(url);
        Charset charset = Charset.forName(FILE_CHARSET);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), charset));
        try {
            DataAdapter da = new StreamDataAdapter(in);
            da.computeStats();
            if (this.sampleStats.getN() == 0) {
                throw new ZeroException(LocalizedFormats.URL_CONTAINS_NO_DATA, url);
            }
            BufferedReader in2 = new BufferedReader(new InputStreamReader(url.openStream(), charset));
            fillBinStats(new StreamDataAdapter(in2));
            this.loaded = true;
            try {
                in2.close();
            } catch (IOException e) {
            }
        } catch (Throwable th) {
            try {
                in.close();
            } catch (IOException e2) {
            }
            throw th;
        }
    }

    public void load(File file) throws IOException, NullArgumentException {
        MathUtils.checkNotNull(file);
        Charset charset = Charset.forName(FILE_CHARSET);
        InputStream is = new FileInputStream(file);
        BufferedReader in = new BufferedReader(new InputStreamReader(is, charset));
        try {
            DataAdapter da = new StreamDataAdapter(in);
            da.computeStats();
            InputStream is2 = new FileInputStream(file);
            in = new BufferedReader(new InputStreamReader(is2, charset));
            fillBinStats(new StreamDataAdapter(in));
            this.loaded = true;
            try {
                in.close();
            } catch (IOException e) {
            }
        } catch (Throwable th) {
            try {
                in.close();
            } catch (IOException e2) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public abstract class DataAdapter {
        public abstract void computeBinStats() throws IOException;

        public abstract void computeStats() throws IOException;

        private DataAdapter() {
        }
    }

    /* loaded from: classes10.dex */
    private class StreamDataAdapter extends DataAdapter {
        private BufferedReader inputStream;

        StreamDataAdapter(BufferedReader in) {
            super();
            this.inputStream = in;
        }

        @Override // org.apache.commons.math3.random.EmpiricalDistribution.DataAdapter
        public void computeBinStats() throws IOException {
            while (true) {
                String str = this.inputStream.readLine();
                if (str != null) {
                    double val = Double.parseDouble(str);
                    SummaryStatistics stats = (SummaryStatistics) EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(val));
                    stats.addValue(val);
                } else {
                    this.inputStream.close();
                    this.inputStream = null;
                    return;
                }
            }
        }

        @Override // org.apache.commons.math3.random.EmpiricalDistribution.DataAdapter
        public void computeStats() throws IOException {
            EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            while (true) {
                String str = this.inputStream.readLine();
                if (str != null) {
                    double val = Double.parseDouble(str);
                    EmpiricalDistribution.this.sampleStats.addValue(val);
                } else {
                    this.inputStream.close();
                    this.inputStream = null;
                    return;
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    private class ArrayDataAdapter extends DataAdapter {
        private double[] inputArray;

        ArrayDataAdapter(double[] in) throws NullArgumentException {
            super();
            MathUtils.checkNotNull(in);
            this.inputArray = in;
        }

        @Override // org.apache.commons.math3.random.EmpiricalDistribution.DataAdapter
        public void computeStats() throws IOException {
            EmpiricalDistribution.this.sampleStats = new SummaryStatistics();
            for (int i = 0; i < this.inputArray.length; i++) {
                EmpiricalDistribution.this.sampleStats.addValue(this.inputArray[i]);
            }
        }

        @Override // org.apache.commons.math3.random.EmpiricalDistribution.DataAdapter
        public void computeBinStats() throws IOException {
            for (int i = 0; i < this.inputArray.length; i++) {
                SummaryStatistics stats = (SummaryStatistics) EmpiricalDistribution.this.binStats.get(EmpiricalDistribution.this.findBin(this.inputArray[i]));
                stats.addValue(this.inputArray[i]);
            }
        }
    }

    private void fillBinStats(DataAdapter da) throws IOException {
        this.min = this.sampleStats.getMin();
        this.max = this.sampleStats.getMax();
        this.delta = (this.max - this.min) / this.binCount;
        if (!this.binStats.isEmpty()) {
            this.binStats.clear();
        }
        for (int i = 0; i < this.binCount; i++) {
            SummaryStatistics stats = new SummaryStatistics();
            this.binStats.add(i, stats);
        }
        da.computeBinStats();
        this.upperBounds = new double[this.binCount];
        this.upperBounds[0] = this.binStats.get(0).getN() / this.sampleStats.getN();
        for (int i2 = 1; i2 < this.binCount - 1; i2++) {
            this.upperBounds[i2] = this.upperBounds[i2 - 1] + (this.binStats.get(i2).getN() / this.sampleStats.getN());
        }
        this.upperBounds[this.binCount - 1] = 1.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int findBin(double value) {
        return FastMath.min(FastMath.max(((int) FastMath.ceil((value - this.min) / this.delta)) - 1, 0), this.binCount - 1);
    }

    public double getNextValue() throws MathIllegalStateException {
        if (!this.loaded) {
            throw new MathIllegalStateException(LocalizedFormats.DISTRIBUTION_NOT_LOADED, new Object[0]);
        }
        return sample();
    }

    public StatisticalSummary getSampleStats() {
        return this.sampleStats;
    }

    public int getBinCount() {
        return this.binCount;
    }

    public List<SummaryStatistics> getBinStats() {
        return this.binStats;
    }

    public double[] getUpperBounds() {
        double[] binUpperBounds = new double[this.binCount];
        for (int i = 0; i < this.binCount - 1; i++) {
            binUpperBounds[i] = this.min + (this.delta * (i + 1));
        }
        int i2 = this.binCount;
        binUpperBounds[i2 - 1] = this.max;
        return binUpperBounds;
    }

    public double[] getGeneratorUpperBounds() {
        int len = this.upperBounds.length;
        double[] out = new double[len];
        System.arraycopy(this.upperBounds, 0, out, 0, len);
        return out;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public void reSeed(long seed) {
        this.randomData.reSeed(seed);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double probability(double x) {
        return 0.0d;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double density(double x) {
        if (x < this.min || x > this.max) {
            return 0.0d;
        }
        int binIndex = findBin(x);
        RealDistribution kernel = getKernel(this.binStats.get(binIndex));
        return (kernel.density(x) * pB(binIndex)) / kB(binIndex);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double cumulativeProbability(double x) {
        if (x < this.min) {
            return 0.0d;
        }
        if (x >= this.max) {
            return 1.0d;
        }
        int binIndex = findBin(x);
        double pBminus = pBminus(binIndex);
        double pB = pB(binIndex);
        RealDistribution kernel = k(x);
        if (kernel instanceof ConstantRealDistribution) {
            if (x < kernel.getNumericalMean()) {
                return pBminus;
            }
            return pBminus + pB;
        }
        double[] binBounds = getUpperBounds();
        double kB = kB(binIndex);
        double lower = binIndex == 0 ? this.min : binBounds[binIndex - 1];
        double withinBinCum = (kernel.cumulativeProbability(x) - kernel.cumulativeProbability(lower)) / kB;
        return pBminus + (pB * withinBinCum);
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0d || p > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(p), 0, 1);
        }
        if (p == 0.0d) {
            return getSupportLowerBound();
        }
        if (p == 1.0d) {
            return getSupportUpperBound();
        }
        int i = 0;
        while (cumBinP(i) < p) {
            i++;
        }
        RealDistribution kernel = getKernel(this.binStats.get(i));
        double kB = kB(i);
        double[] binBounds = getUpperBounds();
        double lower = i == 0 ? this.min : binBounds[i - 1];
        double kBminus = kernel.cumulativeProbability(lower);
        double pB = pB(i);
        double pBminus = pBminus(i);
        double pCrit = p - pBminus;
        if (pCrit <= 0.0d) {
            return lower;
        }
        return kernel.inverseCumulativeProbability(((pCrit * kB) / pB) + kBminus);
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalMean() {
        return this.sampleStats.getMean();
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getNumericalVariance() {
        return this.sampleStats.getVariance();
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportLowerBound() {
        return this.min;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public double getSupportUpperBound() {
        return this.max;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.RealDistribution
    public boolean isSupportConnected() {
        return true;
    }

    @Override // org.apache.commons.math3.distribution.AbstractRealDistribution, org.apache.commons.math3.distribution.RealDistribution
    public void reseedRandomGenerator(long seed) {
        this.randomData.reSeed(seed);
    }

    private double pB(int i) {
        double[] dArr = this.upperBounds;
        return i == 0 ? dArr[0] : dArr[i] - this.upperBounds[i - 1];
    }

    private double pBminus(int i) {
        if (i == 0) {
            return 0.0d;
        }
        return this.upperBounds[i - 1];
    }

    private double kB(int i) {
        double d;
        double d2;
        double[] binBounds = getUpperBounds();
        RealDistribution kernel = getKernel(this.binStats.get(i));
        if (i == 0) {
            d = this.min;
            d2 = binBounds[0];
        } else {
            d = binBounds[i - 1];
            d2 = binBounds[i];
        }
        return kernel.cumulativeProbability(d, d2);
    }

    private RealDistribution k(double x) {
        int binIndex = findBin(x);
        return getKernel(this.binStats.get(binIndex));
    }

    private double cumBinP(int binIndex) {
        return this.upperBounds[binIndex];
    }

    protected RealDistribution getKernel(SummaryStatistics bStats) {
        if (bStats.getN() == 1 || bStats.getVariance() == 0.0d) {
            return new ConstantRealDistribution(bStats.getMean());
        }
        return new NormalDistribution(this.randomData.getRandomGenerator(), bStats.getMean(), bStats.getStandardDeviation(), 1.0E-9d);
    }
}
