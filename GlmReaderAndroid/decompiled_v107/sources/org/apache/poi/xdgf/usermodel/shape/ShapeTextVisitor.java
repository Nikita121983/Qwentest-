package org.apache.poi.xdgf.usermodel.shape;

import java.awt.geom.AffineTransform;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public class ShapeTextVisitor extends ShapeVisitor {
    protected StringBuilder text = new StringBuilder();

    /* loaded from: classes10.dex */
    public static class TextAcceptor implements ShapeVisitorAcceptor {
        @Override // org.apache.poi.xdgf.usermodel.shape.ShapeVisitorAcceptor
        public boolean accept(XDGFShape shape) {
            return shape.hasText();
        }
    }

    @Override // org.apache.poi.xdgf.usermodel.shape.ShapeVisitor
    protected ShapeVisitorAcceptor getAcceptor() {
        return new TextAcceptor();
    }

    @Override // org.apache.poi.xdgf.usermodel.shape.ShapeVisitor
    public void visit(XDGFShape shape, AffineTransform globalTransform, int level) {
        this.text.append(shape.getText().getTextContent().trim());
        this.text.append('\n');
    }

    public String getText() {
        return this.text.toString();
    }
}
