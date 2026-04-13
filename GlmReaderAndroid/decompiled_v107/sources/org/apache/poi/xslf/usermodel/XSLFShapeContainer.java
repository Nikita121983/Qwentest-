package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.sl.usermodel.ConnectorShape;
import org.apache.poi.sl.usermodel.FreeformShape;
import org.apache.poi.sl.usermodel.GroupShape;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.PictureShape;
import org.apache.poi.sl.usermodel.ShapeContainer;
import org.apache.poi.sl.usermodel.TextBox;

/* loaded from: classes10.dex */
public interface XSLFShapeContainer extends ShapeContainer<XSLFShape, XSLFTextParagraph> {
    void clear();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    AutoShape<XSLFShape, XSLFTextParagraph> createAutoShape();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    ConnectorShape<XSLFShape, XSLFTextParagraph> createConnector();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    FreeformShape<XSLFShape, XSLFTextParagraph> createFreeform();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    GroupShape<XSLFShape, XSLFTextParagraph> createGroup();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    PictureShape<XSLFShape, XSLFTextParagraph> createPicture(PictureData pictureData);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.apache.poi.sl.usermodel.ShapeContainer
    TextBox<XSLFShape, XSLFTextParagraph> createTextBox();
}
