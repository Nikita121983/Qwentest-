package com.microsoft.schemas.office.drawing.x2008.diagram.impl;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingShapeProps;

/* loaded from: classes.dex */
public class CTShapeNonVisualImpl extends XmlComplexContentImpl implements CTShapeNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "cNvPr"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "cNvSpPr")};
    private static final long serialVersionUID = 1;

    public CTShapeNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual
    public CTNonVisualDrawingShapeProps getCNvSpPr() {
        CTNonVisualDrawingShapeProps cTNonVisualDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingShapeProps target = (CTNonVisualDrawingShapeProps) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualDrawingShapeProps = target == null ? null : target;
        }
        return cTNonVisualDrawingShapeProps;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual
    public void setCNvSpPr(CTNonVisualDrawingShapeProps cNvSpPr) {
        generatedSetterHelperImpl(cNvSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTShapeNonVisual
    public CTNonVisualDrawingShapeProps addNewCNvSpPr() {
        CTNonVisualDrawingShapeProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingShapeProps) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }
}
