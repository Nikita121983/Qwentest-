package org.apache.poi.xdgf.usermodel.shape;

import java.awt.geom.AffineTransform;
import org.apache.poi.xdgf.usermodel.XDGFShape;

/* loaded from: classes10.dex */
public abstract class ShapeVisitor {
    protected ShapeVisitorAcceptor _acceptor = getAcceptor();

    public abstract void visit(XDGFShape xDGFShape, AffineTransform affineTransform, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$getAcceptor$0(XDGFShape shape) {
        return !shape.isDeleted();
    }

    protected ShapeVisitorAcceptor getAcceptor() {
        return new ShapeVisitorAcceptor() { // from class: org.apache.poi.xdgf.usermodel.shape.ShapeVisitor$$ExternalSyntheticLambda0
            @Override // org.apache.poi.xdgf.usermodel.shape.ShapeVisitorAcceptor
            public final boolean accept(XDGFShape xDGFShape) {
                return ShapeVisitor.lambda$getAcceptor$0(xDGFShape);
            }
        };
    }

    public void setAcceptor(ShapeVisitorAcceptor acceptor) {
        this._acceptor = acceptor;
    }

    public boolean accept(XDGFShape shape) {
        return this._acceptor.accept(shape);
    }
}
