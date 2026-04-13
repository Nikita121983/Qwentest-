package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutMode;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayoutTarget;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout;

/* loaded from: classes11.dex */
public class CTManualLayoutImpl extends XmlComplexContentImpl implements CTManualLayout {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "layoutTarget"), new QName(XSSFRelation.NS_CHART, "xMode"), new QName(XSSFRelation.NS_CHART, "yMode"), new QName(XSSFRelation.NS_CHART, "wMode"), new QName(XSSFRelation.NS_CHART, "hMode"), new QName(XSSFRelation.NS_CHART, "x"), new QName(XSSFRelation.NS_CHART, "y"), new QName(XSSFRelation.NS_CHART, "w"), new QName(XSSFRelation.NS_CHART, "h"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTManualLayoutImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutTarget getLayoutTarget() {
        CTLayoutTarget cTLayoutTarget;
        synchronized (monitor()) {
            check_orphaned();
            CTLayoutTarget target = (CTLayoutTarget) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTLayoutTarget = target == null ? null : target;
        }
        return cTLayoutTarget;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetLayoutTarget() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setLayoutTarget(CTLayoutTarget layoutTarget) {
        generatedSetterHelperImpl(layoutTarget, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutTarget addNewLayoutTarget() {
        CTLayoutTarget target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLayoutTarget) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetLayoutTarget() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode getXMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            CTLayoutMode target = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTLayoutMode = target == null ? null : target;
        }
        return cTLayoutMode;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetXMode() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setXMode(CTLayoutMode xMode) {
        generatedSetterHelperImpl(xMode, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode addNewXMode() {
        CTLayoutMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetXMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode getYMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            CTLayoutMode target = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTLayoutMode = target == null ? null : target;
        }
        return cTLayoutMode;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetYMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setYMode(CTLayoutMode yMode) {
        generatedSetterHelperImpl(yMode, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode addNewYMode() {
        CTLayoutMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetYMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode getWMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            CTLayoutMode target = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTLayoutMode = target == null ? null : target;
        }
        return cTLayoutMode;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetWMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setWMode(CTLayoutMode wMode) {
        generatedSetterHelperImpl(wMode, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode addNewWMode() {
        CTLayoutMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetWMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode getHMode() {
        CTLayoutMode cTLayoutMode;
        synchronized (monitor()) {
            check_orphaned();
            CTLayoutMode target = (CTLayoutMode) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTLayoutMode = target == null ? null : target;
        }
        return cTLayoutMode;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetHMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setHMode(CTLayoutMode hMode) {
        generatedSetterHelperImpl(hMode, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTLayoutMode addNewHMode() {
        CTLayoutMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLayoutMode) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetHMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble getX() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setX(CTDouble x) {
        generatedSetterHelperImpl(x, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble addNewX() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble getY() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setY(CTDouble y) {
        generatedSetterHelperImpl(y, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble addNewY() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble getW() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setW(CTDouble w) {
        generatedSetterHelperImpl(w, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble addNewW() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble getH() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetH() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setH(CTDouble h) {
        generatedSetterHelperImpl(h, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTDouble addNewH() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetH() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTManualLayout
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }
}
