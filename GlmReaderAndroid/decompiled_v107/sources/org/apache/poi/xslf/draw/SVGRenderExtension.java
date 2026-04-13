package org.apache.poi.xslf.draw;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import org.apache.batik.svggen.DefaultExtensionHandler;
import org.apache.batik.svggen.SVGColor;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGPaintDescriptor;
import org.apache.batik.svggen.SVGTexturePaint;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.draw.DrawTexturePaint;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.sl.draw.PathGradientPaint;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Internal
/* loaded from: classes10.dex */
public class SVGRenderExtension extends DefaultExtensionHandler {
    private static final int LINE_LENGTH = 65;
    private static final String XLINK_NS = "http://www.w3.org/1999/xlink";
    private final Map<Long, String> imageMap = new HashMap();
    private WeakReference<SVGGraphics2D> svgGraphics2D = null;

    public SVGGraphics2D getSvgGraphics2D() {
        if (this.svgGraphics2D != null) {
            return this.svgGraphics2D.get();
        }
        return null;
    }

    public void setSvgGraphics2D(SVGGraphics2D svgGraphics2D) {
        this.svgGraphics2D = new WeakReference<>(svgGraphics2D);
    }

    public SVGPaintDescriptor handlePaint(Paint paint, SVGGeneratorContext generatorContext) {
        if (paint instanceof LinearGradientPaint) {
            return getLgpDescriptor((LinearGradientPaint) paint, generatorContext);
        }
        if (paint instanceof RadialGradientPaint) {
            return getRgpDescriptor((RadialGradientPaint) paint, generatorContext);
        }
        if (paint instanceof PathGradientPaint) {
            return getPathDescriptor((PathGradientPaint) paint, generatorContext);
        }
        if (paint instanceof DrawTexturePaint) {
            return getDtpDescriptor((DrawTexturePaint) paint, generatorContext);
        }
        return super.handlePaint(paint, generatorContext);
    }

    private SVGPaintDescriptor getPathDescriptor(PathGradientPaint gradient, SVGGeneratorContext genCtx) {
        RenderingHints hints = genCtx.getGraphicContextDefaults().getRenderingHints();
        Shape shape = (Shape) hints.get(Drawable.GRADIENT_SHAPE);
        if (shape == null) {
            return null;
        }
        PathGradientPaint.PathGradientContext context = gradient.createContext(ColorModel.getRGBdefault(), shape.getBounds(), shape.getBounds2D(), new AffineTransform(), hints);
        WritableRaster raster = context.createRaster();
        BufferedImage img = new BufferedImage(context.getColorModel(), raster, false, (Hashtable) null);
        SVGTexturePaint texturePaint = new SVGTexturePaint(genCtx);
        TexturePaint tp = new TexturePaint(img, shape.getBounds2D());
        return texturePaint.toSVG(tp);
    }

