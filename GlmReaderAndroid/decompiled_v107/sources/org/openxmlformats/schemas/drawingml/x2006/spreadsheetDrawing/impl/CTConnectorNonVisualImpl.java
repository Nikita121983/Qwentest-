package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual;

/* loaded from: classes11.dex */
public class CTConnectorNonVisualImpl extends XmlComplexContentImpl implements CTConnectorNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cNvCxnSpPr")};
    private static final long serialVersionUID = 1;

    public CTConnectorNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual
    public CTNonVisualConnectorProperties getCNvCxnSpPr() {
        CTNonVisualConnectorProperties cTNonVisualConnectorProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualConnectorProperties target = (CTNonVisualConnectorProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualConnectorProperties = target == null ? null : target;
        }
        return cTNonVisualConnectorProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual
    public void setCNvCxnSpPr(CTNonVisualConnectorProperties cNvCxnSpPr) {
        generatedSetterHelperImpl(cNvCxnSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnectorNonVisual
    public CTNonVisualConnectorProperties addNewCNvCxnSpPr() {
        CTNonVisualConnectorProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualConnectorProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }
}
