package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;

/* loaded from: classes11.dex */
public class CTFillPropertiesImpl extends XmlComplexContentImpl implements CTFillProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill")};
    private static final long serialVersionUID = 1;

    public CTFillPropertiesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTNoFillProperties target = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTNoFillProperties = target == null ? null : target;
        }
        return cTNoFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public boolean isSetNoFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void setNoFill(CTNoFillProperties noFill) {
        generatedSetterHelperImpl(noFill, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTSolidColorFillProperties target = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTSolidColorFillProperties = target == null ? null : target;
        }
        return cTSolidColorFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public boolean isSetSolidFill() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void setSolidFill(CTSolidColorFillProperties solidFill) {
        generatedSetterHelperImpl(solidFill, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGradientFillProperties target = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTGradientFillProperties = target == null ? null : target;
        }
        return cTGradientFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void setGradFill(CTGradientFillProperties gradFill) {
        generatedSetterHelperImpl(gradFill, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTBlipFillProperties target = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBlipFillProperties = target == null ? null : target;
        }
        return cTBlipFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public boolean isSetBlipFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void setBlipFill(CTBlipFillProperties blipFill) {
        generatedSetterHelperImpl(blipFill, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void unsetBlipFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTPatternFillProperties target = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTPatternFillProperties = target == null ? null : target;
        }
        return cTPatternFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void setPattFill(CTPatternFillProperties pattFill) {
        generatedSetterHelperImpl(pattFill, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTGroupFillProperties getGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTGroupFillProperties target = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTGroupFillProperties = target == null ? null : target;
        }
        return cTGroupFillProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public boolean isSetGrpFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void setGrpFill(CTGroupFillProperties grpFill) {
        generatedSetterHelperImpl(grpFill, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTFillProperties
    public void unsetGrpFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }
}
