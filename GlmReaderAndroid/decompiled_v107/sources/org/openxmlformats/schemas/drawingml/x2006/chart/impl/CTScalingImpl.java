package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLogBase;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTOrientation;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;

/* loaded from: classes11.dex */
public class CTScalingImpl extends XmlComplexContentImpl implements CTScaling {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "logBase"), new QName(XSSFRelation.NS_CHART, "orientation"), new QName(XSSFRelation.NS_CHART, "max"), new QName(XSSFRelation.NS_CHART, "min"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTScalingImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTLogBase getLogBase() {
        CTLogBase cTLogBase;
        synchronized (monitor()) {
            check_orphaned();
            CTLogBase target = (CTLogBase) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLogBase = target == null ? null : target;
        }
        return cTLogBase;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public boolean isSetLogBase() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void setLogBase(CTLogBase logBase) {
        generatedSetterHelperImpl(logBase, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTLogBase addNewLogBase() {
        CTLogBase target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLogBase) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void unsetLogBase() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTOrientation getOrientation() {
        CTOrientation cTOrientation;
        synchronized (monitor()) {
            check_orphaned();
            CTOrientation target = (CTOrientation) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTOrientation = target == null ? null : target;
        }
        return cTOrientation;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public boolean isSetOrientation() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void setOrientation(CTOrientation orientation) {
        generatedSetterHelperImpl(orientation, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTOrientation addNewOrientation() {
        CTOrientation target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOrientation) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void unsetOrientation() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTDouble getMax() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public boolean isSetMax() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void setMax(CTDouble max) {
        generatedSetterHelperImpl(max, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTDouble addNewMax() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void unsetMax() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTDouble getMin() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public boolean isSetMin() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void setMin(CTDouble min) {
        generatedSetterHelperImpl(min, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTDouble addNewMin() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void unsetMin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
