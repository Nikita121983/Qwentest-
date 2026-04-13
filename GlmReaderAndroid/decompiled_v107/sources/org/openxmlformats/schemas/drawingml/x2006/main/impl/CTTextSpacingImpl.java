package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;

/* loaded from: classes11.dex */
public class CTTextSpacingImpl extends XmlComplexContentImpl implements CTTextSpacing {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "spcPct"), new QName(XSSFRelation.NS_DRAWINGML, "spcPts")};
    private static final long serialVersionUID = 1;

    public CTTextSpacingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public CTTextSpacingPercent getSpcPct() {
        CTTextSpacingPercent cTTextSpacingPercent;
        synchronized (monitor()) {
            check_orphaned();
            CTTextSpacingPercent target = (CTTextSpacingPercent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTextSpacingPercent = target == null ? null : target;
        }
        return cTTextSpacingPercent;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public boolean isSetSpcPct() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public void setSpcPct(CTTextSpacingPercent spcPct) {
        generatedSetterHelperImpl(spcPct, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public CTTextSpacingPercent addNewSpcPct() {
        CTTextSpacingPercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextSpacingPercent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public void unsetSpcPct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public CTTextSpacingPoint getSpcPts() {
        CTTextSpacingPoint cTTextSpacingPoint;
        synchronized (monitor()) {
            check_orphaned();
            CTTextSpacingPoint target = (CTTextSpacingPoint) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextSpacingPoint = target == null ? null : target;
        }
        return cTTextSpacingPoint;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public boolean isSetSpcPts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public void setSpcPts(CTTextSpacingPoint spcPts) {
        generatedSetterHelperImpl(spcPts, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public CTTextSpacingPoint addNewSpcPts() {
        CTTextSpacingPoint target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextSpacingPoint) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing
    public void unsetSpcPts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
