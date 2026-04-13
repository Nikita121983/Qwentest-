package org.apache.commons.math3.geometry.spherical.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.Point;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BoundaryProjection;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

/* loaded from: classes10.dex */
public class ArcsSet extends AbstractRegion<Sphere1D, Sphere1D> implements Iterable<double[]> {
    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ AbstractRegion buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Sphere1D>) bSPTree);
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public /* bridge */ /* synthetic */ Region buildNew(BSPTree bSPTree) {
        return buildNew((BSPTree<Sphere1D>) bSPTree);
    }

    public ArcsSet(double tolerance) {
        super(tolerance);
    }

    public ArcsSet(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
        super(buildTree(lower, upper, tolerance), tolerance);
    }

    public ArcsSet(BSPTree<Sphere1D> tree, double tolerance) throws InconsistentStateAt2PiWrapping {
        super(tree, tolerance);
        check2PiConsistency();
    }

    public ArcsSet(Collection<SubHyperplane<Sphere1D>> boundary, double tolerance) throws InconsistentStateAt2PiWrapping {
        super(boundary, tolerance);
        check2PiConsistency();
    }

    private static BSPTree<Sphere1D> buildTree(double lower, double upper, double tolerance) throws NumberIsTooLargeException {
        if (Precision.equals(lower, upper, 0) || upper - lower >= 6.283185307179586d) {
            return new BSPTree<>(Boolean.TRUE);
        }
        if (lower <= upper) {
            double normalizedLower = MathUtils.normalizeAngle(lower, 3.141592653589793d);
            double normalizedUpper = (upper - lower) + normalizedLower;
            SubHyperplane<Sphere1D> lowerCut = new LimitAngle(new S1Point(normalizedLower), false, tolerance).wholeHyperplane();
            if (normalizedUpper <= 6.283185307179586d) {
                SubHyperplane<Sphere1D> upperCut = new LimitAngle(new S1Point(normalizedUpper), true, tolerance).wholeHyperplane();
                return new BSPTree<>(lowerCut, new BSPTree(Boolean.FALSE), new BSPTree(upperCut, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
            }
            SubHyperplane<Sphere1D> upperCut2 = new LimitAngle(new S1Point(normalizedUpper - 6.283185307179586d), true, tolerance).wholeHyperplane();
            return new BSPTree<>(lowerCut, new BSPTree(upperCut2, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), new BSPTree(Boolean.TRUE), null);
        }
        throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(lower), Double.valueOf(upper), true);
    }

    private void check2PiConsistency() throws InconsistentStateAt2PiWrapping {
        BSPTree<Sphere1D> root = getTree(false);
        if (root.getCut() == null) {
            return;
        }
        Boolean stateBefore = (Boolean) getFirstLeaf(root).getAttribute();
        Boolean stateAfter = (Boolean) getLastLeaf(root).getAttribute();
        if (stateBefore.booleanValue() ^ stateAfter.booleanValue()) {
            throw new InconsistentStateAt2PiWrapping();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> getFirstLeaf(BSPTree<Sphere1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        BSPTree<Sphere1D> smallest = null;
        BSPTree<Sphere1D> n = root;
        while (n != null) {
            smallest = n;
            n = previousInternalNode(n);
        }
        BSPTree<Sphere1D> n2 = leafBefore(smallest);
        return n2;
    }

    private BSPTree<Sphere1D> getLastLeaf(BSPTree<Sphere1D> root) {
        if (root.getCut() == null) {
            return root;
        }
        BSPTree<Sphere1D> largest = null;
        BSPTree<Sphere1D> n = root;
        while (n != null) {
            largest = n;
            n = nextInternalNode(n);
        }
        BSPTree<Sphere1D> n2 = leafAfter(largest);
        return n2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> getFirstArcStart() {
        BSPTree<Sphere1D> node = getTree(false);
        if (node.getCut() == null) {
            return null;
        }
        BSPTree<Sphere1D> node2 = getFirstLeaf(node).getParent();
        while (node2 != null && !isArcStart(node2)) {
            node2 = nextInternalNode(node2);
        }
        return node2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isArcStart(BSPTree<Sphere1D> node) {
        return !((Boolean) leafBefore(node).getAttribute()).booleanValue() && ((Boolean) leafAfter(node).getAttribute()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isArcEnd(BSPTree<Sphere1D> node) {
        return ((Boolean) leafBefore(node).getAttribute()).booleanValue() && !((Boolean) leafAfter(node).getAttribute()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> nextInternalNode(BSPTree<Sphere1D> node) {
        if (childAfter(node).getCut() != null) {
            return leafAfter(node).getParent();
        }
        while (isAfterParent(node)) {
            node = node.getParent();
        }
        return node.getParent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BSPTree<Sphere1D> previousInternalNode(BSPTree<Sphere1D> node) {
        if (childBefore(node).getCut() != null) {
            return leafBefore(node).getParent();
        }
        while (isBeforeParent(node)) {
            node = node.getParent();
        }
        return node.getParent();
    }

    private BSPTree<Sphere1D> leafBefore(BSPTree<Sphere1D> node) {
        BSPTree<Sphere1D> node2 = childBefore(node);
        while (node2.getCut() != null) {
            node2 = childAfter(node2);
        }
        return node2;
    }

    private BSPTree<Sphere1D> leafAfter(BSPTree<Sphere1D> node) {
        BSPTree<Sphere1D> node2 = childAfter(node);
        while (node2.getCut() != null) {
            node2 = childBefore(node2);
        }
        return node2;
    }

    private boolean isBeforeParent(BSPTree<Sphere1D> node) {
        BSPTree<Sphere1D> parent = node.getParent();
        return parent != null && node == childBefore(parent);
    }

    private boolean isAfterParent(BSPTree<Sphere1D> node) {
        BSPTree<Sphere1D> parent = node.getParent();
        return parent != null && node == childAfter(parent);
    }

    private BSPTree<Sphere1D> childBefore(BSPTree<Sphere1D> node) {
        if (isDirect(node)) {
            return node.getMinus();
        }
        return node.getPlus();
    }

    private BSPTree<Sphere1D> childAfter(BSPTree<Sphere1D> node) {
        if (isDirect(node)) {
            return node.getPlus();
        }
        return node.getMinus();
    }

    private boolean isDirect(BSPTree<Sphere1D> node) {
        return ((LimitAngle) node.getCut().getHyperplane()).isDirect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public double getAngle(BSPTree<Sphere1D> node) {
        return ((LimitAngle) node.getCut().getHyperplane()).getLocation().getAlpha();
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public ArcsSet buildNew(BSPTree<Sphere1D> tree) {
        return new ArcsSet(tree, getTolerance());
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion
    protected void computeGeometricalProperties() {
        if (getTree(false).getCut() == null) {
            setBarycenter(S1Point.NaN);
            setSize(((Boolean) getTree(false).getAttribute()).booleanValue() ? 6.283185307179586d : 0.0d);
            return;
        }
        double size = 0.0d;
        double sum = 0.0d;
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            double[] a = i$.next();
            double length = a[1] - a[0];
            size += length;
            sum += (a[0] + a[1]) * length;
        }
        setSize(size);
        if (Precision.equals(size, 6.283185307179586d, 0)) {
            setBarycenter(S1Point.NaN);
        } else if (size >= Precision.SAFE_MIN) {
            setBarycenter(new S1Point(sum / (2.0d * size)));
        } else {
            LimitAngle limit = (LimitAngle) getTree(false).getCut().getHyperplane();
            setBarycenter(limit.getLocation());
        }
    }

    @Override // org.apache.commons.math3.geometry.partitioning.AbstractRegion, org.apache.commons.math3.geometry.partitioning.Region
    public BoundaryProjection<Sphere1D> projectToBoundary(Point<Sphere1D> point) {
        double alpha;
        boolean wrapFirst;
        double first;
        double alpha2 = ((S1Point) point).getAlpha();
        boolean wrapFirst2 = false;
        double first2 = Double.NaN;
        double previous = Double.NaN;
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            double[] a = i$.next();
            if (Double.isNaN(first2)) {
                first2 = a[0];
            }
            if (wrapFirst2) {
                alpha = alpha2;
                wrapFirst = wrapFirst2;
                first = first2;
            } else if (alpha2 < a[0]) {
                if (Double.isNaN(previous)) {
                    wrapFirst2 = true;
                    alpha = alpha2;
                    first = first2;
                    previous = a[1];
                    alpha2 = alpha;
                    first2 = first;
                } else {
                    double previousOffset = alpha2 - previous;
                    double currentOffset = a[0] - alpha2;
                    if (previousOffset < currentOffset) {
                        return new BoundaryProjection<>(point, new S1Point(previous), previousOffset);
                    }
                    double alpha3 = a[0];
                    return new BoundaryProjection<>(point, new S1Point(alpha3), currentOffset);
                }
            } else {
                alpha = alpha2;
                double alpha4 = a[1];
                if (alpha > alpha4) {
                    wrapFirst = wrapFirst2;
                    first = first2;
                } else {
                    double offset0 = a[0] - alpha;
                    double offset1 = alpha - a[1];
                    return offset0 < offset1 ? new BoundaryProjection<>(point, new S1Point(a[1]), offset1) : new BoundaryProjection<>(point, new S1Point(a[0]), offset0);
                }
            }
            wrapFirst2 = wrapFirst;
            previous = a[1];
            alpha2 = alpha;
            first2 = first;
        }
        double alpha5 = alpha2;
        boolean wrapFirst3 = wrapFirst2;
        if (Double.isNaN(previous)) {
            return new BoundaryProjection<>(point, null, 6.283185307179586d);
        }
        if (wrapFirst3) {
            double previousOffset2 = alpha5 - (previous - 6.283185307179586d);
            double currentOffset2 = first2 - alpha5;
            return previousOffset2 < currentOffset2 ? new BoundaryProjection<>(point, new S1Point(previous), previousOffset2) : new BoundaryProjection<>(point, new S1Point(first2), currentOffset2);
        }
        double currentOffset3 = alpha5 - previous;
        double currentOffset4 = (6.283185307179586d + first2) - alpha5;
        return currentOffset3 < currentOffset4 ? new BoundaryProjection<>(point, new S1Point(previous), currentOffset3) : new BoundaryProjection<>(point, new S1Point(first2), currentOffset4);
    }

    public List<Arc> asList() {
        List<Arc> list = new ArrayList<>();
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            double[] a = i$.next();
            list.add(new Arc(a[0], a[1], getTolerance()));
        }
        return list;
    }

    @Override // java.lang.Iterable
    public Iterator<double[]> iterator() {
        return new SubArcsIterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public class SubArcsIterator implements Iterator<double[]> {
        private BSPTree<Sphere1D> current;
        private final BSPTree<Sphere1D> firstStart;
        private double[] pending;

        SubArcsIterator() {
            this.firstStart = ArcsSet.this.getFirstArcStart();
            this.current = this.firstStart;
            if (this.firstStart == null) {
                if (((Boolean) ArcsSet.this.getFirstLeaf(ArcsSet.this.getTree(false)).getAttribute()).booleanValue()) {
                    this.pending = new double[]{0.0d, 6.283185307179586d};
                    return;
                } else {
                    this.pending = null;
                    return;
                }
            }
            selectPending();
        }

        private void selectPending() {
            BSPTree<Sphere1D> start = this.current;
            while (start != null && !ArcsSet.this.isArcStart(start)) {
                start = ArcsSet.this.nextInternalNode(start);
            }
            if (start == null) {
                this.current = null;
                this.pending = null;
                return;
            }
            BSPTree<Sphere1D> end = start;
            while (end != null && !ArcsSet.this.isArcEnd(end)) {
                end = ArcsSet.this.nextInternalNode(end);
            }
            if (end != null) {
                this.pending = new double[]{ArcsSet.this.getAngle(start), ArcsSet.this.getAngle(end)};
                this.current = end;
                return;
            }
            BSPTree<Sphere1D> end2 = this.firstStart;
            while (end2 != null && !ArcsSet.this.isArcEnd(end2)) {
                end2 = ArcsSet.this.previousInternalNode(end2);
            }
            if (end2 != null) {
                this.pending = new double[]{ArcsSet.this.getAngle(start), ArcsSet.this.getAngle(end2) + 6.283185307179586d};
                this.current = null;
                return;
            }
            throw new MathInternalError();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pending != null;
        }

        @Override // java.util.Iterator
        public double[] next() {
            if (this.pending == null) {
                throw new NoSuchElementException();
            }
            double[] next = this.pending;
            selectPending();
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Deprecated
    public Side side(Arc arc) {
        return split(arc).getSide();
    }

    public Split split(Arc arc) {
        List<Double> minus = new ArrayList<>();
        List<Double> plus = new ArrayList<>();
        double reference = arc.getInf() + 3.141592653589793d;
        double arcLength = arc.getSup() - arc.getInf();
        Iterator i$ = iterator();
        while (i$.hasNext()) {
            double[] a = i$.next();
            double syncedStart = MathUtils.normalizeAngle(a[0], reference) - arc.getInf();
            double arcOffset = a[0] - syncedStart;
            double syncedEnd = a[1] - arcOffset;
            if (syncedStart < arcLength) {
                minus.add(Double.valueOf(a[0]));
                if (syncedEnd > arcLength) {
                    double minusToPlus = arcLength + arcOffset;
                    minus.add(Double.valueOf(minusToPlus));
                    plus.add(Double.valueOf(minusToPlus));
                    if (syncedEnd > 6.283185307179586d) {
                        double plusToMinus = arcOffset + 6.283185307179586d;
                        plus.add(Double.valueOf(plusToMinus));
                        minus.add(Double.valueOf(plusToMinus));
                        minus.add(Double.valueOf(a[1]));
                    } else {
                        plus.add(Double.valueOf(a[1]));
                    }
                } else {
                    minus.add(Double.valueOf(a[1]));
                }
            } else {
                plus.add(Double.valueOf(a[0]));
                if (syncedEnd > 6.283185307179586d) {
                    double plusToMinus2 = arcOffset + 6.283185307179586d;
                    plus.add(Double.valueOf(plusToMinus2));
                    minus.add(Double.valueOf(plusToMinus2));
                    if (syncedEnd > arcLength + 6.283185307179586d) {
                        double minusToPlus2 = arcLength + 6.283185307179586d + arcOffset;
                        minus.add(Double.valueOf(minusToPlus2));
                        plus.add(Double.valueOf(minusToPlus2));
                        plus.add(Double.valueOf(a[1]));
                    } else {
                        minus.add(Double.valueOf(a[1]));
                    }
                } else {
                    plus.add(Double.valueOf(a[1]));
                }
            }
        }
        return new Split(createSplitPart(minus));
    }

    private void addArcLimit(BSPTree<Sphere1D> tree, double alpha, boolean isStart) {
        LimitAngle limit = new LimitAngle(new S1Point(alpha), !isStart, getTolerance());
        BSPTree<Sphere1D> node = tree.getCell(limit.getLocation(), getTolerance());
        if (node.getCut() != null) {
            throw new MathInternalError();
        }
        node.insertCut(limit);
        node.setAttribute(null);
        node.getPlus().setAttribute(Boolean.FALSE);
        node.getMinus().setAttribute(Boolean.TRUE);
    }

    private ArcsSet createSplitPart(List<Double> limits) {
        if (limits.isEmpty()) {
            return null;
        }
        int i = 0;
        while (i < limits.size()) {
            int j = (i + 1) % limits.size();
            double lA = limits.get(i).doubleValue();
            double lB = MathUtils.normalizeAngle(limits.get(j).doubleValue(), lA);
            if (FastMath.abs(lB - lA) <= getTolerance()) {
                if (j > 0) {
                    limits.remove(j);
                    limits.remove(i);
                    i--;
                } else {
                    double lEnd = limits.remove(limits.size() - 1).doubleValue();
                    double lStart = limits.remove(0).doubleValue();
                    if (limits.isEmpty()) {
                        if (lEnd - lStart > 3.141592653589793d) {
                            return new ArcsSet((BSPTree<Sphere1D>) new BSPTree(Boolean.TRUE), getTolerance());
                        }
                        return null;
                    }
                    limits.add(Double.valueOf(limits.remove(0).doubleValue() + 6.283185307179586d));
                }
            }
            i++;
        }
        BSPTree<Sphere1D> tree = new BSPTree<>(Boolean.FALSE);
        for (int i2 = 0; i2 < limits.size() - 1; i2 += 2) {
            addArcLimit(tree, limits.get(i2).doubleValue(), true);
            addArcLimit(tree, limits.get(i2 + 1).doubleValue(), false);
        }
        if (tree.getCut() == null) {
            return null;
        }
        return new ArcsSet(tree, getTolerance());
    }

    /* loaded from: classes10.dex */
    public static class Split {
        private final ArcsSet minus;
        private final ArcsSet plus;

        private Split(ArcsSet plus, ArcsSet minus) {
            this.plus = plus;
            this.minus = minus;
        }

        public ArcsSet getPlus() {
            return this.plus;
        }

        public ArcsSet getMinus() {
            return this.minus;
        }

        public Side getSide() {
            if (this.plus != null) {
                if (this.minus != null) {
                    return Side.BOTH;
                }
                return Side.PLUS;
            }
            if (this.minus != null) {
                return Side.MINUS;
            }
            return Side.HYPER;
        }
    }

    /* loaded from: classes10.dex */
    public static class InconsistentStateAt2PiWrapping extends MathIllegalArgumentException {
        private static final long serialVersionUID = 20140107;

        public InconsistentStateAt2PiWrapping() {
            super(LocalizedFormats.INCONSISTENT_STATE_AT_2_PI_WRAPPING, new Object[0]);
        }
    }
}
