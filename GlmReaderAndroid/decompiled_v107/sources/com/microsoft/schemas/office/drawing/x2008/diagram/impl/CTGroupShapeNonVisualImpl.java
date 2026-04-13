package com.microsoft.schemas.office.drawing.x2008.diagram.impl;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;

/* loaded from: classes.dex */
public class CTGroupShapeNonVisualImpl extends XmlComplexContentImpl implements CTGroupShapeNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "cNvPr"), new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "cNvGrpSpPr")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual
    public CTNonVisualGroupDrawingShapeProps getCNvGrpSpPr() {
        CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualGroupDrawingShapeProps target = (CTNonVisualGroupDrawingShapeProps) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualGroupDrawingShapeProps = target == null ? null : target;
        }
        return cTNonVisualGroupDrawingShapeProps;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual
    public void setCNvGrpSpPr(CTNonVisualGroupDrawingShapeProps cNvGrpSpPr) {
        generatedSetterHelperImpl(cNvGrpSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShapeNonVisual
    public CTNonVisualGroupDrawingShapeProps addNewCNvGrpSpPr() {
        CTNonVisualGroupDrawingShapeProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualGroupDrawingShapeProps) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }
}
