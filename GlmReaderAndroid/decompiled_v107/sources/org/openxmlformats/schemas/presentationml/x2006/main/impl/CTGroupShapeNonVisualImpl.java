package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGroupDrawingShapeProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTApplicationNonVisualDrawingProps;
import org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual;

/* loaded from: classes11.dex */
public class CTGroupShapeNonVisualImpl extends XmlComplexContentImpl implements CTGroupShapeNonVisual {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cNvPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "cNvGrpSpPr"), new QName(XSSFRelation.NS_PRESENTATIONML, "nvPr")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeNonVisualImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public CTNonVisualDrawingProps getCNvPr() {
        CTNonVisualDrawingProps cTNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualDrawingProps target = (CTNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNonVisualDrawingProps = target == null ? null : target;
        }
        return cTNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public void setCNvPr(CTNonVisualDrawingProps cNvPr) {
        generatedSetterHelperImpl(cNvPr, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public CTNonVisualDrawingProps addNewCNvPr() {
        CTNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public CTNonVisualGroupDrawingShapeProps getCNvGrpSpPr() {
        CTNonVisualGroupDrawingShapeProps cTNonVisualGroupDrawingShapeProps;
        synchronized (monitor()) {
            check_orphaned();
            CTNonVisualGroupDrawingShapeProps target = (CTNonVisualGroupDrawingShapeProps) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTNonVisualGroupDrawingShapeProps = target == null ? null : target;
        }
        return cTNonVisualGroupDrawingShapeProps;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public void setCNvGrpSpPr(CTNonVisualGroupDrawingShapeProps cNvGrpSpPr) {
        generatedSetterHelperImpl(cNvGrpSpPr, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public CTNonVisualGroupDrawingShapeProps addNewCNvGrpSpPr() {
        CTNonVisualGroupDrawingShapeProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNonVisualGroupDrawingShapeProps) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public CTApplicationNonVisualDrawingProps getNvPr() {
        CTApplicationNonVisualDrawingProps cTApplicationNonVisualDrawingProps;
        synchronized (monitor()) {
            check_orphaned();
            CTApplicationNonVisualDrawingProps target = (CTApplicationNonVisualDrawingProps) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTApplicationNonVisualDrawingProps = target == null ? null : target;
        }
        return cTApplicationNonVisualDrawingProps;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public void setNvPr(CTApplicationNonVisualDrawingProps nvPr) {
        generatedSetterHelperImpl(nvPr, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTGroupShapeNonVisual
    public CTApplicationNonVisualDrawingProps addNewNvPr() {
        CTApplicationNonVisualDrawingProps target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTApplicationNonVisualDrawingProps) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }
}
