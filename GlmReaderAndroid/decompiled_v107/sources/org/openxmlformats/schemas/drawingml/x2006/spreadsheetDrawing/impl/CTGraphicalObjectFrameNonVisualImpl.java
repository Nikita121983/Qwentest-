package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual;

/* loaded from: classes11.dex */
public class CTGraphicalObjectFrameNonVisualImpl extends XmlComplexContentImpl implements CTGraphicalObjectFrameNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvGraphicFramePr")};
    private static final long serialVersionUID = 1;

    public CTGraphicalObjectFrameNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual
    public CTNonVisualGraphicFrameProperties getCNvGraphicFramePr() {
        CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualGraphicFrameProperties target = (CTNonVisualGraphicFrameProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualGraphicFrameProperties = target == null ? null : target;
        }
        return cTNonVisualGraphicFrameProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual
    public void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cNvGraphicFramePr) {
        generatedSetterHelperImpl(cNvGraphicFramePr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrameNonVisual
    public CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr() {
        CTNonVisualGraphicFrameProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualGraphicFrameProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }
}
