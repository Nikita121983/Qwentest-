package org.apache.poi.sl.draw;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.IllegalPathStateException;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Hashtable;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class PathGradientPaint implements Paint {
    private final int capStyle;
    private final Color[] colors;
    private final float[] fractions;
    private final int joinStyle;
    private final int transparency;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PathGradientPaint(float[] fractions, Color[] colors) {
        this(fractions, colors, 1, 1);
    }

    private PathGradientPaint(float[] fractions, Color[] colors, int capStyle, int joinStyle) {
        this.colors = (Color[]) colors.clone();
        this.fractions = (float[]) fractions.clone();
        this.capStyle = capStyle;
        this.joinStyle = joinStyle;
        int i = 1;
        int length = colors.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            Color c = colors[i2];
            if (c != null) {
                i = (i == 0 || c.getAlpha() != 255) ? 0 : 1;
            }
            i2++;
        }
        this.transparency = i == 0 ? 3 : 1;
    }

    public PathGradientContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform transform, RenderingHints hints) {
        return new PathGradientContext(cm, deviceBounds, userBounds, transform, hints);
    }

    public int getTransparency() {
        return this.transparency;
    }

    /* loaded from: classes10.dex */
    public class PathGradientContext implements PaintContext {
        final Rectangle deviceBounds;
        final int gradientSteps;
        final RenderingHints hints;
        final PaintContext pCtx;
        WritableRaster raster;
        protected final Shape shape;
        final Rectangle2D userBounds;
        protected final AffineTransform xform;

        PathGradientContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
            this.shape = (Shape) hints.get(Drawable.GRADIENT_SHAPE);
            if (this.shape == null) {
                throw new IllegalPathStateException("PathGradientPaint needs a shape to be set via the rendering hint Drawable.GRADIANT_SHAPE.");
            }
            this.deviceBounds = deviceBounds;
            this.userBounds = userBounds;
            this.xform = xform;
            this.hints = hints;
            this.gradientSteps = getGradientSteps(this.shape);
            LinearGradientPaint gradientPaint = new LinearGradientPaint(new Point2D.Double(0.0d, 0.0d), new Point2D.Double(this.gradientSteps, 0.0d), PathGradientPaint.this.fractions, PathGradientPaint.this.colors, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform());
            Rectangle bounds = new Rectangle(0, 0, this.gradientSteps, 1);
            this.pCtx = gradientPaint.createContext(cm, bounds, bounds, new AffineTransform(), hints);
        }

        public void dispose() {
        }

        public ColorModel getColorModel() {
            return this.pCtx.getColorModel();
        }

        public Raster getRaster(int xOffset, int yOffset, int w, int h) {
            ColorModel cm = getColorModel();
            this.raster = createRaster();
            WritableRaster childRaster = cm.createCompatibleWritableRaster(w, h);
            Rectangle2D.Double r6 = new Rectangle2D.Double(xOffset, yOffset, w, h);
            if (!r6.intersects(this.deviceBounds)) {
                return childRaster;
            }
            Rectangle2D.Double r62 = new Rectangle2D.Double();
            Rectangle2D.intersect(r6, this.deviceBounds, r62);
            int dx = (int) (r62.getX() - this.deviceBounds.getX());
            int dy = (int) (r62.getY() - this.deviceBounds.getY());
            int dw = (int) r62.getWidth();
            int dh = (int) r62.getHeight();
            Object data = this.raster.getDataElements(dx, dy, dw, dh, (Object) null);
            int dx2 = (int) (r62.getX() - r6.getX());
            int dy2 = (int) (r62.getY() - r6.getY());
            childRaster.setDataElements(dx2, dy2, dw, dh, data);
            return childRaster;
        }

        int getGradientSteps(Shape gradientShape) {
            Rectangle rect = gradientShape.getBounds();
            int lower = 1;
            int upper = (int) (Math.max(rect.getWidth(), rect.getHeight()) / 2.0d);
            while (lower < upper - 1) {
                int mid = ((upper - lower) / 2) + lower;
                BasicStroke bs = new BasicStroke(mid, PathGradientPaint.this.capStyle, PathGradientPaint.this.joinStyle);
                Area area = new Area(bs.createStrokedShape(gradientShape));
                if (area.isSingular()) {
                    upper = mid;
                } else {
                    lower = mid;
                }
            }
            return Math.max(upper, 1);
        }

        public WritableRaster createRaster() {
            if (this.raster != null) {
                return this.raster;
            }
            ColorModel cm = getColorModel();
            this.raster = cm.createCompatibleWritableRaster((int) this.deviceBounds.getWidth(), (int) this.deviceBounds.getHeight());
            BufferedImage img = new BufferedImage(cm, this.raster, false, (Hashtable) null);
            Graphics2D graphics = img.createGraphics();
            graphics.setRenderingHints(this.hints);
            graphics.translate(-this.deviceBounds.getX(), -this.deviceBounds.getY());
            graphics.transform(this.xform);
            Raster img2 = this.pCtx.getRaster(0, 0, this.gradientSteps, 1);
            int[] rgb = new int[cm.getNumComponents()];
            for (int i = this.gradientSteps - 1; i >= 0; i--) {
                img2.getPixel((this.gradientSteps - i) - 1, 0, rgb);
                Color c = new Color(rgb[0], rgb[1], rgb[2]);
                if (rgb.length == 4) {
                    graphics.setComposite(AlphaComposite.getInstance(2, rgb[3] / 255.0f));
                }
                graphics.setStroke(new BasicStroke(i + 1.0f, PathGradientPaint.this.capStyle, PathGradientPaint.this.joinStyle));
                graphics.setColor(c);
                if (i == this.gradientSteps - 1) {
                    graphics.fill(this.shape);
                }
                graphics.draw(this.shape);
            }
            graphics.dispose();
            return this.raster;
        }
    }
}
