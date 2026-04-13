package org.apache.poi.util;

import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

/* loaded from: classes10.dex */
public class Units {
    public static final float DEFAULT_CHARACTER_WIDTH = 7.0017f;
    public static final int EMU_PER_CENTIMETER = 360000;
    public static final int EMU_PER_CHARACTER = 66691;
    public static final int EMU_PER_DXA = 635;
    public static final int EMU_PER_INCH = 914400;
    public static final int EMU_PER_PIXEL = 9525;
    public static final int EMU_PER_POINT = 12700;
    public static final int MASTER_DPI = 576;
    public static final int PIXEL_DPI = 96;
    public static final int POINT_DPI = 72;

    public static int toEMU(double points) {
        return (int) Math.rint(12700.0d * points);
    }

    public static int pixelToEMU(int pixels) {
        return pixels * 9525;
    }

    public static double toPoints(long emu) {
        if (emu == -1) {
            return -1.0d;
        }
        return emu / 12700.0d;
    }

    public static double fixedPointToDouble(int fixedPoint) {
        int i = fixedPoint >> 16;
        int f = 65535 & fixedPoint;
        return i + (f / 65536.0d);
    }

    public static int doubleToFixedPoint(double floatPoint) {
        double fractionalPart = floatPoint % 1.0d;
        double integralPart = floatPoint - fractionalPart;
        int i = (int) Math.floor(integralPart);
        int f = (int) Math.rint(65536.0d * fractionalPart);
        return (i << 16) | (65535 & f);
    }

    public static double masterToPoints(int masterDPI) {
        double points = masterDPI;
        return (points * 72.0d) / 576.0d;
    }

    public static int pointsToMaster(double points) {
        return (int) Math.rint((points * 576.0d) / 72.0d);
    }

    public static int pointsToPixel(double points) {
        return (int) Math.rint((points * 96.0d) / 72.0d);
    }

    public static double pixelToPoints(double pixel) {
        double points = pixel * 72.0d;
        return points / 96.0d;
    }

    public static Dimension2D pointsToPixel(Dimension2D pointsDim) {
        double width = (pointsDim.getWidth() * 96.0d) / 72.0d;
        double height = (pointsDim.getHeight() * 96.0d) / 72.0d;
        return new Dimension2DDouble(width, height);
    }

    public static Dimension2D pixelToPoints(Dimension2D pointsDim) {
        double width = (pointsDim.getWidth() * 72.0d) / 96.0d;
        double height = (pointsDim.getHeight() * 72.0d) / 96.0d;
        return new Dimension2DDouble(width, height);
    }

    public static Rectangle2D pointsToPixel(Rectangle2D pointsDim) {
        double x = (pointsDim.getX() * 96.0d) / 72.0d;
        double y = (pointsDim.getY() * 96.0d) / 72.0d;
        double width = (pointsDim.getWidth() * 96.0d) / 72.0d;
        double height = (pointsDim.getHeight() * 96.0d) / 72.0d;
        return new Rectangle2D.Double(x, y, width, height);
    }

    public static Rectangle2D pixelToPoints(Rectangle2D pointsDim) {
        double x = (pointsDim.getX() * 72.0d) / 96.0d;
        double y = (pointsDim.getY() * 72.0d) / 96.0d;
        double width = (pointsDim.getWidth() * 72.0d) / 96.0d;
        double height = (pointsDim.getHeight() * 72.0d) / 96.0d;
        return new Rectangle2D.Double(x, y, width, height);
    }

    public static int charactersToEMU(double characters) {
        return ((int) characters) * EMU_PER_CHARACTER;
    }

    public static int columnWidthToEMU(int columnWidth) {
        return charactersToEMU(columnWidth / 256.0d);
    }

    public static double toDXA(long emu) {
        if (emu == -1) {
            return -1.0d;
        }
        return emu / 635.0d;
    }
}
