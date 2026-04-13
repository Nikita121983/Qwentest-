package org.apache.poi.sl.usermodel;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.List;
import org.apache.commons.compress.compressors.CompressorStreamFactory;

/* loaded from: classes10.dex */
public interface PaintStyle {

    /* loaded from: classes10.dex */
    public enum FlipMode {
        NONE,
        X,
        Y,
        XY
    }

    /* loaded from: classes10.dex */
    public enum PaintModifier {
        NONE,
        NORM,
        LIGHTEN,
        LIGHTEN_LESS,
        DARKEN,
        DARKEN_LESS
    }

    /* loaded from: classes10.dex */
    public interface SolidPaint extends PaintStyle {
        ColorStyle getSolidColor();
    }

    /* loaded from: classes10.dex */
    public enum TextureAlignment {
        BOTTOM("b"),
        BOTTOM_LEFT("bl"),
        BOTTOM_RIGHT(CompressorStreamFactory.BROTLI),
        CENTER("ctr"),
        LEFT("l"),
        RIGHT("r"),
        TOP("t"),
        TOP_LEFT("tl"),
        TOP_RIGHT("tr");

        private final String ooxmlId;

        TextureAlignment(String ooxmlId) {
            this.ooxmlId = ooxmlId;
        }

        public static TextureAlignment fromOoxmlId(String ooxmlId) {
            for (TextureAlignment ta : values()) {
                if (ta.ooxmlId.equals(ooxmlId)) {
                    return ta;
                }
            }
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public interface GradientPaint extends PaintStyle {

        /* loaded from: classes10.dex */
        public enum GradientType {
            linear,
            circular,
            rectangular,
            shape
        }

        double getGradientAngle();

        ColorStyle[] getGradientColors();

        float[] getGradientFractions();

        GradientType getGradientType();

        boolean isRotatedWithShape();

        default Insets2D getFillToInsets() {
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public interface TexturePaint extends PaintStyle {
        int getAlpha();

        String getContentType();

        InputStream getImageData();

        Shape getShape();

        default boolean isRotatedWithShape() {
            return true;
        }

        default Dimension2D getScale() {
            return null;
        }

        default Point2D getOffset() {
            return null;
        }

        default FlipMode getFlipMode() {
            return FlipMode.NONE;
        }

        default TextureAlignment getAlignment() {
            return null;
        }

        default Insets2D getInsets() {
            return null;
        }

        default Insets2D getStretch() {
            return null;
        }

        default List<ColorStyle> getDuoTone() {
            return null;
        }
    }
}
