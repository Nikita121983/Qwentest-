package org.apache.poi.sl.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.Outline;
import org.apache.poi.sl.draw.geom.Path;
import org.apache.poi.sl.draw.geom.PathIf;
import org.apache.poi.sl.usermodel.LineDecoration;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shadow;
import org.apache.poi.sl.usermodel.SimpleShape;
import org.apache.poi.util.Units;

/* loaded from: classes10.dex */
public class DrawSimpleShape extends DrawShape {
    private static final double DECO_SIZE_POW = 1.5d;

    public DrawSimpleShape(SimpleShape<?, ?> shape) {
        super(shape);
    }

    @Override // org.apache.poi.sl.draw.DrawShape, org.apache.poi.sl.draw.Drawable
    public void draw(final Graphics2D graphics) {
        Collection<Outline> elems;
        Paint fill;
        Collection<Outline> elems2;
        if (getAnchor(graphics, getShape()) == null) {
            return;
        }
        Paint oldPaint = graphics.getPaint();
        Stroke oldStroke = graphics.getStroke();
        Color oldColor = graphics.getColor();
        Paint fill2 = getFillPaint(graphics);
        Paint line = getLinePaint(graphics);
        BasicStroke stroke = getStroke();
        graphics.setStroke(stroke);
        Collection<Outline> elems3 = computeOutlines(graphics);
        drawShadow(graphics, elems3, fill2, line);
        if (fill2 != null) {
            final Path2D.Double r9 = new Path2D.Double();
            graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, r9);
            Consumer<PaintStyle.PaintModifier> fun = new Consumer() { // from class: org.apache.poi.sl.draw.DrawSimpleShape$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    DrawSimpleShape.this.m2510lambda$draw$0$orgapachepoisldrawDrawSimpleShape(graphics, r9, (PaintStyle.PaintModifier) obj);
                }
            };
            PaintStyle.PaintModifier pm = null;
            for (Outline o : elems3) {
                PathIf path = o.getPath();
                if (!path.isFilled()) {
                    fill = fill2;
                    elems2 = elems3;
                } else {
                    PaintStyle.PaintModifier pmOld = pm;
                    pm = path.getFill();
                    if (pmOld != null && pmOld != pm) {
                        fun.accept(pmOld);
                        r9.reset();
                        fill = fill2;
                        elems2 = elems3;
                    } else {
                        fill = fill2;
                        elems2 = elems3;
                        r9.append(o.getOutline(), false);
                    }
                }
                fill2 = fill;
                elems3 = elems2;
            }
            elems = elems3;
            if (r9.getCurrentPoint() != null) {
                fun.accept(pm);
            }
        } else {
            elems = elems3;
        }
        drawContent(graphics);
        if (line != null) {
            graphics.setPaint(line);
            graphics.setStroke(stroke);
            for (Outline o2 : elems) {
                if (o2.getPath().isStroked()) {
                    Shape s = o2.getOutline();
                    graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, s);
                    graphics.draw(s);
                }
            }
        }
        drawDecoration(graphics, line, stroke);
        graphics.setColor(oldColor);
        graphics.setPaint(oldPaint);
        graphics.setStroke(oldStroke);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fillArea, reason: merged with bridge method [inline-methods] */
    public void m2510lambda$draw$0$orgapachepoisldrawDrawSimpleShape(Graphics2D graphics, PaintStyle.PaintModifier pm, Path2D area) {
        SimpleShape<?, ?> ss = getShape();
        PaintStyle ps = ss.getFillStyle().getPaint();
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint(ss);
        Paint fillMod = drawPaint.getPaint(graphics, ps, pm);
        if (fillMod != null) {
            graphics.setPaint(fillMod);
            DrawPaint.fillPaintWorkaround(graphics, area);
        }
    }

    protected Paint getFillPaint(Graphics2D graphics) {
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint(getShape());
        return drawPaint.getPaint(graphics, getShape().getFillStyle().getPaint());
    }

    protected Paint getLinePaint(Graphics2D graphics) {
        DrawPaint drawPaint = DrawFactory.getInstance(graphics).getPaint(getShape());
        return drawPaint.getPaint(graphics, getShape().getStrokeStyle().getPaint());
    }

    protected void drawDecoration(Graphics2D graphics, Paint line, BasicStroke stroke) {
        if (line == null) {
            return;
        }
        graphics.setPaint(line);
        List<Outline> lst = new ArrayList<>();
        LineDecoration deco = getShape().getLineDecoration();
        Outline head = getHeadDecoration(graphics, deco, stroke);
        if (head != null) {
            lst.add(head);
        }
        Outline tail = getTailDecoration(graphics, deco, stroke);
        if (tail != null) {
            lst.add(tail);
        }
        for (Outline o : lst) {
            Shape s = o.getOutline();
            PathIf p = o.getPath();
            graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, s);
            if (p.isFilled()) {
                graphics.fill(s);
            }
            if (p.isStroked()) {
                graphics.draw(s);
            }
        }
    }

    protected Outline getTailDecoration(Graphics2D graphics, LineDecoration deco, BasicStroke stroke) {
        double alpha;
        if (deco == null || stroke == null) {
            return null;
        }
        LineDecoration.DecorationSize tailLength = deco.getTailLength();
        if (tailLength == null) {
            tailLength = LineDecoration.DecorationSize.MEDIUM;
        }
        LineDecoration.DecorationSize tailWidth = deco.getTailWidth();
        if (tailWidth == null) {
            tailWidth = LineDecoration.DecorationSize.MEDIUM;
        }
        double lineWidth = Math.max(2.5d, stroke.getLineWidth());
        Rectangle2D anchor = getAnchor(graphics, getShape());
        double x2 = 0.0d;
        double y2 = 0.0d;
        if (anchor == null) {
            alpha = 0.0d;
        } else {
            x2 = anchor.getX() + anchor.getWidth();
            y2 = anchor.getY() + anchor.getHeight();
            double alpha2 = Math.atan(anchor.getHeight() / anchor.getWidth());
            alpha = alpha2;
        }
        AffineTransform at = new AffineTransform();
        Shape tailShape = null;
        Path p = null;
        double scaleY = Math.pow(DECO_SIZE_POW, tailWidth.ordinal() + 1.0d);
        double scaleX = Math.pow(DECO_SIZE_POW, tailLength.ordinal() + 1.0d);
        LineDecoration.DecorationShape tailShapeEnum = deco.getTailShape();
        if (tailShapeEnum == null) {
            return null;
        }
        switch (tailShapeEnum) {
            case OVAL:
                Path p2 = new Path();
                Shape tailShape2 = new Ellipse2D.Double(0.0d, 0.0d, lineWidth * scaleX, lineWidth * scaleY);
                Rectangle2D bounds = tailShape2.getBounds2D();
                at.translate(x2 - (bounds.getWidth() / 2.0d), y2 - (bounds.getHeight() / 2.0d));
                at.rotate(alpha, (bounds.getWidth() / 2.0d) + bounds.getX(), bounds.getY() + (bounds.getHeight() / 2.0d));
                p = p2;
                tailShape = tailShape2;
                break;
            case STEALTH:
            case ARROW:
                p = new Path();
                p.setFill(PaintStyle.PaintModifier.NONE);
                p.setStroke(true);
                Shape shape = new Path2D.Double();
                shape.moveTo((-lineWidth) * scaleX, ((-lineWidth) * scaleY) / 2.0d);
                shape.lineTo(0.0d, 0.0d);
                shape.lineTo((-lineWidth) * scaleX, (lineWidth * scaleY) / 2.0d);
                tailShape = shape;
                at.translate(x2, y2);
                at.rotate(alpha);
                break;
            case TRIANGLE:
                Path p3 = new Path();
                Shape shape2 = new Path2D.Double();
                shape2.moveTo((-lineWidth) * scaleX, ((-lineWidth) * scaleY) / 2.0d);
                shape2.lineTo(0.0d, 0.0d);
                shape2.lineTo((-lineWidth) * scaleX, (lineWidth * scaleY) / 2.0d);
                shape2.closePath();
                tailShape = shape2;
                at.translate(x2, y2);
                at.rotate(alpha);
                p = p3;
                break;
        }
        if (tailShape != null) {
            tailShape = at.createTransformedShape(tailShape);
        }
        if (tailShape == null) {
            return null;
        }
        return new Outline(tailShape, p);
    }

    protected Outline getHeadDecoration(Graphics2D graphics, LineDecoration deco, BasicStroke stroke) {
        double alpha;
        if (deco == null || stroke == null) {
            return null;
        }
        LineDecoration.DecorationSize headLength = deco.getHeadLength();
        if (headLength == null) {
            headLength = LineDecoration.DecorationSize.MEDIUM;
        }
        LineDecoration.DecorationSize headWidth = deco.getHeadWidth();
        if (headWidth == null) {
            headWidth = LineDecoration.DecorationSize.MEDIUM;
        }
        double lineWidth = Math.max(2.5d, stroke.getLineWidth());
        Rectangle2D anchor = getAnchor(graphics, getShape());
        double x1 = 0.0d;
        double y1 = 0.0d;
        if (anchor == null) {
            alpha = 0.0d;
        } else {
            x1 = anchor.getX();
            y1 = anchor.getY();
            double alpha2 = Math.atan(anchor.getHeight() / anchor.getWidth());
            alpha = alpha2;
        }
        AffineTransform at = new AffineTransform();
        Shape headShape = null;
        Path p = null;
        double scaleY = Math.pow(DECO_SIZE_POW, headWidth.ordinal() + 1.0d);
        double scaleX = Math.pow(DECO_SIZE_POW, headLength.ordinal() + 1.0d);
        LineDecoration.DecorationShape headShapeEnum = deco.getHeadShape();
        if (headShapeEnum == null) {
            return null;
        }
        switch (headShapeEnum) {
            case OVAL:
                Path p2 = new Path();
                Shape headShape2 = new Ellipse2D.Double(0.0d, 0.0d, lineWidth * scaleX, lineWidth * scaleY);
                Rectangle2D bounds = headShape2.getBounds2D();
                at.translate(x1 - (bounds.getWidth() / 2.0d), y1 - (bounds.getHeight() / 2.0d));
                at.rotate(alpha, (bounds.getWidth() / 2.0d) + bounds.getX(), bounds.getY() + (bounds.getHeight() / 2.0d));
                p = p2;
                headShape = headShape2;
                break;
            case STEALTH:
            case ARROW:
                p = new Path();
                p.setFill(PaintStyle.PaintModifier.NONE);
                p.setStroke(true);
                Shape shape = new Path2D.Double();
                shape.moveTo(lineWidth * scaleX, ((-lineWidth) * scaleY) / 2.0d);
                shape.lineTo(0.0d, 0.0d);
                shape.lineTo(lineWidth * scaleX, (lineWidth * scaleY) / 2.0d);
                headShape = shape;
                at.translate(x1, y1);
                at.rotate(alpha);
                break;
            case TRIANGLE:
                Path p3 = new Path();
                Shape shape2 = new Path2D.Double();
                shape2.moveTo(lineWidth * scaleX, ((-lineWidth) * scaleY) / 2.0d);
                shape2.lineTo(0.0d, 0.0d);
                shape2.lineTo(lineWidth * scaleX, (lineWidth * scaleY) / 2.0d);
                shape2.closePath();
                headShape = shape2;
                at.translate(x1, y1);
                at.rotate(alpha);
                p = p3;
                break;
        }
        if (headShape != null) {
            headShape = at.createTransformedShape(headShape);
        }
        if (headShape == null) {
            return null;
        }
        return new Outline(headShape, p);
    }

    public BasicStroke getStroke() {
        return getStroke(getShape().getStrokeStyle());
    }

    protected void drawShadow(Graphics2D graphics, Collection<Outline> outlines, Paint fill, Paint line) {
        Shadow<?, ?> shadow = getShape().getShadow();
        if (shadow != null) {
            if (fill == null && line == null) {
                return;
            }
            PaintStyle.SolidPaint shadowPaint = shadow.getFillStyle();
            Color shadowColor = DrawPaint.applyColorTransform(shadowPaint.getSolidColor());
            double shapeRotation = getShape().getRotation();
            if (getShape().getFlipVertical()) {
                shapeRotation += 180.0d;
            }
            double angle = shadow.getAngle() - shapeRotation;
            double dist = shadow.getDistance();
            double dx = Math.cos(Math.toRadians(angle)) * dist;
            double dy = Math.sin(Math.toRadians(angle)) * dist;
            graphics.translate(dx, dy);
            for (Outline o : outlines) {
                Shadow<?, ?> shadow2 = shadow;
                Shape s = o.getOutline();
                PathIf p = o.getPath();
                PaintStyle.SolidPaint shadowPaint2 = shadowPaint;
                graphics.setRenderingHint(Drawable.GRADIENT_SHAPE, s);
                graphics.setPaint(shadowColor);
                if (fill != null && p.isFilled()) {
                    DrawPaint.fillPaintWorkaround(graphics, s);
                } else if (line != null && p.isStroked()) {
                    graphics.draw(s);
                }
                shadow = shadow2;
                shadowPaint = shadowPaint2;
            }
            graphics.translate(-dx, -dy);
        }
    }

    protected Collection<Outline> computeOutlines(Graphics2D graphics) {
        double w;
        double w2;
        double scaleY;
        SimpleShape<?, ?> sh = getShape();
        List<Outline> lst = new ArrayList<>();
        CustomGeometry geom = sh.getGeometry();
        if (geom == null) {
            return lst;
        }
        Rectangle2D anchor = getAnchor(graphics, sh);
        if (anchor == null) {
            return lst;
        }
        for (Iterator<PathIf> it = geom.iterator(); it.hasNext(); it = it) {
            PathIf p = it.next();
            double w3 = p.getW();
            double h = p.getH();
            if (w3 == -1.0d) {
                w = Units.toEMU(anchor.getWidth());
                w2 = Units.toPoints(1L);
            } else {
                double scaleX = anchor.getWidth();
                if (scaleX == 0.0d) {
                    w = w3;
                    w2 = 1.0d;
                } else {
                    double scaleX2 = anchor.getWidth();
                    w = w3;
                    w2 = scaleX2 / w3;
                }
            }
            if (h == -1.0d) {
                h = Units.toEMU(anchor.getHeight());
                scaleY = Units.toPoints(1L);
            } else {
                double scaleY2 = anchor.getHeight();
                if (scaleY2 == 0.0d) {
                    scaleY = 1.0d;
                } else {
                    double scaleY3 = anchor.getHeight();
                    scaleY = scaleY3 / h;
                }
            }
            Context ctx = new Context(geom, new Rectangle2D.Double(0.0d, 0.0d, w, h), sh);
            Path2D.Double path = p.getPath(ctx);
            AffineTransform at = new AffineTransform();
            CustomGeometry geom2 = geom;
            Rectangle2D anchor2 = anchor;
            at.translate(anchor.getX(), anchor2.getY());
            at.scale(w2, scaleY);
            Shape canvasShape = at.createTransformedShape(path);
            lst.add(new Outline(canvasShape, p));
            geom = geom2;
            anchor = anchor2;
        }
        return lst;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.sl.draw.DrawShape
    public SimpleShape<?, ?> getShape() {
        return (SimpleShape) this.shape;
    }
}
