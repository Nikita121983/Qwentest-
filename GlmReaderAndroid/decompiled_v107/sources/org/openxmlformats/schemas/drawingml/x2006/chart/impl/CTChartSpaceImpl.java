package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExternalData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPrintSettings;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTProtection;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRelId;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTextLanguageID;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMapping;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes11.dex */
public class CTChartSpaceImpl extends XmlComplexContentImpl implements CTChartSpace {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "date1904"), new QName(XSSFRelation.NS_CHART, "lang"), new QName(XSSFRelation.NS_CHART, "roundedCorners"), new QName(XSSFRelation.NS_CHART, "style"), new QName(XSSFRelation.NS_CHART, "clrMapOvr"), new QName(XSSFRelation.NS_CHART, "pivotSource"), new QName(XSSFRelation.NS_CHART, "protection"), new QName(XSSFRelation.NS_CHART, "chart"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "txPr"), new QName(XSSFRelation.NS_CHART, "externalData"), new QName(XSSFRelation.NS_CHART, "printSettings"), new QName(XSSFRelation.NS_CHART, "userShapes"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTChartSpaceImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTBoolean getDate1904() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetDate1904() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setDate1904(CTBoolean date1904) {
        generatedSetterHelperImpl(date1904, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTBoolean addNewDate1904() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetDate1904() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTTextLanguageID getLang() {
        CTTextLanguageID cTTextLanguageID;
        synchronized (monitor()) {
            check_orphaned();
            CTTextLanguageID target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTTextLanguageID = target == null ? null : target;
        }
        return cTTextLanguageID;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetLang() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setLang(CTTextLanguageID lang) {
        generatedSetterHelperImpl(lang, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTTextLanguageID addNewLang() {
        CTTextLanguageID target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetLang() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTBoolean getRoundedCorners() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetRoundedCorners() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setRoundedCorners(CTBoolean roundedCorners) {
        generatedSetterHelperImpl(roundedCorners, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTBoolean addNewRoundedCorners() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetRoundedCorners() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTStyle getStyle() {
        CTStyle cTStyle;
        synchronized (monitor()) {
            check_orphaned();
            CTStyle target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTStyle = target == null ? null : target;
        }
        return cTStyle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setStyle(CTStyle style) {
        generatedSetterHelperImpl(style, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTStyle addNewStyle() {
        CTStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTColorMapping getClrMapOvr() {
        CTColorMapping cTColorMapping;
        synchronized (monitor()) {
            check_orphaned();
            CTColorMapping target = (CTColorMapping) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTColorMapping = target == null ? null : target;
        }
        return cTColorMapping;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetClrMapOvr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setClrMapOvr(CTColorMapping clrMapOvr) {
        generatedSetterHelperImpl(clrMapOvr, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTColorMapping addNewClrMapOvr() {
        CTColorMapping target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTColorMapping) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetClrMapOvr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTPivotSource getPivotSource() {
        CTPivotSource cTPivotSource;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotSource target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPivotSource = target == null ? null : target;
        }
        return cTPivotSource;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetPivotSource() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setPivotSource(CTPivotSource pivotSource) {
        generatedSetterHelperImpl(pivotSource, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTPivotSource addNewPivotSource() {
        CTPivotSource target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetPivotSource() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTProtection getProtection() {
        CTProtection cTProtection;
        synchronized (monitor()) {
            check_orphaned();
            CTProtection target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTProtection = target == null ? null : target;
        }
        return cTProtection;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setProtection(CTProtection protection) {
        generatedSetterHelperImpl(protection, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTProtection addNewProtection() {
        CTProtection target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTChart getChart() {
        CTChart cTChart;
        synchronized (monitor()) {
            check_orphaned();
            CTChart target = (CTChart) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTChart = target == null ? null : target;
        }
        return cTChart;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setChart(CTChart chart) {
        generatedSetterHelperImpl(chart, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTChart addNewChart() {
        CTChart target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChart) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTTextBody getTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBody target = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTextBody = target == null ? null : target;
        }
        return cTTextBody;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetTxPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setTxPr(CTTextBody txPr) {
        generatedSetterHelperImpl(txPr, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTTextBody addNewTxPr() {
        CTTextBody target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetTxPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTExternalData getExternalData() {
        CTExternalData cTExternalData;
        synchronized (monitor()) {
            check_orphaned();
            CTExternalData target = (CTExternalData) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTExternalData = target == null ? null : target;
        }
        return cTExternalData;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetExternalData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setExternalData(CTExternalData externalData) {
        generatedSetterHelperImpl(externalData, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTExternalData addNewExternalData() {
        CTExternalData target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalData) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetExternalData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTPrintSettings getPrintSettings() {
        CTPrintSettings cTPrintSettings;
        synchronized (monitor()) {
            check_orphaned();
            CTPrintSettings target = (CTPrintSettings) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTPrintSettings = target == null ? null : target;
        }
        return cTPrintSettings;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetPrintSettings() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setPrintSettings(CTPrintSettings printSettings) {
        generatedSetterHelperImpl(printSettings, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTPrintSettings addNewPrintSettings() {
        CTPrintSettings target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPrintSettings) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetPrintSettings() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTRelId getUserShapes() {
        CTRelId cTRelId;
        synchronized (monitor()) {
            check_orphaned();
            CTRelId target = (CTRelId) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTRelId = target == null ? null : target;
        }
        return cTRelId;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetUserShapes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setUserShapes(CTRelId userShapes) {
        generatedSetterHelperImpl(userShapes, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTRelId addNewUserShapes() {
        CTRelId target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRelId) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetUserShapes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }
}
