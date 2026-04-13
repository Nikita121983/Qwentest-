package org.apache.commons.math3.ml.neuralnet;

import androidx.lifecycle.AtomicReference$$ExternalSyntheticBackportWithForwarding0;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class Neuron implements Serializable {
    private static final long serialVersionUID = 20130207;
    private final AtomicReference<double[]> features;
    private final long identifier;
    private final AtomicLong numberOfAttemptedUpdates = new AtomicLong(0);
    private final AtomicLong numberOfSuccessfulUpdates = new AtomicLong(0);
    private final int size;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Neuron(long identifier, double[] features) {
        this.identifier = identifier;
        this.size = features.length;
        this.features = new AtomicReference<>(features.clone());
    }

    public synchronized Neuron copy() {
        Neuron copy;
        copy = new Neuron(getIdentifier(), getFeatures());
        copy.numberOfAttemptedUpdates.set(this.numberOfAttemptedUpdates.get());
        copy.numberOfSuccessfulUpdates.set(this.numberOfSuccessfulUpdates.get());
        return copy;
    }

    public long getIdentifier() {
        return this.identifier;
    }

    public int getSize() {
        return this.size;
    }

    public double[] getFeatures() {
        return (double[]) this.features.get().clone();
    }

    public boolean compareAndSetFeatures(double[] expect, double[] update) {
        if (update.length != this.size) {
            throw new DimensionMismatchException(update.length, this.size);
        }
        double[] current = this.features.get();
        if (!containSameValues(current, expect)) {
            return false;
        }
        this.numberOfAttemptedUpdates.incrementAndGet();
        if (!AtomicReference$$ExternalSyntheticBackportWithForwarding0.m(this.features, current, update.clone())) {
            return false;
        }
        this.numberOfSuccessfulUpdates.incrementAndGet();
        return true;
    }

    public long getNumberOfAttemptedUpdates() {
        return this.numberOfAttemptedUpdates.get();
    }

    public long getNumberOfSuccessfulUpdates() {
        return this.numberOfSuccessfulUpdates.get();
    }

    private boolean containSameValues(double[] current, double[] expect) {
        if (expect.length != this.size) {
            throw new DimensionMismatchException(expect.length, this.size);
        }
        for (int i = 0; i < this.size; i++) {
            if (!Precision.equals(current[i], expect[i])) {
                return false;
            }
        }
        return true;
    }

    private void readObject(ObjectInputStream in) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        return new SerializationProxy(this.identifier, this.features.get());
    }

    /* loaded from: classes10.dex */
    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 20130207;
        private final double[] features;
        private final long identifier;

        SerializationProxy(long identifier, double[] features) {
            this.identifier = identifier;
            this.features = features;
        }

        private Object readResolve() {
            return new Neuron(this.identifier, this.features);
        }
    }
}
