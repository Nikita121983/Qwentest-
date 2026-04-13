package org.apache.commons.math3.stat.ranking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

/* loaded from: classes10.dex */
public class NaturalRanking implements RankingAlgorithm {
    public static final NaNStrategy DEFAULT_NAN_STRATEGY = NaNStrategy.FAILED;
    public static final TiesStrategy DEFAULT_TIES_STRATEGY = TiesStrategy.AVERAGE;
    private final NaNStrategy nanStrategy;
    private final RandomDataGenerator randomData;
    private final TiesStrategy tiesStrategy;

    public NaturalRanking() {
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(TiesStrategy tiesStrategy) {
        this.tiesStrategy = tiesStrategy;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataGenerator();
    }

    public NaturalRanking(NaNStrategy nanStrategy) {
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = DEFAULT_TIES_STRATEGY;
        this.randomData = null;
    }

    public NaturalRanking(NaNStrategy nanStrategy, TiesStrategy tiesStrategy) {
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = tiesStrategy;
        this.randomData = new RandomDataGenerator();
    }

    public NaturalRanking(RandomGenerator randomGenerator) {
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.nanStrategy = DEFAULT_NAN_STRATEGY;
        this.randomData = new RandomDataGenerator(randomGenerator);
    }

    public NaturalRanking(NaNStrategy nanStrategy, RandomGenerator randomGenerator) {
        this.nanStrategy = nanStrategy;
        this.tiesStrategy = TiesStrategy.RANDOM;
        this.randomData = new RandomDataGenerator(randomGenerator);
    }

    public NaNStrategy getNanStrategy() {
        return this.nanStrategy;
    }

    public TiesStrategy getTiesStrategy() {
        return this.tiesStrategy;
    }

    @Override // org.apache.commons.math3.stat.ranking.RankingAlgorithm
    public double[] rank(double[] data) {
        IntDoublePair[] ranks = new IntDoublePair[data.length];
        for (int i = 0; i < data.length; i++) {
            ranks[i] = new IntDoublePair(data[i], i);
        }
        List<Integer> nanPositions = null;
        switch (this.nanStrategy) {
            case MAXIMAL:
                recodeNaNs(ranks, Double.POSITIVE_INFINITY);
                break;
            case MINIMAL:
                recodeNaNs(ranks, Double.NEGATIVE_INFINITY);
                break;
            case REMOVED:
                ranks = removeNaNs(ranks);
                break;
            case FIXED:
                nanPositions = getNanPositions(ranks);
                break;
            case FAILED:
                nanPositions = getNanPositions(ranks);
                if (nanPositions.size() > 0) {
                    throw new NotANumberException();
                }
                break;
            default:
                throw new MathInternalError();
        }
        Arrays.sort(ranks);
        double[] out = new double[ranks.length];
        int pos = 1;
        out[ranks[0].getPosition()] = 1;
        List<Integer> tiesTrace = new ArrayList<>();
        tiesTrace.add(Integer.valueOf(ranks[0].getPosition()));
        for (int i2 = 1; i2 < ranks.length; i2++) {
            if (Double.compare(ranks[i2].getValue(), ranks[i2 - 1].getValue()) > 0) {
                pos = i2 + 1;
                if (tiesTrace.size() > 1) {
                    resolveTie(out, tiesTrace);
                }
                List<Integer> tiesTrace2 = new ArrayList<>();
                tiesTrace2.add(Integer.valueOf(ranks[i2].getPosition()));
                tiesTrace = tiesTrace2;
            } else {
                tiesTrace.add(Integer.valueOf(ranks[i2].getPosition()));
            }
            out[ranks[i2].getPosition()] = pos;
        }
        int i3 = tiesTrace.size();
        if (i3 > 1) {
            resolveTie(out, tiesTrace);
        }
        if (this.nanStrategy == NaNStrategy.FIXED) {
            restoreNaNs(out, nanPositions);
        }
        return out;
    }

    private IntDoublePair[] removeNaNs(IntDoublePair[] ranks) {
        if (!containsNaNs(ranks)) {
            return ranks;
        }
        IntDoublePair[] outRanks = new IntDoublePair[ranks.length];
        int j = 0;
        for (int i = 0; i < ranks.length; i++) {
            if (Double.isNaN(ranks[i].getValue())) {
                for (int k = i + 1; k < ranks.length; k++) {
                    ranks[k] = new IntDoublePair(ranks[k].getValue(), ranks[k].getPosition() - 1);
                }
            } else {
                outRanks[j] = new IntDoublePair(ranks[i].getValue(), ranks[i].getPosition());
                j++;
            }
        }
        IntDoublePair[] returnRanks = new IntDoublePair[j];
        System.arraycopy(outRanks, 0, returnRanks, 0, j);
        return returnRanks;
    }

    private void recodeNaNs(IntDoublePair[] ranks, double value) {
        for (int i = 0; i < ranks.length; i++) {
            if (Double.isNaN(ranks[i].getValue())) {
                ranks[i] = new IntDoublePair(value, ranks[i].getPosition());
            }
        }
    }

    private boolean containsNaNs(IntDoublePair[] ranks) {
        for (IntDoublePair intDoublePair : ranks) {
            if (Double.isNaN(intDoublePair.getValue())) {
                return true;
            }
        }
        return false;
    }

    private void resolveTie(double[] ranks, List<Integer> tiesTrace) {
        double c = ranks[tiesTrace.get(0).intValue()];
        int length = tiesTrace.size();
        switch (this.tiesStrategy) {
            case AVERAGE:
                fill(ranks, tiesTrace, (((c * 2.0d) + length) - 1.0d) / 2.0d);
                return;
            case MAXIMUM:
                fill(ranks, tiesTrace, (length + c) - 1.0d);
                return;
            case MINIMUM:
                fill(ranks, tiesTrace, c);
                return;
            case RANDOM:
                Iterator<Integer> iterator = tiesTrace.iterator();
                long f = FastMath.round(c);
                while (iterator.hasNext()) {
                    ranks[iterator.next().intValue()] = this.randomData.nextLong(f, (length + f) - 1);
                }
                return;
            case SEQUENTIAL:
                Iterator<Integer> iterator2 = tiesTrace.iterator();
                long f2 = FastMath.round(c);
                int i = 0;
                while (iterator2.hasNext()) {
                    ranks[iterator2.next().intValue()] = i + f2;
                    i++;
                }
                return;
            default:
                throw new MathInternalError();
        }
    }

    private void fill(double[] data, List<Integer> tiesTrace, double value) {
        Iterator<Integer> iterator = tiesTrace.iterator();
        while (iterator.hasNext()) {
            data[iterator.next().intValue()] = value;
        }
    }

    private void restoreNaNs(double[] ranks, List<Integer> nanPositions) {
        if (nanPositions.size() == 0) {
            return;
        }
        Iterator<Integer> iterator = nanPositions.iterator();
        while (iterator.hasNext()) {
            ranks[iterator.next().intValue()] = Double.NaN;
        }
    }

    private List<Integer> getNanPositions(IntDoublePair[] ranks) {
        ArrayList<Integer> out = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            if (Double.isNaN(ranks[i].getValue())) {
                out.add(Integer.valueOf(i));
            }
        }
        return out;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class IntDoublePair implements Comparable<IntDoublePair> {
        private final int position;
        private final double value;

        IntDoublePair(double value, int position) {
            this.value = value;
            this.position = position;
        }

        @Override // java.lang.Comparable
        public int compareTo(IntDoublePair other) {
            return Double.compare(this.value, other.value);
        }

        public double getValue() {
            return this.value;
        }

        public int getPosition() {
            return this.position;
        }
    }
}
