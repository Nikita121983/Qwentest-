package org.apache.commons.math3.stat.descriptive.rank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.NevilleInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic;
import org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class PSquarePercentile extends AbstractStorelessUnivariateStatistic implements StorelessUnivariateStatistic, Serializable {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("00.00");
    private static final double DEFAULT_QUANTILE_DESIRED = 50.0d;
    private static final int PSQUARE_CONSTANT = 5;
    private static final long serialVersionUID = 2283912083175715479L;
    private long countOfObservations;
    private final List<Double> initialFive;
    private transient double lastObservation;
    private PSquareMarkers markers;
    private double pValue;
    private final double quantile;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public interface PSquareMarkers extends Cloneable {
        Object clone();

        double estimate(int i);

        double getPercentileValue();

        double height(int i);

        double processDataPoint(double d);
    }

    public PSquarePercentile(double p) {
        this.initialFive = new FixedCapacityList(5);
        this.markers = null;
        this.pValue = Double.NaN;
        if (p > 100.0d || p < 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE, Double.valueOf(p), 0, 100);
        }
        this.quantile = p / 100.0d;
    }

    PSquarePercentile() {
        this(DEFAULT_QUANTILE_DESIRED);
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic
    public int hashCode() {
        double result = getResult();
        double result2 = Double.isNaN(result) ? 37.0d : result;
        double markersHash = this.markers == null ? 0.0d : this.markers.hashCode();
        double[] toHash = {result2, this.quantile, markersHash, this.countOfObservations};
        return Arrays.hashCode(toHash);
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof PSquarePercentile)) {
            return false;
        }
        PSquarePercentile that = (PSquarePercentile) o;
        boolean isNotNull = (this.markers == null || that.markers == null) ? false : true;
        boolean isNull = this.markers == null && that.markers == null;
        boolean result = isNotNull ? this.markers.equals(that.markers) : isNull;
        boolean result2 = result && getN() == that.getN();
        return result2;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void increment(double observation) {
        this.countOfObservations++;
        this.lastObservation = observation;
        if (this.markers == null) {
            if (this.initialFive.add(Double.valueOf(observation))) {
                Collections.sort(this.initialFive);
                this.pValue = this.initialFive.get((int) (this.quantile * (this.initialFive.size() - 1))).doubleValue();
                return;
            }
            this.markers = newMarkers(this.initialFive, this.quantile);
        }
        this.pValue = this.markers.processDataPoint(observation);
    }

    public String toString() {
        if (this.markers == null) {
            return String.format("obs=%s pValue=%s", DECIMAL_FORMAT.format(this.lastObservation), DECIMAL_FORMAT.format(this.pValue));
        }
        return String.format("obs=%s markers=%s", DECIMAL_FORMAT.format(this.lastObservation), this.markers.toString());
    }

    @Override // org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public long getN() {
        return this.countOfObservations;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.AbstractUnivariateStatistic, org.apache.commons.math3.stat.descriptive.UnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public StorelessUnivariateStatistic copy() {
        PSquarePercentile copy = new PSquarePercentile(this.quantile * 100.0d);
        if (this.markers != null) {
            copy.markers = (PSquareMarkers) this.markers.clone();
        }
        copy.countOfObservations = this.countOfObservations;
        copy.pValue = this.pValue;
        copy.initialFive.clear();
        copy.initialFive.addAll(this.initialFive);
        return copy;
    }

    public double quantile() {
        return this.quantile;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public void clear() {
        this.markers = null;
        this.initialFive.clear();
        this.countOfObservations = 0L;
        this.pValue = Double.NaN;
    }

    @Override // org.apache.commons.math3.stat.descriptive.AbstractStorelessUnivariateStatistic, org.apache.commons.math3.stat.descriptive.StorelessUnivariateStatistic
    public double getResult() {
        if (Double.compare(this.quantile, 1.0d) == 0) {
            this.pValue = maximum();
        } else if (Double.compare(this.quantile, 0.0d) == 0) {
            this.pValue = minimum();
        }
        return this.pValue;
    }

    private double maximum() {
        if (this.markers != null) {
            double val = this.markers.height(5);
            return val;
        }
        if (this.initialFive.isEmpty()) {
            return Double.NaN;
        }
        double val2 = this.initialFive.get(this.initialFive.size() - 1).doubleValue();
        return val2;
    }

    private double minimum() {
        if (this.markers != null) {
            double val = this.markers.height(1);
            return val;
        }
        if (this.initialFive.isEmpty()) {
            return Double.NaN;
        }
        double val2 = this.initialFive.get(0).doubleValue();
        return val2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Markers implements PSquareMarkers, Serializable {
        private static final int HIGH = 4;
        private static final int LOW = 2;
        private static final long serialVersionUID = 1;
        private transient int k;
        private final Marker[] markerArray;

        private Markers(Marker[] theMarkerArray) {
            this.k = -1;
            MathUtils.checkNotNull(theMarkerArray);
            this.markerArray = theMarkerArray;
            for (int i = 1; i < 5; i++) {
                this.markerArray[i].previous(this.markerArray[i - 1]).next(this.markerArray[i + 1]).index(i);
            }
            this.markerArray[0].previous(this.markerArray[0]).next(this.markerArray[1]).index(0);
            this.markerArray[5].previous(this.markerArray[4]).next(this.markerArray[5]).index(5);
        }

        private Markers(List<Double> initialFive, double p) {
            this(createMarkerArray(initialFive, p));
        }

        private static Marker[] createMarkerArray(List<Double> initialFive, double p) {
            int countObserved = initialFive == null ? -1 : initialFive.size();
            if (countObserved < 5) {
                throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(countObserved), 5);
            }
            Collections.sort(initialFive);
            return new Marker[]{new Marker(), new Marker(initialFive.get(0).doubleValue(), 1.0d, 0.0d, 1.0d), new Marker(initialFive.get(1).doubleValue(), (p * 2.0d) + 1.0d, p / 2.0d, 2.0d), new Marker(initialFive.get(2).doubleValue(), (4.0d * p) + 1.0d, p, 3.0d), new Marker(initialFive.get(3).doubleValue(), 3.0d + (p * 2.0d), (p + 1.0d) / 2.0d, 4.0d), new Marker(initialFive.get(4).doubleValue(), 5.0d, 1.0d, 5.0d)};
        }

        public int hashCode() {
            return Arrays.deepHashCode(this.markerArray);
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof Markers)) {
                return false;
            }
            Markers that = (Markers) o;
            boolean result = Arrays.deepEquals(this.markerArray, that.markerArray);
            return result;
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double processDataPoint(double inputDataPoint) {
            int kthCell = findCellAndUpdateMinMax(inputDataPoint);
            incrementPositions(1, kthCell + 1, 5);
            updateDesiredPositions();
            adjustHeightsOfMarkers();
            return getPercentileValue();
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double getPercentileValue() {
            return height(3);
        }

        private int findCellAndUpdateMinMax(double observation) {
            this.k = -1;
            if (observation < height(1)) {
                this.markerArray[1].markerHeight = observation;
                this.k = 1;
            } else if (observation < height(2)) {
                this.k = 1;
            } else if (observation < height(3)) {
                this.k = 2;
            } else if (observation < height(4)) {
                this.k = 3;
            } else if (observation <= height(5)) {
                this.k = 4;
            } else {
                this.markerArray[5].markerHeight = observation;
                this.k = 4;
            }
            return this.k;
        }

        private void adjustHeightsOfMarkers() {
            for (int i = 2; i <= 4; i++) {
                estimate(i);
            }
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double estimate(int index) {
            if (index < 2 || index > 4) {
                throw new OutOfRangeException(Integer.valueOf(index), 2, 4);
            }
            return this.markerArray[index].estimate();
        }

        private void incrementPositions(int d, int startIndex, int endIndex) {
            for (int i = startIndex; i <= endIndex; i++) {
                this.markerArray[i].incrementPosition(d);
            }
        }

        private void updateDesiredPositions() {
            for (int i = 1; i < this.markerArray.length; i++) {
                this.markerArray[i].updateDesiredPosition();
            }
        }

        private void readObject(ObjectInputStream anInputStream) throws ClassNotFoundException, IOException {
            anInputStream.defaultReadObject();
            for (int i = 1; i < 5; i++) {
                this.markerArray[i].previous(this.markerArray[i - 1]).next(this.markerArray[i + 1]).index(i);
            }
            this.markerArray[0].previous(this.markerArray[0]).next(this.markerArray[1]).index(0);
            this.markerArray[5].previous(this.markerArray[4]).next(this.markerArray[5]).index(5);
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public double height(int markerIndex) {
            if (markerIndex >= this.markerArray.length || markerIndex <= 0) {
                throw new OutOfRangeException(Integer.valueOf(markerIndex), 1, Integer.valueOf(this.markerArray.length));
            }
            return this.markerArray[markerIndex].markerHeight;
        }

        @Override // org.apache.commons.math3.stat.descriptive.rank.PSquarePercentile.PSquareMarkers
        public Object clone() {
            return new Markers(new Marker[]{new Marker(), (Marker) this.markerArray[1].clone(), (Marker) this.markerArray[2].clone(), (Marker) this.markerArray[3].clone(), (Marker) this.markerArray[4].clone(), (Marker) this.markerArray[5].clone()});
        }

        public String toString() {
            return String.format("m1=[%s],m2=[%s],m3=[%s],m4=[%s],m5=[%s]", this.markerArray[1].toString(), this.markerArray[2].toString(), this.markerArray[3].toString(), this.markerArray[4].toString(), this.markerArray[5].toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class Marker implements Serializable, Cloneable {
        private static final long serialVersionUID = -3575879478288538431L;
        private double desiredMarkerIncrement;
        private double desiredMarkerPosition;
        private int index;
        private double intMarkerPosition;
        private transient UnivariateInterpolator linear;
        private double markerHeight;
        private transient Marker next;
        private final UnivariateInterpolator nonLinear;
        private transient Marker previous;

        private Marker() {
            this.nonLinear = new NevilleInterpolator();
            this.linear = new LinearInterpolator();
            this.previous = this;
            this.next = this;
        }

        private Marker(double heightOfMarker, double makerPositionDesired, double markerPositionIncrement, double markerPositionNumber) {
            this();
            this.markerHeight = heightOfMarker;
            this.desiredMarkerPosition = makerPositionDesired;
            this.desiredMarkerIncrement = markerPositionIncrement;
            this.intMarkerPosition = markerPositionNumber;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Marker previous(Marker previousMarker) {
            MathUtils.checkNotNull(previousMarker);
            this.previous = previousMarker;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Marker next(Marker nextMarker) {
            MathUtils.checkNotNull(nextMarker);
            this.next = nextMarker;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Marker index(int indexOfMarker) {
            this.index = indexOfMarker;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateDesiredPosition() {
            this.desiredMarkerPosition += this.desiredMarkerIncrement;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void incrementPosition(int d) {
            this.intMarkerPosition += d;
        }

        private double difference() {
            return this.desiredMarkerPosition - this.intMarkerPosition;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public double estimate() {
            double di = difference();
            boolean isNextHigher = this.next.intMarkerPosition - this.intMarkerPosition > 1.0d;
            boolean isPreviousLower = this.previous.intMarkerPosition - this.intMarkerPosition < -1.0d;
            if ((di >= 1.0d && isNextHigher) || (di <= -1.0d && isPreviousLower)) {
                int d = di >= 0.0d ? 1 : -1;
                double[] xval = {this.previous.intMarkerPosition, this.intMarkerPosition, this.next.intMarkerPosition};
                double[] yval = {this.previous.markerHeight, this.markerHeight, this.next.markerHeight};
                double xD = this.intMarkerPosition + d;
                UnivariateFunction univariateFunction = this.nonLinear.interpolate(xval, yval);
                this.markerHeight = univariateFunction.value(xD);
                if (isEstimateBad(yval, this.markerHeight)) {
                    int delta = xD - xval[1] > 0.0d ? 1 : -1;
                    double[] xBad = {xval[1], xval[delta + 1]};
                    double[] yBad = {yval[1], yval[delta + 1]};
                    MathArrays.sortInPlace(xBad, yBad);
                    UnivariateFunction univariateFunction2 = this.linear.interpolate(xBad, yBad);
                    this.markerHeight = univariateFunction2.value(xD);
                }
                incrementPosition(d);
            }
            return this.markerHeight;
        }

        private boolean isEstimateBad(double[] y, double yD) {
            return yD <= y[0] || yD >= y[2];
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof Marker)) {
                return false;
            }
            Marker that = (Marker) o;
            boolean result = Double.compare(this.markerHeight, that.markerHeight) == 0;
            boolean result2 = result && Double.compare(this.intMarkerPosition, that.intMarkerPosition) == 0;
            boolean result3 = result2 && Double.compare(this.desiredMarkerPosition, that.desiredMarkerPosition) == 0;
            boolean result4 = result3 && Double.compare(this.desiredMarkerIncrement, that.desiredMarkerIncrement) == 0;
            boolean result5 = result4 && this.next.index == that.next.index;
            boolean result6 = result5 && this.previous.index == that.previous.index;
            return result6;
        }

        public int hashCode() {
            return Arrays.hashCode(new double[]{this.markerHeight, this.intMarkerPosition, this.desiredMarkerIncrement, this.desiredMarkerPosition, this.previous.index, this.next.index});
        }

        private void readObject(ObjectInputStream anInstream) throws ClassNotFoundException, IOException {
            anInstream.defaultReadObject();
            this.next = this;
            this.previous = this;
            this.linear = new LinearInterpolator();
        }

        public Object clone() {
            return new Marker(this.markerHeight, this.desiredMarkerPosition, this.desiredMarkerIncrement, this.intMarkerPosition);
        }

        public String toString() {
            return String.format("index=%.0f,n=%.0f,np=%.2f,q=%.2f,dn=%.2f,prev=%d,next=%d", Double.valueOf(this.index), Double.valueOf(Precision.round(this.intMarkerPosition, 0)), Double.valueOf(Precision.round(this.desiredMarkerPosition, 2)), Double.valueOf(Precision.round(this.markerHeight, 2)), Double.valueOf(Precision.round(this.desiredMarkerIncrement, 2)), Integer.valueOf(this.previous.index), Integer.valueOf(this.next.index));
        }
    }

    /* loaded from: classes10.dex */
    private static class FixedCapacityList<E> extends ArrayList<E> implements Serializable {
        private static final long serialVersionUID = 2283952083075725479L;
        private final int capacity;

        FixedCapacityList(int fixedCapacity) {
            super(fixedCapacity);
            this.capacity = fixedCapacity;
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(E e) {
            if (size() < this.capacity) {
                return super.add(e);
            }
            return false;
        }

        @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends E> collection) {
            boolean isCollectionLess = collection != null && collection.size() + size() <= this.capacity;
            if (isCollectionLess) {
                return super.addAll(collection);
            }
            return false;
        }
    }

    public static PSquareMarkers newMarkers(List<Double> initialFive, double p) {
        return new Markers(initialFive, p);
    }
}
