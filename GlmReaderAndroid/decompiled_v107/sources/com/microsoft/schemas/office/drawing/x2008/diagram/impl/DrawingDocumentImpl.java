package com.microsoft.schemas.office.drawing.x2008.diagram.impl;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTDrawing;
import com.microsoft.schemas.office.drawing.x2008.diagram.DrawingDocument;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class DrawingDocumentImpl extends XmlComplexContentImpl implements DrawingDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "drawing")};
    private static final long serialVersionUID = 1;

    public DrawingDocumentImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.DrawingDocument
    public CTDrawing getDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            CTDrawing target = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTDrawing = target == null ? null : target;
        }
        return cTDrawing;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.DrawingDocument
    public void setDrawing(CTDrawing drawing) {
        generatedSetterHelperImpl(drawing, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.DrawingDocument
    public CTDrawing addNewDrawing() {
        CTDrawing target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
