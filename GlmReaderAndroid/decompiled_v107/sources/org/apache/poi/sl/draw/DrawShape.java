package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Locale;
import org.apache.poi.sl.usermodel.PlaceableShape;
import org.apache.poi.sl.usermodel.Shape;
import org.apache.poi.sl.usermodel.StrokeStyle;

/* loaded from: classes10.dex */
public class DrawShape implements Drawable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected final Shape<?, ?> shape;

    public DrawShape(Shape<?, ?> shape) {
        this.shape = shape;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isHSLF(Object shape) {
        return shape.getClass().getName().toLowerCase(Locale.ROOT).contains("hslf");
    }

    @Override // org.apache.poi.sl.draw.Drawable
    public void applyTransform(Graphics2D graphics) {
        Rectangle2D anchor;
        if (!(this.shape instanceof PlaceableShape) || graphics == null || (anchor = getAnchor(graphics, (PlaceableShape<?, ?>) this.shape)) == null) {
            return;
        }
        if (isHSLF(this.shape)) {
            flipHorizontal(graphics, anchor);
            flipVertical(graphics, anchor);
            rotate(graphics, anchor);
        } else {
            rotate(graphics, anchor);
            flipHorizontal(graphics, anchor);
            flipVertical(graphics, anchor);
        }
    }

    private void flipHorizontal(Graphics2D graphics, Rectangle2D anchor) {
        if (!(this.shape instanceof PlaceableShape) || anchor == null) {
            throw new AssertionError();
        }
        if (((PlaceableShape) this.shape).getFlipHorizontal()) {
            graphics.translate(anchor.getX() + anchor.getWidth(), anchor.getY());
            graphics.scale(-1.0d, 1.0d);
            graphics.translate(-anchor.getX(), -anchor.getY());
        }
    }

    private void flipVertical(Graphics2D graphics, Rectangle2D anchor) {
        if (!(this.shape instanceof PlaceableShape) || anchor == null) {
            throw new AssertionError();
        }
        if (((PlaceableShape) this.shape).getFlipVertical()) {
            graphics.translate(anchor.getX(), anchor.getY() + anchor.getHeight());
            graphics.scale(1.0d, -1.0d);
            graphics.translate(-anchor.getX(), -anchor.getY());
        }
    }

    private void rotate(Graphics2D graphics, Rectangle2D anchor) {
        if (!(this.shape instanceof PlaceableShape) || anchor == null) {
            throw new AssertionError();
        }
        double rotation = ((PlaceableShape) this.shape).getRotation();
        if (rotation != 0.0d) {
            graphics.rotate(Math.toRadians(rotation), anchor.getCenterX(), anchor.getCenterY());
        }
    }

    private static double safeScale(double dim1, double dim2) {
        if (dim1 == 0.0d || dim2 == 0.0d) {
            return 1.0d;
        }
        return dim1 / dim2;
    }

    @Override // org.apache.poi.sl.draw.Drawable
    public void draw(Graphics2D graphics) {
    }

    @Override // org.apache.poi.sl.draw.Drawable
    public void drawContent(Graphics2D graphics) {
    }

    public static Rectangle2D getAnchor(Graphics2D graphics, PlaceableShape<?, ?> shape) {
        java.awt.Shape bounds2D;
        java.awt.Shape anchor = shape.getAnchor();
        AffineTransform tx = null;
        if (anchor == null) {
            return null;
        }
        boolean isHSLF = isHSLF(shape);
        if (graphics != null) {
            tx = (AffineTransform) graphics.getRenderingHint(Drawable.GROUP_TRANSFORM);
        }
        if (tx == null) {
            tx = new AffineTransform();
        }
        double rotation = ((shape.getRotation() % 360.0d) + 360.0d) % 360.0d;
        int quadrant = ((((int) rotation) + 45) / 90) % 4;
        if (quadrant == 1 || quadrant == 3) {
            Rectangle2D anchorO = tx.createTransformedShape(anchor).getBounds2D();
            double centerX = anchorO.getCenterX();
            double centerY = anchorO.getCenterY();
            AffineTransform txs2 = new AffineTransform();
            if (!isHSLF) {
                txs2.quadrantRotate(1, centerX, centerY);
                txs2.concatenate(tx);
            }
            txs2.quadrantRotate(3, centerX, centerY);
            if (isHSLF) {
                txs2.concatenate(tx);
            }
            Rectangle2D anchorT = txs2.createTransformedShape(anchor).getBounds2D();
            double scaleX2 = safeScale(anchorO.getWidth(), anchorT.getWidth());
            double scaleY2 = safeScale(anchorO.getHeight(), anchorT.getHeight());
            double centerX2 = anchor.getCenterX();
            double centerY2 = anchor.getCenterY();
            AffineTransform txs22 = new AffineTransform();
            txs22.translate(centerX2, centerY2);
            txs22.scale(scaleY2, scaleX2);
            txs22.translate(-centerX2, -centerY2);
            bounds2D = txs22.createTransformedShape(anchor).getBounds2D();
        } else {
            bounds2D = anchor;
        }
        if (tx.isIdentity()) {
            return bounds2D;
        }
        java.awt.Shape anc = tx.createTransformedShape(bounds2D);
        return anc != null ? anc.getBounds2D() : bounds2D;
    }

    public static Rectangle2D getAnchor(Graphics2D graphics, Rectangle2D anchor) {
        if (graphics == null) {
            return anchor;
        }
        AffineTransform tx = (AffineTransform) graphics.getRenderingHint(Drawable.GROUP_TRANSFORM);
        if (tx != null && !tx.isIdentity() && tx.createTransformedShape(anchor) != null) {
            return tx.createTransformedShape(anchor).getBounds2D();
        }
        return anchor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Shape<?, ?> getShape() {
        return this.shape;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static BasicStroke getStroke(StrokeStyle strokeStyle) {
        float lineWidth;
        float[] dashPatF;
        StrokeStyle.LineCap lineCapE;
        int lineCap;
        float lineWidth2 = (float) strokeStyle.getLineWidth();
        if (lineWidth2 != 0.0f) {
            lineWidth = lineWidth2;
        } else {
            lineWidth = 0.25f;
        }
        StrokeStyle.LineDash lineDash = strokeStyle.getLineDash();
        if (lineDash == null) {
            lineDash = StrokeStyle.LineDash.SOLID;
        }
        int[] dashPatI = lineDash.pattern;
        if (dashPatI == null) {
            dashPatF = null;
        } else {
            float[] dashPatF2 = new float[dashPatI.length];
            for (int i = 0; i < dashPatI.length; i++) {
                dashPatF2[i] = dashPatI[i] * Math.max(1.0f, lineWidth);
            }
            dashPatF = dashPatF2;
        }
        StrokeStyle.LineCap lineCapE2 = strokeStyle.getLineCap();
        if (lineCapE2 != null) {
            lineCapE = lineCapE2;
        } else {
            lineCapE = StrokeStyle.LineCap.FLAT;
        }
        switch (lineCapE) {
            case ROUND:
                lineCap = 1;
                break;
            case SQUARE:
                lineCap = 2;
                break;
            default:
                lineCap = 0;
                break;
        }
        return new BasicStroke(lineWidth, lineCap, 1, 10.0f, dashPatF, 0.0f);
    }
}
