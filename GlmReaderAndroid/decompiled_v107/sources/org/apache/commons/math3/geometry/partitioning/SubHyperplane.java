package org.apache.commons.math3.geometry.partitioning;

import org.apache.commons.math3.geometry.Space;

/* loaded from: classes10.dex */
public interface SubHyperplane<S extends Space> {
    SubHyperplane<S> copySelf();

    Hyperplane<S> getHyperplane();

    double getSize();

    boolean isEmpty();

    SubHyperplane<S> reunite(SubHyperplane<S> subHyperplane);

    @Deprecated
    Side side(Hyperplane<S> hyperplane);

    SplitSubHyperplane<S> split(Hyperplane<S> hyperplane);

    /* loaded from: classes10.dex */
    public static class SplitSubHyperplane<U extends Space> {
        private final SubHyperplane<U> minus;
        private final SubHyperplane<U> plus;

        public SplitSubHyperplane(SubHyperplane<U> plus, SubHyperplane<U> minus) {
            this.plus = plus;
            this.minus = minus;
        }

        public SubHyperplane<U> getPlus() {
            return this.plus;
        }

        public SubHyperplane<U> getMinus() {
            return this.minus;
        }

        public Side getSide() {
            if (this.plus != null && !this.plus.isEmpty()) {
                if (this.minus != null && !this.minus.isEmpty()) {
                    return Side.BOTH;
                }
                return Side.PLUS;
            }
            if (this.minus != null && !this.minus.isEmpty()) {
                return Side.MINUS;
            }
            return Side.HYPER;
        }
    }
}
