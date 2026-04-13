package org.apache.poi.sl.draw;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Dimension2DDouble;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
public class DrawTexturePaint extends TexturePaint {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Insets2D INSETS_EMPTY = new Insets2D(0.0d, 0.0d, 0.0d, 0.0d);
    private final PaintStyle.TexturePaint fill;
    private final double flipX;
    private final double flipY;
    private final ImageRenderer imgRdr;
    private final boolean isBitmapSrc;
    private final Shape shape;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DrawTexturePaint(ImageRenderer imgRdr, BufferedImage txtr, Shape shape, PaintStyle.TexturePaint fill, double flipX, double flipY, boolean isBitmapSrc) {
        super(txtr, new Rectangle2D.Double(0.0d, 0.0d, txtr.getWidth(), txtr.getHeight()));
        this.imgRdr = imgRdr;
        this.fill = fill;
        this.shape = shape;
        this.flipX = flipX;
        this.flipY = flipY;
        this.isBitmapSrc = isBitmapSrc;
    }

    public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
        Rectangle2D usedBounds;
        Dimension2D userDim = new Dimension2DDouble();
        if (this.fill.isRotatedWithShape() || this.shape == null) {
            usedBounds = userBounds;
        } else {
            AffineTransform transform = new AffineTransform(xform);
            transform.preConcatenate(AffineTransform.getTranslateInstance(-transform.getTranslateX(), -transform.getTranslateY()));
            Point2D.Double r2 = new Point2D.Double(1.0d, 0.0d);
            Point2D p1 = transform.transform(r2, r2);
            double rad = Math.atan2(p1.getY(), p1.getX());
            if (rad != 0.0d) {
                xform.rotate(-rad, userBounds.getCenterX(), userBounds.getCenterY());
            }
            usedBounds = AffineTransform.getRotateInstance(rad, userBounds.getCenterX(), userBounds.getCenterY()).createTransformedShape(this.shape).getBounds2D();
        }
        userDim.setSize(usedBounds.getWidth(), usedBounds.getHeight());
        xform.translate(usedBounds.getX(), usedBounds.getY());
        BufferedImage bi = getImage(usedBounds);
        if (this.fill.getStretch() != null) {
            TexturePaint tp = new TexturePaint(bi, new Rectangle2D.Double(0.0d, 0.0d, bi.getWidth(), bi.getHeight()));
            return tp.createContext(cm, deviceBounds, usedBounds, xform, hints);
        }
        if (this.fill.getScale() != null) {
            AffineTransform newXform = getTiledInstance(usedBounds, (AffineTransform) xform.clone());
            TexturePaint tp2 = new TexturePaint(bi, new Rectangle2D.Double(0.0d, 0.0d, bi.getWidth(), bi.getHeight()));
            return tp2.createContext(cm, deviceBounds, userBounds, newXform, hints);
        }
        return super.createContext(cm, deviceBounds, userBounds, xform, hints);
    }

    public BufferedImage getImage(Rectangle2D userBounds) {
        double d;
        BufferedImage bi = super.getImage();
        Insets2D insets = this.fill.getInsets();
        Insets2D stretch = this.fill.getStretch();
        if (((insets == null || INSETS_EMPTY.equals(insets)) && stretch == null) || userBounds == null || userBounds.isEmpty()) {
            return bi;
        }
        if (insets == null || INSETS_EMPTY.equals(insets)) {
            d = 100000.0d;
        } else {
            int width = bi.getWidth();
            int height = bi.getHeight();
            d = 100000.0d;
            BufferedImage bi2 = bi.getSubimage((int) ((Math.max(insets.left, 0.0d) / 100000.0d) * width), (int) ((Math.max(insets.top, 0.0d) / 100000.0d) * height), (int) ((((100000.0d - Math.max(insets.left, 0.0d)) - Math.max(insets.right, 0.0d)) / 100000.0d) * width), (int) ((((100000.0d - Math.max(insets.top, 0.0d)) - Math.max(insets.bottom, 0.0d)) / 100000.0d) * height));
            int addTop = (int) ((Math.max(-insets.top, 0.0d) / 100000.0d) * height);
            int addLeft = (int) ((Math.max(-insets.left, 0.0d) / 100000.0d) * width);
            int addBottom = (int) ((Math.max(-insets.bottom, 0.0d) / 100000.0d) * height);
            int addRight = (int) ((Math.max(-insets.right, 0.0d) / 100000.0d) * width);
            if (addTop > 0 || addLeft > 0 || addBottom > 0 || addRight > 0) {
                int[] buf = new int[bi2.getWidth() * bi2.getHeight()];
                bi2.getRGB(0, 0, bi2.getWidth(), bi2.getHeight(), buf, 0, bi2.getWidth());
                BufferedImage borderBi = new BufferedImage(bi2.getWidth() + addLeft + addRight, bi2.getHeight() + addTop + addBottom, bi2.getType());
                borderBi.setRGB(addLeft, addTop, bi2.getWidth(), bi2.getHeight(), buf, 0, bi2.getWidth());
                bi = borderBi;
            } else {
                bi = bi2;
            }
        }
        if (stretch != null) {
            Rectangle2D.Double r4 = new Rectangle2D.Double(0.0d, 0.0d, bi.getWidth(), bi.getHeight());
            Rectangle2D.Double r5 = new Rectangle2D.Double((stretch.left / d) * userBounds.getWidth(), (stretch.top / d) * userBounds.getHeight(), (((d - stretch.left) - stretch.right) / d) * userBounds.getWidth(), (((d - stretch.top) - stretch.bottom) / d) * userBounds.getHeight());
            BufferedImage stretchBi = new BufferedImage((int) userBounds.getWidth(), (int) userBounds.getHeight(), 2);
            Graphics2D g = stretchBi.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g.setComposite(AlphaComposite.Clear);
            g.fillRect(0, 0, stretchBi.getWidth(), stretchBi.getHeight());
            g.setComposite(AlphaComposite.SrcOver);
            AffineTransform at = new AffineTransform();
            at.translate(r5.getCenterX(), r5.getCenterY());
            at.scale(r5.getWidth() / r4.getWidth(), r5.getHeight() / r4.getHeight());
            at.translate(-r4.getCenterX(), -r4.getCenterY());
            g.drawRenderedImage(bi, at);
            g.dispose();
            return stretchBi;
        }
        return bi;
    }

    private AffineTransform getTiledInstance(Rectangle2D usedBounds, AffineTransform xform) {
        double alg_x;
        Dimension2D scale;
        double alg_y;
        BufferedImage bi = getImage();
        Dimension2D scale2 = this.fill.getScale();
        if (scale2 == null) {
            throw new AssertionError();
        }
        double img_w = (bi.getWidth() * (scale2.getWidth() == 0.0d ? 1.0d : scale2.getWidth())) / this.flipX;
        double img_h = (bi.getHeight() * (scale2.getHeight() == 0.0d ? 1.0d : scale2.getHeight())) / this.flipY;
        PaintStyle.TextureAlignment ta = this.fill.getAlignment();
        double usr_w = usedBounds.getWidth();
        double usr_h = usedBounds.getHeight();
        switch (ta == null ? PaintStyle.TextureAlignment.TOP_LEFT : ta) {
            case BOTTOM:
                double alg_y2 = usr_w - img_w;
                double alg_x2 = alg_y2 / 2.0d;
                double alg_y3 = usr_h - img_h;
                alg_x = alg_x2;
                scale = scale2;
                alg_y = alg_y3;
                break;
            case BOTTOM_LEFT:
                double alg_y4 = usr_h - img_h;
                alg_x = 0.0d;
                scale = scale2;
                alg_y = alg_y4;
                break;
            case BOTTOM_RIGHT:
                double alg_y5 = usr_w - img_w;
                double alg_y6 = usr_h - img_h;
                alg_x = alg_y5;
                scale = scale2;
                alg_y = alg_y6;
                break;
            case CENTER:
                double alg_x3 = usr_w - img_w;
                double alg_y7 = (usr_h - img_h) / 2.0d;
                alg_x = alg_x3 / 2.0d;
                scale = scale2;
                alg_y = alg_y7;
                break;
            case LEFT:
                double alg_y8 = (usr_h - img_h) / 2.0d;
                alg_x = 0.0d;
                scale = scale2;
                alg_y = alg_y8;
                break;
            case RIGHT:
                double alg_y9 = usr_w - img_w;
                double alg_y10 = (usr_h - img_h) / 2.0d;
                alg_x = alg_y9;
                scale = scale2;
                alg_y = alg_y10;
                break;
            case TOP:
                double alg_y11 = usr_w - img_w;
                double alg_x4 = alg_y11 / 2.0d;
                alg_x = alg_x4;
                scale = scale2;
                alg_y = 0.0d;
                break;
            case TOP_LEFT:
            default:
                alg_x = 0.0d;
                scale = scale2;
                alg_y = 0.0d;
                break;
            case TOP_RIGHT:
                double alg_x5 = usr_w - img_w;
                alg_x = alg_x5;
                scale = scale2;
                alg_y = 0.0d;
                break;
        }
        xform.translate(alg_x, alg_y);
        Point2D offset = this.fill.getOffset();
        if (offset != null) {
            xform.translate(offset.getX(), offset.getY());
        }
        xform.scale(scale.getWidth() / (this.isBitmapSrc ? this.flipX : 1.0d), scale.getHeight() / (this.isBitmapSrc ? this.flipY : 1.0d));
        return xform;
    }

    public ImageRenderer getImageRenderer() {
        return this.imgRdr;
    }

    public PaintStyle.TexturePaint getFill() {
        return this.fill;
    }

    public Shape getAwtShape() {
        return this.shape;
    }
}
