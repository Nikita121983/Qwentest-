package org.apache.commons.math3.ml.clustering;

import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.ml.clustering.Clusterable;
import org.apache.commons.math3.ml.distance.DistanceMeasure;

/* loaded from: classes10.dex */
public abstract class Clusterer<T extends Clusterable> {
    private DistanceMeasure measure;

    public abstract List<? extends Cluster<T>> cluster(Collection<T> collection) throws MathIllegalArgumentException, ConvergenceException;

    /* JADX INFO: Access modifiers changed from: protected */
    public Clusterer(DistanceMeasure measure) {
        this.measure = measure;
    }

    public DistanceMeasure getDistanceMeasure() {
        return this.measure;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public double distance(Clusterable p1, Clusterable p2) {
        return this.measure.compute(p1.getPoint(), p2.getPoint());
    }
}
