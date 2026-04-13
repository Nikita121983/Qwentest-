package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import kotlinx.coroutines.DebugKt;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxisUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCrosses;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLblOffset;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScaling;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTickMark;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTimeUnit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

/* loaded from: classes11.dex */
public class CTDateAxImpl extends XmlComplexContentImpl implements CTDateAx {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "scaling"), new QName(XSSFRelation.NS_CHART, "delete"), new QName(XSSFRelation.NS_CHART, "axPos"), new QName(XSSFRelation.NS_CHART, "majorGridlines"), new QName(XSSFRelation.NS_CHART, "minorGridlines"), new QName(XSSFRelation.NS_CHART, "title"), new QName(XSSFRelation.NS_CHART, "numFmt"), new QName(XSSFRelation.NS_CHART, "majorTickMark"), new QName(XSSFRelation.NS_CHART, "minorTickMark"), new QName(XSSFRelation.NS_CHART, "tickLblPos"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "txPr"), new QName(XSSFRelation.NS_CHART, "crossAx"), new QName(XSSFRelation.NS_CHART, "crosses"), new QName(XSSFRelation.NS_CHART, "crossesAt"), new QName(XSSFRelation.NS_CHART, DebugKt.DEBUG_PROPERTY_VALUE_AUTO), new QName(XSSFRelation.NS_CHART, "lblOffset"), new QName(XSSFRelation.NS_CHART, "baseTimeUnit"), new QName(XSSFRelation.NS_CHART, "majorUnit"), new QName(XSSFRelation.NS_CHART, "majorTimeUnit"), new QName(XSSFRelation.NS_CHART, "minorUnit"), new QName(XSSFRelation.NS_CHART, "minorTimeUnit"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDateAxImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTUnsignedInt getAxId() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            CTUnsignedInt target = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTUnsignedInt = target == null ? null : target;
        }
        return cTUnsignedInt;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setAxId(CTUnsignedInt axId) {
        generatedSetterHelperImpl(axId, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTScaling getScaling() {
        CTScaling cTScaling;
        synchronized (monitor()) {
            check_orphaned();
            CTScaling target = (CTScaling) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTScaling = target == null ? null : target;
        }
        return cTScaling;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setScaling(CTScaling scaling) {
        generatedSetterHelperImpl(scaling, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTScaling addNewScaling() {
        CTScaling target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTScaling) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTBoolean getDelete() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetDelete() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setDelete(CTBoolean delete) {
        generatedSetterHelperImpl(delete, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTBoolean addNewDelete() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetDelete() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTAxPos getAxPos() {
        CTAxPos cTAxPos;
        synchronized (monitor()) {
            check_orphaned();
            CTAxPos target = (CTAxPos) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTAxPos = target == null ? null : target;
        }
        return cTAxPos;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setAxPos(CTAxPos axPos) {
        generatedSetterHelperImpl(axPos, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTAxPos addNewAxPos() {
        CTAxPos target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAxPos) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTChartLines getMajorGridlines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            CTChartLines target = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTChartLines = target == null ? null : target;
        }
        return cTChartLines;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMajorGridlines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMajorGridlines(CTChartLines majorGridlines) {
        generatedSetterHelperImpl(majorGridlines, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTChartLines addNewMajorGridlines() {
        CTChartLines target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMajorGridlines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTChartLines getMinorGridlines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            CTChartLines target = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTChartLines = target == null ? null : target;
        }
        return cTChartLines;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMinorGridlines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMinorGridlines(CTChartLines minorGridlines) {
        generatedSetterHelperImpl(minorGridlines, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTChartLines addNewMinorGridlines() {
        CTChartLines target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMinorGridlines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTitle getTitle() {
        CTTitle cTTitle;
        synchronized (monitor()) {
            check_orphaned();
            CTTitle target = (CTTitle) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTTitle = target == null ? null : target;
        }
        return cTTitle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setTitle(CTTitle title) {
        generatedSetterHelperImpl(title, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTitle addNewTitle() {
        CTTitle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTitle) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            CTNumFmt target = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTNumFmt = target == null ? null : target;
        }
        return cTNumFmt;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetNumFmt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setNumFmt(CTNumFmt numFmt) {
        generatedSetterHelperImpl(numFmt, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTNumFmt addNewNumFmt() {
        CTNumFmt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTickMark getMajorTickMark() {
        CTTickMark cTTickMark;
        synchronized (monitor()) {
            check_orphaned();
            CTTickMark target = (CTTickMark) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTTickMark = target == null ? null : target;
        }
        return cTTickMark;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMajorTickMark() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMajorTickMark(CTTickMark majorTickMark) {
        generatedSetterHelperImpl(majorTickMark, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTickMark addNewMajorTickMark() {
        CTTickMark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTickMark) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMajorTickMark() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTickMark getMinorTickMark() {
        CTTickMark cTTickMark;
        synchronized (monitor()) {
            check_orphaned();
            CTTickMark target = (CTTickMark) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTTickMark = target == null ? null : target;
        }
        return cTTickMark;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMinorTickMark() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMinorTickMark(CTTickMark minorTickMark) {
        generatedSetterHelperImpl(minorTickMark, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTickMark addNewMinorTickMark() {
        CTTickMark target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTickMark) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMinorTickMark() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTickLblPos getTickLblPos() {
        CTTickLblPos cTTickLblPos;
        synchronized (monitor()) {
            check_orphaned();
            CTTickLblPos target = (CTTickLblPos) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTTickLblPos = target == null ? null : target;
        }
        return cTTickLblPos;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetTickLblPos() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setTickLblPos(CTTickLblPos tickLblPos) {
        generatedSetterHelperImpl(tickLblPos, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTickLblPos addNewTickLblPos() {
        CTTickLblPos target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTickLblPos) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetTickLblPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTextBody getTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            CTTextBody target = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTTextBody = target == null ? null : target;
        }
        return cTTextBody;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetTxPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setTxPr(CTTextBody txPr) {
        generatedSetterHelperImpl(txPr, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTextBody addNewTxPr() {
        CTTextBody target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetTxPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTUnsignedInt getCrossAx() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            CTUnsignedInt target = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            cTUnsignedInt = target == null ? null : target;
        }
        return cTUnsignedInt;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setCrossAx(CTUnsignedInt crossAx) {
        generatedSetterHelperImpl(crossAx, PROPERTY_QNAME[13], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTUnsignedInt addNewCrossAx() {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTCrosses getCrosses() {
        CTCrosses cTCrosses;
        synchronized (monitor()) {
            check_orphaned();
            CTCrosses target = (CTCrosses) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            cTCrosses = target == null ? null : target;
        }
        return cTCrosses;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetCrosses() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setCrosses(CTCrosses crosses) {
        generatedSetterHelperImpl(crosses, PROPERTY_QNAME[14], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTCrosses addNewCrosses() {
        CTCrosses target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCrosses) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetCrosses() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTDouble getCrossesAt() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            CTDouble target = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            cTDouble = target == null ? null : target;
        }
        return cTDouble;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetCrossesAt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setCrossesAt(CTDouble crossesAt) {
        generatedSetterHelperImpl(crossesAt, PROPERTY_QNAME[15], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTDouble addNewCrossesAt() {
        CTDouble target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetCrossesAt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTBoolean getAuto() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetAuto() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setAuto(CTBoolean auto) {
        generatedSetterHelperImpl(auto, PROPERTY_QNAME[16], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTBoolean addNewAuto() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetAuto() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTLblOffset getLblOffset() {
        CTLblOffset cTLblOffset;
        synchronized (monitor()) {
            check_orphaned();
            CTLblOffset target = get_store().find_element_user(PROPERTY_QNAME[17], 0);
            cTLblOffset = target == null ? null : target;
        }
        return cTLblOffset;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetLblOffset() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setLblOffset(CTLblOffset lblOffset) {
        generatedSetterHelperImpl(lblOffset, PROPERTY_QNAME[17], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTLblOffset addNewLblOffset() {
        CTLblOffset target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetLblOffset() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTimeUnit getBaseTimeUnit() {
        CTTimeUnit cTTimeUnit;
        synchronized (monitor()) {
            check_orphaned();
            CTTimeUnit target = get_store().find_element_user(PROPERTY_QNAME[18], 0);
            cTTimeUnit = target == null ? null : target;
        }
        return cTTimeUnit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetBaseTimeUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setBaseTimeUnit(CTTimeUnit baseTimeUnit) {
        generatedSetterHelperImpl(baseTimeUnit, PROPERTY_QNAME[18], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTimeUnit addNewBaseTimeUnit() {
        CTTimeUnit target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetBaseTimeUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTAxisUnit getMajorUnit() {
        CTAxisUnit cTAxisUnit;
        synchronized (monitor()) {
            check_orphaned();
            CTAxisUnit target = (CTAxisUnit) get_store().find_element_user(PROPERTY_QNAME[19], 0);
            cTAxisUnit = target == null ? null : target;
        }
        return cTAxisUnit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMajorUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[19]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMajorUnit(CTAxisUnit majorUnit) {
        generatedSetterHelperImpl(majorUnit, PROPERTY_QNAME[19], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTAxisUnit addNewMajorUnit() {
        CTAxisUnit target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAxisUnit) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMajorUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTimeUnit getMajorTimeUnit() {
        CTTimeUnit cTTimeUnit;
        synchronized (monitor()) {
            check_orphaned();
            CTTimeUnit target = get_store().find_element_user(PROPERTY_QNAME[20], 0);
            cTTimeUnit = target == null ? null : target;
        }
        return cTTimeUnit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMajorTimeUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[20]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMajorTimeUnit(CTTimeUnit majorTimeUnit) {
        generatedSetterHelperImpl(majorTimeUnit, PROPERTY_QNAME[20], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTimeUnit addNewMajorTimeUnit() {
        CTTimeUnit target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMajorTimeUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTAxisUnit getMinorUnit() {
        CTAxisUnit cTAxisUnit;
        synchronized (monitor()) {
            check_orphaned();
            CTAxisUnit target = (CTAxisUnit) get_store().find_element_user(PROPERTY_QNAME[21], 0);
            cTAxisUnit = target == null ? null : target;
        }
        return cTAxisUnit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMinorUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[21]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMinorUnit(CTAxisUnit minorUnit) {
        generatedSetterHelperImpl(minorUnit, PROPERTY_QNAME[21], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTAxisUnit addNewMinorUnit() {
        CTAxisUnit target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAxisUnit) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMinorUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTimeUnit getMinorTimeUnit() {
        CTTimeUnit cTTimeUnit;
        synchronized (monitor()) {
            check_orphaned();
            CTTimeUnit target = get_store().find_element_user(PROPERTY_QNAME[22], 0);
            cTTimeUnit = target == null ? null : target;
        }
        return cTTimeUnit;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetMinorTimeUnit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[22]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setMinorTimeUnit(CTTimeUnit minorTimeUnit) {
        generatedSetterHelperImpl(minorTimeUnit, PROPERTY_QNAME[22], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTTimeUnit addNewMinorTimeUnit() {
        CTTimeUnit target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetMinorTimeUnit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[23], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[23]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[23], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTDateAx
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], 0);
        }
    }
}
