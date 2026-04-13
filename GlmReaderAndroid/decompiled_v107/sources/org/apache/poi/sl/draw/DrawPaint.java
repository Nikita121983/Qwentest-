package org.apache.poi.sl.draw;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.sl.draw.geom.ArcToCommand;
import org.apache.poi.sl.usermodel.AbstractColorStyle;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.util.Dimension2DDouble;

/* loaded from: classes10.dex */
public class DrawPaint {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DrawPaint.class);
    private static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, 0.0f);
    protected PlaceableShape<?, ?> shape;

    public DrawPaint(PlaceableShape<?, ?> shape) {
        this.shape = shape;
    }

    /* loaded from: classes10.dex */
    private static class SimpleSolidPaint implements PaintStyle.SolidPaint {
        private final ColorStyle solidColor;

        SimpleSolidPaint(final Color color) {
            if (color == null) {
                throw new NullPointerException("Color needs to be specified");
            }
            this.solidColor = new AbstractColorStyle() { // from class: org.apache.poi.sl.draw.DrawPaint.SimpleSolidPaint.1
                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public Color getColor() {
                    return new Color(color.getRed(), color.getGreen(), color.getBlue());
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getAlpha() {
                    return (int) Math.round((color.getAlpha() * 100000.0d) / 255.0d);
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getHueOff() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getHueMod() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getSatOff() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getSatMod() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getLumOff() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getLumMod() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getShade() {
                    return -1;
                }

                @Override // org.apache.poi.sl.usermodel.ColorStyle
                public int getTint() {
                    return -1;
                }
            };
        }

        SimpleSolidPaint(ColorStyle color) {
            if (color == null) {
                throw new NullPointerException("Color needs to be specified");
            }
            this.solidColor = color;
        }

        @Override // org.apache.poi.sl.usermodel.PaintStyle.SolidPaint
        public ColorStyle getSolidColor() {
            return this.solidColor;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof PaintStyle.SolidPaint)) {
                return false;
            }
            return Objects.equals(getSolidColor(), ((PaintStyle.SolidPaint) o).getSolidColor());
        }

        public int hashCode() {
            return Objects.hash(this.solidColor);
        }
    }

    public static PaintStyle.SolidPaint createSolidPaint(Color color) {
        if (color == null) {
            return null;
        }
        return new SimpleSolidPaint(color);
    }

    public static PaintStyle.SolidPaint createSolidPaint(ColorStyle color) {
        if (color == null) {
            return null;
        }
        return new SimpleSolidPaint(color);
    }

    public Paint getPaint(Graphics2D graphics, PaintStyle paint) {
        return getPaint(graphics, paint, PaintStyle.PaintModifier.NORM);
    }

    public Paint getPaint(Graphics2D graphics, PaintStyle paint, PaintStyle.PaintModifier modifier) {
        if (modifier == PaintStyle.PaintModifier.NONE) {
            return TRANSPARENT;
        }
        if (paint instanceof PaintStyle.SolidPaint) {
            return getSolidPaint((PaintStyle.SolidPaint) paint, graphics, modifier);
        }
        if (paint instanceof PaintStyle.GradientPaint) {
            return getGradientPaint((PaintStyle.GradientPaint) paint, graphics);
        }
        if (paint instanceof PaintStyle.TexturePaint) {
            return getTexturePaint((PaintStyle.TexturePaint) paint, graphics);
        }
        return TRANSPARENT;
    }

    protected Paint getSolidPaint(PaintStyle.SolidPaint fill, Graphics2D graphics, final PaintStyle.PaintModifier modifier) {
        final ColorStyle orig = fill.getSolidColor();
        ColorStyle cs = new AbstractColorStyle() { // from class: org.apache.poi.sl.draw.DrawPaint.1
            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public Color getColor() {
                return orig.getColor();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getAlpha() {
                return orig.getAlpha();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getHueOff() {
                return orig.getHueOff();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getHueMod() {
                return orig.getHueMod();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getSatOff() {
                return orig.getSatOff();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getSatMod() {
                return orig.getSatMod();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getLumOff() {
                return orig.getLumOff();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getLumMod() {
                return orig.getLumMod();
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getShade() {
                return scale(orig.getShade(), PaintStyle.PaintModifier.DARKEN_LESS, PaintStyle.PaintModifier.DARKEN);
            }

            @Override // org.apache.poi.sl.usermodel.ColorStyle
            public int getTint() {
                return scale(orig.getTint(), PaintStyle.PaintModifier.LIGHTEN_LESS, PaintStyle.PaintModifier.LIGHTEN);
            }

            private int scale(int value, PaintStyle.PaintModifier lessModifier, PaintStyle.PaintModifier moreModifier) {
                if (value == -1) {
                    return -1;
                }
                int delta = modifier == lessModifier ? AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH : modifier == moreModifier ? 40000 : 0;
                return Math.min(BZip2Constants.BASEBLOCKSIZE, Math.max(0, value) + delta);
            }
        };
        return applyColorTransform(cs);
    }

    protected Paint getGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        switch (fill.getGradientType()) {
            case linear:
                return createLinearGradientPaint(fill, graphics);
            case rectangular:
            case circular:
                return createRadialGradientPaint(fill, graphics);
            case shape:
                return createPathGradientPaint(fill, graphics);
            default:
                throw new UnsupportedOperationException("gradient fill of type " + fill + " not supported.");
        }
    }

    protected Paint getTexturePaint(PaintStyle.TexturePaint fill, Graphics2D graphics) {
        BufferedImage image;
        Graphics2D g;
        if (graphics == null) {
            throw new AssertionError();
        }
        String contentType = fill.getContentType();
        if (contentType != null && !contentType.isEmpty()) {
            ImageRenderer renderer = DrawPictureShape.getImageRenderer(graphics, contentType);
            Rectangle2D textAnchor = this.shape.getAnchor();
            try {
                InputStream is = fill.getImageData();
                try {
                    if (is == null) {
                        Color color = TRANSPARENT;
                        if (is != null) {
                            is.close();
                        }
                        return color;
                    }
                    Boolean cacheImage = (Boolean) graphics.getRenderingHint(Drawable.CACHE_IMAGE_SOURCE);
                    renderer.setCacheInput(cacheImage != null && cacheImage.booleanValue());
                    renderer.loadImage(is, contentType);
                    int alpha = fill.getAlpha();
                    if (alpha >= 0 && alpha < 100000) {
                        renderer.setAlpha(alpha / 100000.0f);
                    }
                    Dimension2D imgDim = renderer.getDimension();
                    if ("image/x-wmf".contains(contentType)) {
                        imgDim = new Dimension2DDouble(textAnchor.getWidth(), textAnchor.getHeight());
                    }
                    BufferedImage image2 = renderer.getImage(imgDim);
                    if (image2 == null) {
                        LOG.atError().log("Can't load image data");
                        Color color2 = TRANSPARENT;
                        if (is != null) {
                            is.close();
                        }
                        return color2;
                    }
                    double flipX = 1.0d;
                    double flipY = 1.0d;
                    PaintStyle.FlipMode flip = fill.getFlipMode();
                    if (flip == null || flip == PaintStyle.FlipMode.NONE) {
                        image = image2;
                    } else {
                        int width = image2.getWidth();
                        int height = image2.getHeight();
                        switch (flip) {
                            case X:
                                flipX = 2.0d;
                                break;
                            case Y:
                                flipY = 2.0d;
                                break;
                            case XY:
                                flipX = 2.0d;
                                flipY = 2.0d;
                                break;
                        }
                        double flipX2 = flipX;
                        BufferedImage img = new BufferedImage((int) (width * flipX), (int) (height * flipY), 2);
                        Graphics2D g2 = img.createGraphics();
                        g2.drawImage(image2, 0, 0, (ImageObserver) null);
                        switch (flip) {
                            case X:
                                g = g2;
                                g.drawImage(image2, width * 2, 0, -width, height, (ImageObserver) null);
                                break;
                            case Y:
                                g = g2;
                                g.drawImage(image2, 0, height * 2, width, -height, (ImageObserver) null);
                                break;
                            case XY:
                                g = g2;
                                g.drawImage(image2, width * 2, 0, -width, height, (ImageObserver) null);
                                g.drawImage(image2, 0, height * 2, width, -height, (ImageObserver) null);
                                g.drawImage(image2, width * 2, height * 2, -width, -height, (ImageObserver) null);
                                break;
                            default:
                                g = g2;
                                break;
                        }
                        g.dispose();
                        image = img;
                        flipX = flipX2;
                    }
                    BufferedImage image3 = colorizePattern(fill, image);
                    Shape s = (Shape) graphics.getRenderingHint(Drawable.GRADIENT_SHAPE);
                    DrawTexturePaint drawTexturePaint = new DrawTexturePaint(renderer, image3, s, fill, flipX, flipY, renderer instanceof BitmapImageRenderer);
                    if (is != null) {
                        is.close();
                    }
                    return drawTexturePaint;
                } finally {
                }
            } catch (IOException e) {
                LOG.atError().withThrowable(e).log("Can't load image data - using transparent color");
                return TRANSPARENT;
            }
        }
        return TRANSPARENT;
    }

    private static BufferedImage colorizePattern(PaintStyle.TexturePaint fill, BufferedImage pattern) {
        List<ColorStyle> duoTone = fill.getDuoTone();
        if (duoTone == null || duoTone.size() != 2) {
            return pattern;
        }
        int redBits = pattern.getSampleModel().getSampleSize(0);
        int blendBits = Math.max(Math.min(redBits, 8), 1);
        int blendShades = 1 << blendBits;
        double blendRatio = blendShades / (1 << Math.max(redBits, 1));
        int[] gradSample = linearBlendedColors(duoTone, blendShades);
        IndexColorModel icm = new IndexColorModel(blendBits, blendShades, gradSample, 0, true, -1, 0);
        BufferedImage patIdx = new BufferedImage(pattern.getWidth(), pattern.getHeight(), 13, icm);
        WritableRaster rasterRGBA = pattern.getRaster();
        WritableRaster rasterIdx = patIdx.getRaster();
        int[] redSample = new int[pattern.getWidth()];
        int y = 0;
        while (y < pattern.getHeight()) {
            rasterRGBA.getSamples(0, y, redSample.length, 1, 0, redSample);
            scaleShades(redSample, blendRatio);
            WritableRaster rasterIdx2 = rasterIdx;
            int[] redSample2 = redSample;
            int y2 = y;
            rasterIdx2.setSamples(0, y2, redSample.length, 1, 0, redSample2);
            redSample = redSample2;
            y = y2 + 1;
            rasterIdx = rasterIdx2;
        }
        return patIdx;
    }

    private static void scaleShades(int[] samples, double ratio) {
        if (ratio != 1.0d) {
            for (int x = 0; x < samples.length; x++) {
                samples[x] = (int) Math.rint(samples[x] * ratio);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Color[] lambda$linearBlendedColors$0(int x$0) {
        return new Color[x$0];
    }

    private static int[] linearBlendedColors(List<ColorStyle> duoTone, int blendShades) {
        Color[] colors = (Color[]) duoTone.stream().map(new Function() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DrawPaint.applyColorTransform((ColorStyle) obj);
            }
        }).toArray(new IntFunction() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda5
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return DrawPaint.lambda$linearBlendedColors$0(i);
            }
        });
        float[] fractions = {0.0f, 1.0f};
        BufferedImage gradBI = new BufferedImage(blendShades, 1, 2);
        Graphics2D gradG = gradBI.createGraphics();
        gradG.setPaint(new LinearGradientPaint(0.0f, 0.0f, blendShades, 0.0f, fractions, colors));
        gradG.fillRect(0, 0, blendShades, 1);
        gradG.dispose();
        return gradBI.getRGB(0, 0, blendShades, 1, (int[]) null, 0, blendShades);
    }

    public static Color applyColorTransform(ColorStyle color) {
        if (color == null || color.getColor() == null) {
            return TRANSPARENT;
        }
        Color result = color.getColor();
        double alpha = getAlpha(result, color);
        double[] scRGB = RGB2SCRGB(result);
        applyShade(scRGB, color);
        applyTint(scRGB, color);
        double[] hsl = RGB2HSL(SCRGB2RGB(scRGB));
        applyHslModOff(hsl, 0, color.getHueMod(), color.getHueOff());
        applyHslModOff(hsl, 1, color.getSatMod(), color.getSatOff());
        applyHslModOff(hsl, 2, color.getLumMod(), color.getLumOff());
        return HSL2RGB(hsl[0], hsl[1], hsl[2], alpha);
    }

    private static double getAlpha(Color c, ColorStyle fc) {
        double alpha = c.getAlpha() / 255.0d;
        int fcAlpha = fc.getAlpha();
        if (fcAlpha != -1) {
            alpha *= fcAlpha / 100000.0d;
        }
        return Math.min(1.0d, Math.max(0.0d, alpha));
    }

    private static void applyHslModOff(double[] hsl, int hslPart, int mod, int off) {
        if (mod != -1) {
            hsl[hslPart] = hsl[hslPart] * (mod / 100000.0d);
        }
        if (off != -1) {
            hsl[hslPart] = hsl[hslPart] + (off / 1000.0d);
        }
    }

    private static void applyShade(double[] scRGB, ColorStyle fc) {
        int shade = fc.getShade();
        if (shade == -1) {
            return;
        }
        double shadePct = shade / 100000.0d;
        for (int i = 0; i < 3; i++) {
            scRGB[i] = Math.max(0.0d, Math.min(1.0d, scRGB[i] * shadePct));
        }
    }

    private static void applyTint(double[] scRGB, ColorStyle fc) {
        int tint = fc.getTint();
        if (tint == -1 || tint == 0) {
            return;
        }
        double tintPct = tint / 100000.0d;
        for (int i = 0; i < 3; i++) {
            scRGB[i] = 1.0d - ((1.0d - scRGB[i]) * tintPct);
        }
    }

    protected Paint createLinearGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        double angle = fill.getGradientAngle();
        if (!fill.isRotatedWithShape()) {
            angle -= this.shape.getRotation();
        }
        Rectangle2D anchor = DrawShape.getAnchor(graphics, this.shape);
        if (anchor == null) {
            return TRANSPARENT;
        }
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(ArcToCommand.convertOoxml2AwtAngle(-angle, anchor.getWidth(), anchor.getHeight())), anchor.getCenterX(), anchor.getCenterY());
        double diagonal = Math.sqrt(Math.pow(anchor.getWidth(), 2.0d) + Math.pow(anchor.getHeight(), 2.0d));
        final Point2D p1 = at.transform(new Point2D.Double(anchor.getCenterX() - (diagonal / 2.0d), anchor.getCenterY()), (Point2D) null);
        final Point2D p2 = at.transform(new Point2D.Double(anchor.getMaxX(), anchor.getCenterY()), (Point2D) null);
        if (p1.equals(p2) || fill.getGradientFractions().length < 2) {
            return null;
        }
        return safeFractions(new BiFunction() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return DrawPaint.lambda$createLinearGradientPaint$1(p1, p2, (float[]) obj, (Color[]) obj2);
            }
        }, fill);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Paint lambda$createLinearGradientPaint$1(Point2D p1, Point2D p2, float[] f, Color[] c) {
        return new LinearGradientPaint(p1, p2, f, c);
    }

    protected Paint createRadialGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        Rectangle2D anchor = DrawShape.getAnchor(graphics, this.shape);
        if (anchor == null) {
            return TRANSPARENT;
        }
        Insets2D insets = fill.getFillToInsets();
        if (insets == null) {
            insets = new Insets2D(0.0d, 0.0d, 0.0d, 0.0d);
        }
        final Point2D.Double r4 = new Point2D.Double(anchor.getCenterX(), anchor.getCenterY());
        final Point2D.Double r5 = new Point2D.Double(getCenterVal(anchor.getMinX(), anchor.getMaxX(), insets.left, insets.right), getCenterVal(anchor.getMinY(), anchor.getMaxY(), insets.top, insets.bottom));
        final float radius = (float) Math.max(anchor.getWidth(), anchor.getHeight());
        final AffineTransform at = new AffineTransform();
        at.translate(r5.getX(), r5.getY());
        at.scale(getScale(anchor.getMinX(), anchor.getMaxX(), insets.left, insets.right), getScale(anchor.getMinY(), anchor.getMaxY(), insets.top, insets.bottom));
        at.translate(-r5.getX(), -r5.getY());
        return safeFractions(new BiFunction() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda6
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return DrawPaint.lambda$createRadialGradientPaint$2(r4, radius, r5, at, (float[]) obj, (Color[]) obj2);
            }
        }, fill);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Paint lambda$createRadialGradientPaint$2(Point2D pCenter, float radius, Point2D pFocus, AffineTransform at, float[] f, Color[] c) {
        return new RadialGradientPaint(pCenter, radius, pFocus, f, c, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, at);
    }

    private static double getScale(double absMin, double absMax, double relMin, double relMax) {
        double absDelta = absMax - absMin;
        double absStart = (absDelta * relMin) + absMin;
        double absStop = relMin + relMax <= 1.0d ? absMax - (absDelta * relMax) : (absDelta * relMax) + absMax;
        if (absDelta == 0.0d) {
            return 1.0d;
        }
        return (absStop - absStart) / absDelta;
    }

    private static double getCenterVal(double absMin, double absMax, double relMin, double relMax) {
        double absDelta = absMax - absMin;
        double absStart = (absDelta * relMin) + absMin;
        double absStop = relMin + relMax <= 1.0d ? absMax - (absDelta * relMax) : (absDelta * relMax) + absMax;
        return ((absStop - absStart) / 2.0d) + absStart;
    }

    protected Paint createPathGradientPaint(PaintStyle.GradientPaint fill, Graphics2D graphics) {
        return safeFractions(new BiFunction() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda3
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return new PathGradientPaint((float[]) obj, (Color[]) obj2);
            }
        }, fill);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Paint safeFractions(BiFunction<float[], Color[], Paint> biFunction, PaintStyle.GradientPaint fill) {
        Iterator<Color> styles = Stream.of((Object[]) fill.getGradientColors()).map(new Function() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DrawPaint.lambda$safeFractions$3((ColorStyle) obj);
            }
        }).iterator();
        Map<Float, Color> m = new TreeMap<>();
        for (float fraction : fill.getGradientFractions()) {
            float gradientFraction = fraction;
            if (m.containsKey(Float.valueOf(gradientFraction))) {
                gradientFraction = (float) (gradientFraction + ((((double) gradientFraction) == 1.0d ? -1.0d : 1.0d) * 5.0E-8d));
            }
            m.put(Float.valueOf(gradientFraction), styles.next());
        }
        return (Paint) biFunction.apply(toArray(m.keySet()), m.values().toArray(new Color[0]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Color lambda$safeFractions$3(ColorStyle s) {
        return s == null ? TRANSPARENT : applyColorTransform(s);
    }

    private static float[] toArray(Collection<Float> floatList) {
        final int[] idx = {0};
        final float[] ret = new float[floatList.size()];
        floatList.forEach(new Consumer() { // from class: org.apache.poi.sl.draw.DrawPaint$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                DrawPaint.lambda$toArray$4(ret, idx, (Float) obj);
            }
        });
        return ret;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$toArray$4(float[] ret, int[] idx, Float f) {
        int i = idx[0];
        idx[0] = i + 1;
        ret[i] = f.floatValue();
    }

    public static Color HSL2RGB(double h, double s, double l, double alpha) {
        double s2 = Math.max(0.0d, Math.min(100.0d, s));
        double l2 = Math.max(0.0d, Math.min(100.0d, l));
        if (alpha < 0.0d || alpha > 1.0d) {
            String message = "Color parameter outside of expected range - Alpha: " + alpha;
            throw new IllegalArgumentException(message);
        }
        double h2 = (h % 360.0d) / 360.0d;
        double s3 = s2 / 100.0d;
        double l3 = l2 / 100.0d;
        double q = l3 < 0.5d ? (s3 + 1.0d) * l3 : (l3 + s3) - (s3 * l3);
        double p = (2.0d * l3) - q;
        double q2 = q;
        double r = Math.max(0.0d, HUE2RGB(p, q2, h2 + 0.3333333333333333d));
        double g = Math.max(0.0d, HUE2RGB(p, q2, h2));
        double b = Math.max(0.0d, HUE2RGB(p, q2, h2 - 0.3333333333333333d));
        return new Color((float) Math.min(r, 1.0d), (float) Math.min(g, 1.0d), (float) Math.min(b, 1.0d), (float) alpha);
    }

    private static double HUE2RGB(double p, double q, double h) {
        if (h < 0.0d) {
            h += 1.0d;
        }
        if (h > 1.0d) {
            h -= 1.0d;
        }
        if (h * 6.0d < 1.0d) {
            return ((q - p) * 6.0d * h) + p;
        }
        if (h * 2.0d < 1.0d) {
            return q;
        }
        if (3.0d * h < 2.0d) {
            return ((q - p) * 6.0d * (0.6666666666666666d - h)) + p;
        }
        return p;
    }

    public static double[] RGB2HSL(Color color) {
        double s;
        float[] rgb = color.getRGBColorComponents((float[]) null);
        double r = rgb[0];
        double g = rgb[1];
        double b = rgb[2];
        double min = Math.min(r, Math.min(g, b));
        double max = Math.max(r, Math.max(g, b));
        double h = 0.0d;
        if (max == min) {
            h = 0.0d;
        } else if (max == r) {
            h = ((((g - b) * 60.0d) / (max - min)) + 360.0d) % 360.0d;
        } else if (max == g) {
            h = (((b - r) * 60.0d) / (max - min)) + 120.0d;
        } else if (max == b) {
            h = (((r - g) * 60.0d) / (max - min)) + 240.0d;
        }
        double l = (max + min) / 2.0d;
        if (max == min) {
            s = 0.0d;
        } else if (l <= 0.5d) {
            s = (max - min) / (max + min);
        } else {
            s = (max - min) / ((2.0d - max) - min);
        }
        return new double[]{h, s * 100.0d, 100.0d * l};
    }

    public static double[] RGB2SCRGB(Color color) {
        float[] rgb = color.getColorComponents((float[]) null);
        double[] scRGB = new double[3];
        for (int i = 0; i < 3; i++) {
            if (rgb[i] < 0.0f) {
                scRGB[i] = 0.0d;
            } else if (rgb[i] <= 0.04045d) {
                scRGB[i] = rgb[i] / 12.92d;
            } else if (rgb[i] <= 1.0f) {
                scRGB[i] = Math.pow((rgb[i] + 0.055d) / 1.055d, 2.4d);
            } else {
                scRGB[i] = 1.0d;
            }
        }
        return scRGB;
    }

    public static Color SCRGB2RGB(double... scRGB) {
        double[] rgb = new double[3];
        for (int i = 0; i < 3; i++) {
            if (scRGB[i] < 0.0d) {
                rgb[i] = 0.0d;
            } else if (scRGB[i] <= 0.0031308d) {
                rgb[i] = scRGB[i] * 12.92d;
            } else if (scRGB[i] < 1.0d) {
                rgb[i] = (Math.pow(scRGB[i], 0.4166666666666667d) * 1.055d) - 0.055d;
            } else {
                rgb[i] = 1.0d;
            }
        }
        return new Color((float) rgb[0], (float) rgb[1], (float) rgb[2]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fillPaintWorkaround(Graphics2D graphics, Shape shape) {
        try {
            graphics.fill(shape);
        } catch (ArrayIndexOutOfBoundsException e) {
            LOG.atWarn().withThrowable(e).log("IBM JDK failed with TexturePaintContext AIOOBE - try adding the following to the VM parameter:\n-Xjit:exclude={sun/java2d/pipe/AlphaPaintPipe.renderPathTile(Ljava/lang/Object;[BIIIIII)V} and search for 'JIT Problem Determination for IBM SDK using -Xjit' (http://www-01.ibm.com/support/docview.wss?uid=swg21294023) for how to add/determine further excludes");
        }
    }
}
