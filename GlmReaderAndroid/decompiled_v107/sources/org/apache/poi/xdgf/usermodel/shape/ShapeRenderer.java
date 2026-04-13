package org.apache.poi.xdgf.usermodel.shape;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.XDGFText;
import org.apache.poi.xdgf.usermodel.section.GeometrySection;

/* loaded from: classes10.dex */
public class ShapeRenderer extends ShapeVisitor {
    protected Graphics2D _graphics;

    public ShapeRenderer() {
        this._graphics = null;
    }

    public ShapeRenderer(Graphics2D g) {
        this._graphics = g;
    }

    public void setGraphics(Graphics2D g) {
        this._graphics = g;
    }

    @Override // org.apache.poi.xdgf.usermodel.shape.ShapeVisitor
    public void visit(XDGFShape shape, AffineTransform globalTransform, int level) {
        AffineTransform savedTr = this._graphics.getTransform();
        this._graphics.transform(globalTransform);
        drawPath(shape);
        drawText(shape);
        this._graphics.setTransform(savedTr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Path2D drawPath(XDGFShape shape) {
        Path2D path = null;
        for (GeometrySection geometrySection : shape.getGeometrySections()) {
            if (!geometrySection.getNoShow().booleanValue()) {
                if (path == null) {
                    path = drawPath(geometrySection, shape);
                } else {
                    drawPath(geometrySection, shape);
                }
            }
        }
        return path;
    }

    private Path2D drawPath(GeometrySection geometrySection, XDGFShape shape) {
        Path2D.Double path = geometrySection.getPath(shape);
        if (path != null) {
            this._graphics.setColor(shape.getLineColor());
            this._graphics.setStroke(shape.getStroke());
            this._graphics.draw(path);
        }
        return path;
    }

    protected void drawText(XDGFShape shape) {
        XDGFText text = shape.getText();
        if (text != null) {
            if (text.getTextContent().equals("Header")) {
                text.getTextBounds();
            }
            Font oldFont = this._graphics.getFont();
            this._graphics.setFont(oldFont.deriveFont(shape.getFontSize().floatValue()));
            this._graphics.setColor(shape.getFontColor());
            text.draw(this._graphics);
            this._graphics.setFont(oldFont);
        }
    }
}
