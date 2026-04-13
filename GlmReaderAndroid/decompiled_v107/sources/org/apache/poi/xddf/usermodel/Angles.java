package org.apache.poi.xddf.usermodel;

/* loaded from: classes10.dex */
public class Angles {
    public static final int OOXML_DEGREE = 60000;

    public static final int degreesToAttribute(double angle) {
        return Math.toIntExact(Math.round(60000.0d * angle));
    }

    public static final double attributeToDegrees(int angle) {
        return angle / 60000.0d;
    }
}
