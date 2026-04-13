package org.apache.poi.sl.usermodel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.common.Duplicatable;

/* loaded from: classes10.dex */
public final class Insets2D implements Duplicatable {
    public double bottom;
    public double left;
    public double right;
    public double top;

    public Insets2D(double top, double left, double bottom, double right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public void set(double top, double left, double bottom, double right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Insets2D)) {
            return false;
        }
        Insets2D insets = (Insets2D) obj;
        return this.top == insets.top && this.left == insets.left && this.bottom == insets.bottom && this.right == insets.right;
    }

    public int hashCode() {
        double sum1 = this.left + this.bottom;
        double sum2 = this.right + this.top;
        double val1 = (((sum1 + 1.0d) * sum1) / 2.0d) + this.left;
        double val2 = (((sum2 + 1.0d) * sum2) / 2.0d) + this.top;
        double sum3 = val1 + val2;
        return (int) ((((1.0d + sum3) * sum3) / 2.0d) + val2);
    }

    public String toString() {
        return getClass().getName() + "[top=" + this.top + ",left=" + this.left + ",bottom=" + this.bottom + ",right=" + this.right + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    @Override // org.apache.poi.common.Duplicatable
    public Insets2D copy() {
        return new Insets2D(this.top, this.left, this.bottom, this.right);
    }
}
