package org.apache.poi.util;

import java.awt.geom.Dimension2D;
import org.apache.commons.collections4.CollectionUtils;

/* loaded from: classes10.dex */
public class Dimension2DDouble extends Dimension2D {
    double height;
    double width;

    public Dimension2DDouble() {
        this.width = 0.0d;
        this.height = 0.0d;
    }

    public Dimension2DDouble(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Dimension2DDouble)) {
            return false;
        }
        Dimension2DDouble other = (Dimension2DDouble) obj;
        return this.width == other.width && this.height == other.height;
    }

    public int hashCode() {
        double sum = this.width + this.height;
        return (int) Math.ceil((((1.0d + sum) * sum) / 2.0d) + this.width);
    }

    public String toString() {
        return "Dimension2DDouble[" + this.width + ", " + this.height + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }
}