    private SVGPaintDescriptor getRgpDescriptor(RadialGradientPaint gradient, SVGGeneratorContext genCtx) {
        Element gradElem = genCtx.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "radialGradient");
        String id = genCtx.getIDGenerator().generateID("gradient");
        gradElem.setAttribute("id", id);
        setPoint(gradElem, gradient.getCenterPoint(), "cx", "cy");
        setPoint(gradElem, gradient.getFocusPoint(), "fx", "fy");
        gradElem.setAttribute("r", String.valueOf(gradient.getRadius()));
        addMgpAttributes(gradElem, genCtx, gradient);
        return new SVGPaintDescriptor("url(#" + id + ")", "1", gradElem);
    }

    private SVGPaintDescriptor getLgpDescriptor(LinearGradientPaint gradient, SVGGeneratorContext genCtx) {
        Element gradElem = genCtx.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "linearGradient");
        String id = genCtx.getIDGenerator().generateID("gradient");
        gradElem.setAttribute("id", id);
        setPoint(gradElem, gradient.getStartPoint(), "x1", "y1");
        setPoint(gradElem, gradient.getEndPoint(), "x2", "y2");
        addMgpAttributes(gradElem, genCtx, gradient);
        return new SVGPaintDescriptor("url(#" + id + ")", "1", gradElem);
    }

    private void addMgpAttributes(Element gradElem, SVGGeneratorContext genCtx, MultipleGradientPaint gradient) {
        String cycleVal;
        gradElem.setAttribute("gradientUnits", "userSpaceOnUse");
        switch (AnonymousClass1.$SwitchMap$java$awt$MultipleGradientPaint$CycleMethod[gradient.getCycleMethod().ordinal()]) {
            case 1:
                cycleVal = "reflect";
                break;
            case 2:
                cycleVal = "repeat";
                break;
            default:
                cycleVal = "pad";
                break;
        }
        gradElem.setAttribute("spreadMethod", cycleVal);
        String colorSpace = gradient.getColorSpace() == MultipleGradientPaint.ColorSpaceType.LINEAR_RGB ? "linearRGB" : "sRGB";
        gradElem.setAttribute("color-interpolation", colorSpace);
        AffineTransform tf = gradient.getTransform();
        if (!tf.isIdentity()) {
            String matrix = "matrix(" + tf.getScaleX() + StringUtils.SPACE + tf.getShearY() + StringUtils.SPACE + tf.getShearX() + StringUtils.SPACE + tf.getScaleY() + StringUtils.SPACE + tf.getTranslateX() + StringUtils.SPACE + tf.getTranslateY() + ")";
            gradElem.setAttribute("gradientTransform", matrix);
        }
        Color[] colors = gradient.getColors();
        float[] fracs = gradient.getFractions();
        for (int i = 0; i < colors.length; i++) {
            Element stop = genCtx.getDOMFactory().createElementNS("http://www.w3.org/2000/svg", "stop");
            SVGPaintDescriptor pd = SVGColor.toSVG(colors[i], genCtx);
            stop.setAttribute(TypedValues.CycleType.S_WAVE_OFFSET, ((int) (fracs[i] * 100.0f)) + "%");
            stop.setAttribute("stop-color", pd.getPaintValue());
            if (colors[i].getAlpha() != 255) {
                stop.setAttribute("stop-opacity", pd.getOpacityValue());
            }
            gradElem.appendChild(stop);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.poi.xslf.draw.SVGRenderExtension$1, reason: invalid class name */
    /* loaded from: classes10.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod = new int[MultipleGradientPaint.CycleMethod.values().length];

        static {
            try {
                $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod[MultipleGradientPaint.CycleMethod.REFLECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod[MultipleGradientPaint.CycleMethod.REPEAT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$awt$MultipleGradientPaint$CycleMethod[MultipleGradientPaint.CycleMethod.NO_CYCLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static void setPoint(Element gradElem, Point2D point, String x, String y) {
        gradElem.setAttribute(x, Double.toString(point.getX()));
        gradElem.setAttribute(y, Double.toString(point.getY()));
    }

    private SVGPaintDescriptor getDtpDescriptor(DrawTexturePaint tdp, SVGGeneratorContext genCtx) {
        Dimension2D scale;
        Point2D offset;
        String imgID = getImageID(tdp, genCtx);
        Document domFactory = genCtx.getDOMFactory();
        Element patternDef = domFactory.createElementNS("http://www.w3.org/2000/svg", "pattern");
        String patID = genCtx.getIDGenerator().generateID("pattern");
        PaintStyle.TexturePaint fill = tdp.getFill();
        Insets2D stretch = fill.getStretch();
        if (stretch == null) {
            stretch = new Insets2D(0.0d, 0.0d, 0.0d, 0.0d);
        }
        Rectangle2D anchorRect = tdp.getAnchorRect();
        String x = genCtx.doubleString(((-stretch.left) / 100000.0d) * anchorRect.getWidth());
        String y = genCtx.doubleString(((-stretch.top) / 100000.0d) * anchorRect.getHeight());
        String w = genCtx.doubleString((((stretch.left + 100000.0d) + stretch.right) / 100000.0d) * anchorRect.getWidth());
        String h = genCtx.doubleString((((stretch.top + 100000.0d) + stretch.bottom) / 100000.0d) * anchorRect.getHeight());
        Dimension2D scale2 = fill.getScale();
        if (scale2 != null) {
            scale = scale2;
        } else {
            scale = new Dimension2DDouble(1.0d, 1.0d);
        }
        Point2D offset2 = fill.getOffset();
        if (offset2 != null) {
            offset = offset2;
        } else {
            offset = new Point2D.Double(0.0d, 0.0d);
        }
        PaintStyle.FlipMode flipMode = fill.getFlipMode();
        if (flipMode == null) {
            PaintStyle.FlipMode flipMode2 = PaintStyle.FlipMode.NONE;
        }
        setAttribute(genCtx, patternDef, null, "patternUnits", "objectBoundingBox", null, "id", patID, null, "x", Double.valueOf(offset.getX()), null, "y", Double.valueOf(offset.getY()), null, "width", genCtx.doubleString(scale.getWidth() * 100.0d) + "%", null, "height", genCtx.doubleString(scale.getHeight() * 100.0d) + "%", null, "preserveAspectRatio", "none", null, "viewBox", x + StringUtils.SPACE + y + StringUtils.SPACE + w + StringUtils.SPACE + h);
        org.apache.poi.sl.usermodel.Shape<?, ?> slShape = fill.getShape();
        if (!fill.isRotatedWithShape() && (slShape instanceof SimpleShape)) {
            double rot = ((SimpleShape) slShape).getRotation();
            if (rot != 0.0d) {
                setAttribute(genCtx, patternDef, null, "patternTransform", "rotate(" + genCtx.doubleString(-rot) + ")");
            }
        }
        Element useImageEl = domFactory.createElementNS("http://www.w3.org/2000/svg", "use");
        useImageEl.setAttributeNS(null, "href", "#" + imgID);
        patternDef.appendChild(useImageEl);
        String patternAttrBuf = "url(#" + patID + ")";
        return new SVGPaintDescriptor(patternAttrBuf, "1", patternDef);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0075 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getImageID(org.apache.poi.sl.draw.DrawTexturePaint r33, org.apache.batik.svggen.SVGGeneratorContext r34) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.draw.SVGRenderExtension.getImageID(org.apache.poi.sl.draw.DrawTexturePaint, org.apache.batik.svggen.SVGGeneratorContext):java.lang.String");
    }

    private static void setAttribute(SVGGeneratorContext genCtx, Element el, Object... params) {
        String val;
        for (int i = 0; i < params.length; i += 3) {
            String ns = (String) params[i];
            String name = (String) params[i + 1];
            Object oval = params[i + 2];
            if (oval instanceof String) {
                val = (String) oval;
            } else if (oval instanceof Number) {
                val = genCtx.doubleString(((Number) oval).doubleValue());
            } else if (oval == 0) {
                val = "";
            } else {
                val = oval.toString();
            }
            el.setAttributeNS(ns, name, val);
        }
    }
}
