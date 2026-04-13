package org.apache.commons.math3.stat.descriptive.rank;

import java.io.Serializable;
import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic;
import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.KthSelector;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.MedianOf3PivotingStrategy;
import org.apache.commons.math3.util.PivotingStrategyInterface;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class Percentile extends AbstractUnivariateStatistic implements Serializable {
    private static final int MAX_CACHED_LEVELS = 10;
    private static final int PIVOTS_HEAP_LENGTH = 512;
    private static final long serialVersionUID = -8091216485095130416L;
    private int[] cachedPivots;
    private final EstimationType estimationType;
    private final KthSelector kthSelector;
    private final NaNStrategy nanStrategy;
    private double quantile;

    public Percentile() {
        this(50.0d);
    }

    public Percentile(double quantile) throws MathIllegalArgumentException {
        this(quantile, EstimationType.LEGACY, NaNStrategy.REMOVED, new KthSelector(new MedianOf3PivotingStrategy()));
    }

    public Percentile(Percentile original) throws NullArgumentException {
        MathUtils.checkNotNull(original);
        this.estimationType = original.getEstimationType();
        this.nanStrategy = original.getNaNStrategy();
        this.kthSelector = original.getKthSelector();
        setData(original.getDataRef());
        if (original.cachedPivots != null) {
            System.arraycopy(original.cachedPivots, 0, this.cachedPivots, 0, original.cachedPivots.length);
        }
        setQuantile(original.quantile);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Percentile(double quantile, EstimationType estimationType, NaNStrategy nanStrategy, KthSelector kthSelector) throws MathIllegalArgumentException {
        setQuantile(quantile);
        this.cachedPivots = null;
        MathUtils.checkNotNull(estimationType);
        MathUtils.checkNotNull(nanStrategy);
        MathUtils.checkNotNull(kthSelector);
        this.estimationType = estimationType;
        this.nanStrategy = nanStrategy;
        this.kthSelector = kthSelector;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic
    public void setData(double[] values) {
        if (values == null) {
            this.cachedPivots = null;
        } else {
            this.cachedPivots = new int[512];
            Arrays.fill(this.cachedPivots, -1);
        }
        super.setData(values);
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic
    public void setData(double[] values, int begin, int length) throws MathIllegalArgumentException {
        if (values == null) {
            this.cachedPivots = null;
        } else {
            this.cachedPivots = new int[512];
            Arrays.fill(this.cachedPivots, -1);
        }
        super.setData(values, begin, length);
    }

    public double evaluate(double p) throws MathIllegalArgumentException {
        return evaluate(getDataRef(), p);
    }

    public double evaluate(double[] values, double p) throws MathIllegalArgumentException {
        test(values, 0, 0);
        return evaluate(values, 0, values.length, p);
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.util.MathArrays.Function
    public double evaluate(double[] values, int start, int length) throws MathIllegalArgumentException {
        return evaluate(values, start, length, this.quantile);
    }

    public double evaluate(double[] values, int begin, int length, double p) throws MathIllegalArgumentException {
        test(values, begin, length);
        if (p > 100.0d || p <= 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(p), 0, 100);
        }
        if (length == 0) {
            return Double.NaN;
        }
        if (length == 1) {
            return values[begin];
        }
        double[] work = getWorkArray(values, begin, length);
        int[] pivotsHeap = getPivots(values);
        if (work.length == 0) {
            return Double.NaN;
        }
        return this.estimationType.evaluate(work, pivotsHeap, p, this.kthSelector);
    }

    @Deprecated
    int medianOf3(double[] work, int begin, int end) {
        return new MedianOf3PivotingStrategy().pivotIndex(work, begin, end);
    }

    public double getQuantile() {
        return this.quantile;
    }

    public void setQuantile(double p) throws MathIllegalArgumentException {
        if (p <= 0.0d || p > 100.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(p), 0, 100);
        }
        this.quantile = p;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public Percentile copy() {
        return new Percentile(this);
    }

    @Deprecated
    public static void copy(Percentile source, Percentile dest) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    protected double[] getWorkArray(double[] values, int begin, int length) {
        if (values == getDataRef()) {
            double[] work = getDataRef();
            return work;
        }
        switch (this.nanStrategy) {
            case MAXIMAL:
                double[] work2 = replaceAndSlice(values, begin, length, Double.NaN, Double.POSITIVE_INFINITY);
                return work2;
            case MINIMAL:
                double[] work3 = replaceAndSlice(values, begin, length, Double.NaN, Double.NEGATIVE_INFINITY);
                return work3;
            case REMOVED:
                double[] work4 = removeAndSlice(values, begin, length, Double.NaN);
                return work4;
            case FAILED:
                double[] work5 = copyOf(values, begin, length);
                MathArrays.checkNotNaN(work5);
                return work5;
            default:
                double[] work6 = copyOf(values, begin, length);
                return work6;
        }
    }

    private static double[] copyOf(double[] values, int begin, int length) {
        MathArrays.verifyValues(values, begin, length);
        return MathArrays.copyOfRange(values, begin, begin + length);
    }

    private static double[] replaceAndSlice(double[] values, int begin, int length, double original, double replacement) {
        double[] temp = copyOf(values, begin, length);
        for (int i = 0; i < length; i++) {
            temp[i] = Precision.equalsIncludingNaN(original, temp[i]) ? replacement : temp[i];
        }
        return temp;
    }

    private static double[] removeAndSlice(double[] values, int begin, int length, double removedValue) {
        MathArrays.verifyValues(values, begin, length);
        BitSet bits = new BitSet(length);
        for (int i = begin; i < begin + length; i++) {
            if (Precision.equalsIncludingNaN(removedValue, values[i])) {
                bits.set(i - begin);
            }
        }
        if (bits.isEmpty()) {
            return copyOf(values, begin, length);
        }
        if (bits.cardinality() == length) {
            return new double[0];
        }
        double[] temp = new double[length - bits.cardinality()];
        int start = begin;
        int dest = 0;
        int bitSetPtr = 0;
        while (true) {
            int nextOne = bits.nextSetBit(bitSetPtr);
            if (nextOne == -1) {
                break;
            }
            int lengthToCopy = nextOne - bitSetPtr;
            System.arraycopy(values, start, temp, dest, lengthToCopy);
            dest += lengthToCopy;
            int nextClearBit = bits.nextClearBit(nextOne);
            bitSetPtr = nextClearBit;
            start = begin + nextClearBit;
        }
        if (start < begin + length) {
            System.arraycopy(values, start, temp, dest, (begin + length) - start);
            return temp;
        }
        return temp;
    }

    private int[] getPivots(double[] values) {
        if (values == getDataRef()) {
            return this.cachedPivots;
        }
        int[] pivotsHeap = new int[512];
        Arrays.fill(pivotsHeap, -1);
        return pivotsHeap;
    }

    public EstimationType getEstimationType() {
        return this.estimationType;
    }

    public Percentile withEstimationType(EstimationType newEstimationType) {
        return new Percentile(this.quantile, newEstimationType, this.nanStrategy, this.kthSelector);
    }

    public NaNStrategy getNaNStrategy() {
        return this.nanStrategy;
    }

    public Percentile withNaNStrategy(NaNStrategy newNaNStrategy) {
        return new Percentile(this.quantile, this.estimationType, newNaNStrategy, this.kthSelector);
    }

    public KthSelector getKthSelector() {
        return this.kthSelector;
    }

    public PivotingStrategyInterface getPivotingStrategy() {
        return this.kthSelector.getPivotingStrategy();
    }

    public Percentile withKthSelector(KthSelector newKthSelector) {
        return new Percentile(this.quantile, this.estimationType, this.nanStrategy, newKthSelector);
    }

    /* loaded from: classes10.dex */
    public enum EstimationType {
        LEGACY("Legacy Apache Commons Math") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.1
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                if (Double.compare(p, 0.0d) == 0) {
                    return 0.0d;
                }
                return Double.compare(p, 1.0d) == 0 ? length : (length + 1) * p;
            }
        },
        R_1("R-1") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.2
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                if (Double.compare(p, 0.0d) == 0) {
                    return 0.0d;
                }
                return (length * p) + 0.5d;
            }

            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double estimate(double[] values, int[] pivotsHeap, double pos, int length, KthSelector selector) {
                return super.estimate(values, pivotsHeap, FastMath.ceil(pos - 0.5d), length, selector);
            }
        },
        R_2("R-2") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.3
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                if (Double.compare(p, 1.0d) == 0) {
                    return length;
                }
                if (Double.compare(p, 0.0d) == 0) {
                    return 0.0d;
                }
                return (length * p) + 0.5d;
            }

            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double estimate(double[] values, int[] pivotsHeap, double pos, int length, KthSelector selector) {
                double low = super.estimate(values, pivotsHeap, FastMath.ceil(pos - 0.5d), length, selector);
                double high = super.estimate(values, pivotsHeap, FastMath.floor(0.5d + pos), length, selector);
                return (low + high) / 2.0d;
            }
        },
        R_3("R-3") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.4
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                double minLimit = 0.5d / length;
                if (Double.compare(p, minLimit) <= 0) {
                    return 0.0d;
                }
                return FastMath.rint(length * p);
            }
        },
        R_4("R-4") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.5
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                double minLimit = 1.0d / length;
                if (Double.compare(p, minLimit) < 0) {
                    return 0.0d;
                }
                return Double.compare(p, 1.0d) == 0 ? length : length * p;
            }
        },
        R_5("R-5") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.6
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                double minLimit = 0.5d / length;
                double maxLimit = (length - 0.5d) / length;
                if (Double.compare(p, minLimit) < 0) {
                    return 0.0d;
                }
                return Double.compare(p, maxLimit) >= 0 ? length : 0.5d + (length * p);
            }
        },
        R_6("R-6") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.7
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                double minLimit = 1.0d / (length + 1);
                double maxLimit = (length * 1.0d) / (length + 1);
                if (Double.compare(p, minLimit) < 0) {
                    return 0.0d;
                }
                return Double.compare(p, maxLimit) >= 0 ? length : (length + 1) * p;
            }
        },
        R_7("R-7") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.8
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                if (Double.compare(p, 0.0d) == 0) {
                    return 0.0d;
                }
                return Double.compare(p, 1.0d) == 0 ? length : 1.0d + ((length - 1) * p);
            }
        },
        R_8("R-8") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.9
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                double minLimit = 0.6666666666666666d / (length + 0.3333333333333333d);
                double maxLimit = (length - 0.3333333333333333d) / (length + 0.3333333333333333d);
                if (Double.compare(p, minLimit) < 0) {
                    return 0.0d;
                }
                return Double.compare(p, maxLimit) >= 0 ? length : 0.3333333333333333d + ((length + 0.3333333333333333d) * p);
            }
        },
        R_9("R-9") { // from class: org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType.10
            @Override // org.apache.commons.math3.stat.descriptive.rank.Percentile.EstimationType
            protected double index(double p, int length) {
                double minLimit = 0.625d / (length + 0.25d);
                double maxLimit = (length - 0.375d) / (length + 0.25d);
                if (Double.compare(p, minLimit) < 0) {
                    return 0.0d;
                }
                return Double.compare(p, maxLimit) >= 0 ? length : ((length + 0.25d) * p) + 0.375d;
            }
        };

        private final String name;

        protected abstract double index(double d, int i);

        EstimationType(String type) {
            this.name = type;
        }

        protected double estimate(double[] work, int[] pivotsHeap, double pos, int length, KthSelector selector) {
            double fpos = FastMath.floor(pos);
            int intPos = (int) fpos;
            double dif = pos - fpos;
            if (pos < 1.0d) {
                return selector.select(work, pivotsHeap, 0);
            }
            if (pos >= length) {
                return selector.select(work, pivotsHeap, length - 1);
            }
            double lower = selector.select(work, pivotsHeap, intPos - 1);
            double upper = selector.select(work, pivotsHeap, intPos);
            return ((upper - lower) * dif) + lower;
        }

        protected double evaluate(double[] work, int[] pivotsHeap, double p, KthSelector selector) {
            MathUtils.checkNotNull(work);
            if (p > 100.0d || p <= 0.0d) {
                throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUNDS_QUANTILE_VALUE, Double.valueOf(p), 0, 100);
            }
            return estimate(work, pivotsHeap, index(p / 100.0d, work.length), work.length, selector);
        }

        public double evaluate(double[] work, double p, KthSelector selector) {
            return evaluate(work, null, p, selector);
        }

        String getName() {
            return this.name;
        }
    }
}
