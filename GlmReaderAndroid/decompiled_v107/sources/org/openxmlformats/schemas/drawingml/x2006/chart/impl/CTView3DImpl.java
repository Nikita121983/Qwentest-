package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDepthPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTHPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPerspective;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotX;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRotY;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

/* loaded from: classes11.dex */
public class CTView3DImpl extends XmlComplexContentImpl implements CTView3D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "rotX"), new QName(XSSFRelation.NS_CHART, "hPercent"), new QName(XSSFRelation.NS_CHART, "rotY"), new QName(XSSFRelation.NS_CHART, "depthPercent"), new QName(XSSFRelation.NS_CHART, "rAngAx"), new QName(XSSFRelation.NS_CHART, "perspective"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTView3DImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTRotX getRotX() {
        CTRotX cTRotX;
        synchronized (monitor()) {
            check_orphaned();
            CTRotX target = (CTRotX) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTRotX = target == null ? null : target;
        }
        return cTRotX;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetRotX() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setRotX(CTRotX rotX) {
        generatedSetterHelperImpl(rotX, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTRotX addNewRotX() {
        CTRotX target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRotX) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetRotX() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTHPercent getHPercent() {
        CTHPercent cTHPercent;
        synchronized (monitor()) {
            check_orphaned();
            CTHPercent target = (CTHPercent) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTHPercent = target == null ? null : target;
        }
        return cTHPercent;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetHPercent() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setHPercent(CTHPercent hPercent) {
        generatedSetterHelperImpl(hPercent, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTHPercent addNewHPercent() {
        CTHPercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTHPercent) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetHPercent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTRotY getRotY() {
        CTRotY cTRotY;
        synchronized (monitor()) {
            check_orphaned();
            CTRotY target = (CTRotY) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTRotY = target == null ? null : target;
        }
        return cTRotY;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetRotY() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setRotY(CTRotY rotY) {
        generatedSetterHelperImpl(rotY, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTRotY addNewRotY() {
        CTRotY target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRotY) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetRotY() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTDepthPercent getDepthPercent() {
        CTDepthPercent cTDepthPercent;
        synchronized (monitor()) {
            check_orphaned();
            CTDepthPercent target = (CTDepthPercent) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTDepthPercent = target == null ? null : target;
        }
        return cTDepthPercent;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetDepthPercent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setDepthPercent(CTDepthPercent depthPercent) {
        generatedSetterHelperImpl(depthPercent, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTDepthPercent addNewDepthPercent() {
        CTDepthPercent target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDepthPercent) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetDepthPercent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTBoolean getRAngAx() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetRAngAx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setRAngAx(CTBoolean rAngAx) {
        generatedSetterHelperImpl(rAngAx, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTBoolean addNewRAngAx() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetRAngAx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTPerspective getPerspective() {
        CTPerspective cTPerspective;
        synchronized (monitor()) {
            check_orphaned();
            CTPerspective target = (CTPerspective) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPerspective = target == null ? null : target;
        }
        return cTPerspective;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetPerspective() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setPerspective(CTPerspective perspective) {
        generatedSetterHelperImpl(perspective, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTPerspective addNewPerspective() {
        CTPerspective target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPerspective) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetPerspective() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }
}
