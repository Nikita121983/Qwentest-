package org.apache.poi.sl.draw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.sl.usermodel.TextShape;

/* loaded from: classes10.dex */
public class DrawTextShape extends DrawSimpleShape {
    public DrawTextShape(TextShape<?, ?> shape) {
        super(shape);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x0031 */
    @Override // org.apache.poi.sl.draw.DrawShape, org.apache.poi.sl.draw.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void drawContent(java.awt.Graphics2D r32) {
        /*
            Method dump skipped, instructions count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.sl.draw.DrawTextShape.drawContent(java.awt.Graphics2D):void");
    }

    public double drawParagraphs(Graphics2D graphics, double x, double y) {
        double d;
        double d2;
        double y2;
        DrawFactory fact;
        Graphics2D graphics2D = graphics;
        DrawFactory fact2 = DrawFactory.getInstance(graphics2D);
        Iterator<P> it = getShape().iterator();
        boolean isFirstLine = true;
        int autoNbrIdx = 0;
        double y3 = y;
        while (it.hasNext()) {
            TextParagraph<?, ?, ? extends TextRun> p = (TextParagraph) it.next();
            DrawTextParagraph dp = fact2.getDrawable(p);
            TextParagraph.BulletStyle bs = p.getBulletStyle();
            if (bs == null || bs.getAutoNumberingScheme() == null) {
                autoNbrIdx = -1;
            } else {
                Integer startAt = bs.getAutoNumberingStartAt();
                if (startAt == null) {
                    startAt = 1;
                }
                if (startAt.intValue() > autoNbrIdx) {
                    autoNbrIdx = startAt.intValue();
                }
            }
            dp.setAutoNumberingIdx(autoNbrIdx);
            dp.breakText(graphics2D);
            if (isFirstLine) {
                d = 0.01d;
                y2 = y3 + dp.getFirstLineLeading();
                d2 = 0.0d;
            } else {
                d = 0.01d;
                Double spaceBefore = p.getSpaceBefore();
                if (spaceBefore == null) {
                    spaceBefore = Double.valueOf(0.0d);
                }
                if (spaceBefore.doubleValue() > 0.0d) {
                    d2 = 0.0d;
                    y2 = y3 + (spaceBefore.doubleValue() * 0.01d * dp.getFirstLineHeight());
                } else {
                    d2 = 0.0d;
                    y2 = y3 + (-spaceBefore.doubleValue());
                }
            }
            dp.setPosition(x, y2);
            dp.setFirstParagraph(isFirstLine);
            isFirstLine = false;
            dp.draw(graphics2D);
            y3 = y2 + dp.getY();
            if (!it.hasNext()) {
                fact = fact2;
            } else {
                Double spaceAfter = p.getSpaceAfter();
                if (spaceAfter == null) {
                    spaceAfter = Double.valueOf(d2);
                }
                if (spaceAfter.doubleValue() > d2) {
                    fact = fact2;
                    y3 += spaceAfter.doubleValue() * d * dp.getLastLineHeight();
                } else {
                    fact = fact2;
                    y3 += -spaceAfter.doubleValue();
                }
            }
            autoNbrIdx++;
            graphics2D = graphics;
            fact2 = fact;
        }
        return y3 - y;
    }

    public double getTextHeight() {
        return getTextHeight(null);
    }

    public double getTextHeight(Graphics2D oldGraphics) {
        BufferedImage img = new BufferedImage(1, 1, 1);
        Graphics2D graphics = img.createGraphics();
        if (oldGraphics != null) {
            graphics.addRenderingHints(oldGraphics.getRenderingHints());
            graphics.setTransform(oldGraphics.getTransform());
        }
        return drawParagraphs(graphics, 0.0d, 0.0d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.sl.draw.DrawSimpleShape, org.apache.poi.sl.draw.DrawShape
    public TextShape<?, ? extends TextParagraph<?, ?, ? extends TextRun>> getShape() {
        return (TextShape) this.shape;
    }
}
