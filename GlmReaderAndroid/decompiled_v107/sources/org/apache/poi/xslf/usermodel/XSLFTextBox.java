package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

/* loaded from: classes10.dex */
public class XSLFTextBox extends XSLFAutoShape implements TextBox<XSLFShape, XSLFTextParagraph> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public XSLFTextBox(CTShape shape, XSLFSheet sheet) {
        super(shape, sheet);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CTShape prototype(int shapeId) {
        CTShape ct = CTShape.Factory.newInstance();
        CTShapeNonVisual nvSpPr = ct.addNewNvSpPr();
        CTNonVisualDrawingProps cnv = nvSpPr.addNewCNvPr();
        cnv.setName("TextBox " + shapeId);
        cnv.setId(shapeId);
        nvSpPr.addNewCNvSpPr().setTxBox(true);
        nvSpPr.addNewNvPr();
        CTShapeProperties spPr = ct.addNewSpPr();
        CTPresetGeometry2D prst = spPr.addNewPrstGeom();
        prst.setPrst(STShapeType.RECT);
        prst.addNewAvLst();
        XDDFTextBody body = new XDDFTextBody(null);
        body.initialize();
        ct.setTxBody(body.getXmlObject());
        return ct;
    }
}
