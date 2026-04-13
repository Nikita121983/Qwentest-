package org.apache.poi.sl.draw;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.RectAlign;

/* loaded from: classes10.dex */
public class DrawPictureShape extends DrawSimpleShape {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) DrawPictureShape.class);

    public DrawPictureShape(PictureShape<?, ?> shape) {
        super(shape);
    }

    @Override // org.apache.poi.sl.draw.DrawShape, org.apache.poi.sl.draw.Drawable
    public void drawContent(Graphics2D graphics) {
        PictureShape<?, ?> ps = getShape();
        Rectangle2D anchor = getAnchor(graphics, ps);
        Insets insets = ps.getClipping();
        PictureData[] pics = {ps.getAlternativePictureData(), ps.getPictureData()};
        for (PictureData data : pics) {
            if (data != null) {
                try {
                    byte[] dataBytes = data.getData();
                    PictureType type = PictureType.valueOf(FileMagic.valueOf(dataBytes));
                    String ct = type == PictureType.UNKNOWN ? data.getContentType() : type.getContentType();
                    ImageRenderer renderer = getImageRenderer(graphics, ct);
                    if (renderer.canRender(ct)) {
                        renderer.loadImage(dataBytes, ct);
                        renderer.drawImage(graphics, anchor, insets);
                        return;
                    }
                    continue;
                } catch (IOException e) {
                    LOG.atError().withThrowable(e).log("image can't be loaded/rendered.");
                }
            }
        }
    }

    public static ImageRenderer getImageRenderer(Graphics2D graphics, String contentType) {
        ImageRenderer ir;
        ImageRenderer renderer = graphics != null ? (ImageRenderer) graphics.getRenderingHint(Drawable.IMAGE_RENDERER) : null;
        if (renderer != null && renderer.canRender(contentType)) {
            return renderer;
        }
        BitmapImageRenderer fallback = new BitmapImageRenderer();
        if (fallback.canRender(contentType)) {
            return fallback;
        }
        ClassLoader cl = DrawPictureShape.class.getClassLoader();
        Iterator<ImageRenderer> iter = ServiceLoader.load(ImageRenderer.class, cl).iterator();
        while (true) {
            try {
                ir = iter.next();
            } catch (NoSuchElementException e) {
                LOG.atWarn().log("No suitable image renderer found for content-type '{}' - include poi-scratchpad (for wmf/emf) or poi-ooxml (for svg) jars - svgs/batik doesn't work on the module-path!", contentType);
                return fallback;
            } catch (Exception e2) {
            } catch (ServiceConfigurationError e3) {
            }
            if (ir.canRender(contentType)) {
                return ir;
            }
        }
    }

    @Override // org.apache.poi.sl.draw.DrawSimpleShape
    protected Paint getFillPaint(Graphics2D graphics) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.sl.draw.DrawSimpleShape, org.apache.poi.sl.draw.DrawShape
    public PictureShape<?, ?> getShape() {
        return (PictureShape) this.shape;
    }

    public void resize() {
        PictureShape<?, ?> ps = getShape();
        Dimension dim = ps.getPictureData().getImageDimension();
        Rectangle2D origRect = ps.getAnchor();
        double x = origRect.getX();
        double y = origRect.getY();
        double w = dim.getWidth();
        double h = dim.getHeight();
        ps.setAnchor(new Rectangle2D.Double(x, y, w, h));
    }

    public void resize(Rectangle2D target) {
        resize(target, RectAlign.CENTER);
    }

    public void resize(Rectangle2D target, RectAlign align) {
        double w;
        double h;
        double x;
        double y;
        PictureShape<?, ?> ps = getShape();
        Dimension dim = ps.getPictureData().getImageDimension();
        if (dim.width <= 0 || dim.height <= 0) {
            ps.setAnchor(target);
            return;
        }
        double w2 = target.getWidth();
        double h2 = target.getHeight();
        double sx = w2 / dim.width;
        double sy = h2 / dim.height;
        double dx = 0.0d;
        double dy = 0.0d;
        if (sx > sy) {
            double w3 = dim.width * sy;
            dx = target.getWidth() - w3;
            w = w3;
            h = h2;
        } else if (sy > sx) {
            double h3 = sx * dim.height;
            dy = target.getHeight() - h3;
            w = w2;
            h = h3;
        } else {
            ps.setAnchor(target);
            return;
        }
        double x2 = target.getX();
        double y2 = target.getY();
        switch (align) {
            case TOP:
                x = x2 + (dx / 2.0d);
                y = y2;
                break;
            case TOP_RIGHT:
                x = x2 + dx;
                y = y2;
                break;
            case RIGHT:
                x = x2 + dx;
                y = y2 + (dy / 2.0d);
                break;
            case BOTTOM_RIGHT:
                x = x2 + dx;
                y = y2 + dy;
                break;
            case BOTTOM:
                x = x2 + (dx / 2.0d);
                y = y2 + dy;
                break;
            case BOTTOM_LEFT:
                x = x2;
                y = y2 + dy;
                break;
            case LEFT:
                x = x2;
                y = y2 + (dy / 2.0d);
                break;
            case TOP_LEFT:
                x = x2;
                y = y2;
                break;
            default:
                x = x2 + (dx / 2.0d);
                y = y2 + (dy / 2.0d);
                break;
        }
        ps.setAnchor(new Rectangle2D.Double(x, y, w, h));
    }
}
