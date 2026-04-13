package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo;

/* loaded from: classes11.dex */
public class CTPath2DLineToImpl extends XmlComplexContentImpl implements CTPath2DLineTo {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pt")};
    private static final long serialVersionUID = 1;

    public CTPath2DLineToImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo
    public CTAdjPoint2D getPt() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            CTAdjPoint2D target = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTAdjPoint2D = target == null ? null : target;
        }
        return cTAdjPoint2D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo
    public void setPt(CTAdjPoint2D pt) {
        generatedSetterHelperImpl(pt, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DLineTo
    public CTAdjPoint2D addNewPt() {
        CTAdjPoint2D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }
}
