package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualConnectorProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual;

/* loaded from: classes11.dex */
public class CTConnectorNonVisualImpl extends XmlComplexContentImpl implements CTConnectorNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cNvPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "cNvCxnSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "nvPr")};
    private static final long serialVersionUID = 1;

    public CTConnectorNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public CTNonVisualConnectorProperties getCNvCxnSpPr() {
        CTNonVisualConnectorProperties cTNonVisualConnectorProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualConnectorProperties target = (CTNonVisualConnectorProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualConnectorProperties = target == null ? null : target;
        }
        return cTNonVisualConnectorProperties;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public void setCNvCxnSpPr(CTNonVisualConnectorProperties cNvCxnSpPr) {
        generatedSetterHelperImpl(cNvCxnSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public CTNonVisualConnectorProperties addNewCNvCxnSpPr() {
        CTNonVisualConnectorProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualConnectorProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public CTApplicationNonVisualDrawingProps getNvPr() {
        CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTApplicationNonVisualDrawingProps target = (CTApplicationNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTApplicationNonVisualDrawingProps = target == null ? null : target;
        }
        return cTApplicationNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public void setNvPr(CTApplicationNonVisualDrawingProps nvPr) {
        generatedSetterHelperImpl(nvPr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTConnectorNonVisual
    public CTApplicationNonVisualDrawingProps addNewNvPr() {
        CTApplicationNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTApplicationNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }
}
