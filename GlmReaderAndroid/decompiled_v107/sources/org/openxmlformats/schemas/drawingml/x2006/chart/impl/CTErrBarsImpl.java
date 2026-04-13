package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrValType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

/* loaded from: classes11.dex */
public class CTErrBarsImpl extends XmlComplexContentImpl implements CTErrBars {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "errDir"), new QName(XSSFRelation.NS_CHART, "errBarType"), new QName(XSSFRelation.NS_CHART, "errValType"), new QName(XSSFRelation.NS_CHART, "noEndCap"), new QName(XSSFRelation.NS_CHART, "plus"), new QName(XSSFRelation.NS_CHART, "minus"), new QName(XSSFRelation.NS_CHART, "val"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTErrBarsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTErrDir getErrDir() {
        CTErrDir cTErrDir;
        synchronized (monitor()) {
            check_orphaned();
            CTErrDir target = (CTErrDir) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTErrDir = target == null ? null : target;
        }
        return cTErrDir;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetErrDir() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setErrDir(CTErrDir errDir) {
        generatedSetterHelperImpl(errDir, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTErrDir addNewErrDir() {
        CTErrDir target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTErrDir) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetErrDir() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTErrBarType getErrBarType() {
        CTErrBarType cTErrBarType;
        synchronized (monitor()) {
            check_orphaned();
            CTErrBarType target = (CTErrBarType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTErrBarType = target == null ? null : target;
        }
        return cTErrBarType;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setErrBarType(CTErrBarType errBarType) {
        generatedSetterHelperImpl(errBarType, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTErrBarType addNewErrBarType() {
        CTErrBarType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTErrBarType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTErrValType getErrValType() {
        CTErrValType cTErrValType;
        synchronized (monitor()) {
            check_orphaned();
            CTErrValType target = (CTErrValType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTErrValType = target == null ? null : target;
        }
        return cTErrValType;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setErrValType(CTErrValType errValType) {
        generatedSetterHelperImpl(errValType, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTErrValType addNewErrValType() {
        CTErrValType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTErrValType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTBoolean getNoEndCap() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetNoEndCap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setNoEndCap(CTBoolean noEndCap) {
        generatedSetterHelperImpl(noEndCap, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTBoolean addNewNoEndCap() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetNoEndCap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTNumDataSource getPlus() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            CTNumDataSource target = (CTNumDataSource) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTNumDataSource = target == null ? null : target;
        }
        return cTNumDataSource;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetPlus() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setPlus(CTNumDataSource plus) {
        generatedSetterHelperImpl(plus, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTNumDataSource addNewPlus() {
        CTNumDataSource target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumDataSource) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetPlus() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTNumDataSource getMinus() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            CTNumDataSource target = (CTNumDataSource) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTNumDataSource = target == null ? null : target;
        }
        return cTNumDataSource;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetMinus() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setMinus(CTNumDataSource minus) {
        generatedSetterHelperImpl(minus, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTNumDataSource addNewMinus() {
        CTNumDataSource target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumDataSource) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetMinus() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTDouble getVal() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setVal(CTDouble val) {
        generatedSetterHelperImpl(val, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTDouble addNewVal() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
