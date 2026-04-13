package com.microsoft.schemas.office.drawing.x2008.diagram.impl;

import com.microsoft.schemas.office.drawing.x2008.diagram.CTDrawing;
import com.microsoft.schemas.office.drawing.x2008.diagram.CTGroupShape;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

/* loaded from: classes.dex */
public class CTDrawingImpl extends XmlComplexContentImpl implements CTDrawing {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/drawing/2008/diagram", "spTree")};
    private static final long serialVersionUID = 1;

    public CTDrawingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTDrawing
    public CTGroupShape getSpTree() {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupShape target = (CTGroupShape) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGroupShape = target == null ? null : target;
        }
        return cTGroupShape;
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTDrawing
    public void setSpTree(CTGroupShape spTree) {
        generatedSetterHelperImpl(spTree, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.drawing.x2008.diagram.CTDrawing
    public CTGroupShape addNewSpTree() {
        CTGroupShape target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
