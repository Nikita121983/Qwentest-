package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDispBlanksAs;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPivotFmts;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSurface;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTitle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTView3D;

/* loaded from: classes11.dex */
public class CTChartImpl extends XmlComplexContentImpl implements CTChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "title"), new QName(XSSFRelation.NS_CHART, "autoTitleDeleted"), new QName(XSSFRelation.NS_CHART, "pivotFmts"), new QName(XSSFRelation.NS_CHART, "view3D"), new QName(XSSFRelation.NS_CHART, "floor"), new QName(XSSFRelation.NS_CHART, "sideWall"), new QName(XSSFRelation.NS_CHART, "backWall"), new QName(XSSFRelation.NS_CHART, "plotArea"), new QName(XSSFRelation.NS_CHART, "legend"), new QName(XSSFRelation.NS_CHART, "plotVisOnly"), new QName(XSSFRelation.NS_CHART, "dispBlanksAs"), new QName(XSSFRelation.NS_CHART, "showDLblsOverMax"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTChartImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTTitle getTitle() {
        CTTitle cTTitle;
        synchronized (monitor()) {
            check_orphaned();
            CTTitle target = (CTTitle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTTitle = target == null ? null : target;
        }
        return cTTitle;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setTitle(CTTitle title) {
        generatedSetterHelperImpl(title, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTTitle addNewTitle() {
        CTTitle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTitle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTBoolean getAutoTitleDeleted() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetAutoTitleDeleted() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setAutoTitleDeleted(CTBoolean autoTitleDeleted) {
        generatedSetterHelperImpl(autoTitleDeleted, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTBoolean addNewAutoTitleDeleted() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetAutoTitleDeleted() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTPivotFmts getPivotFmts() {
        CTPivotFmts cTPivotFmts;
        synchronized (monitor()) {
            check_orphaned();
            CTPivotFmts target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTPivotFmts = target == null ? null : target;
        }
        return cTPivotFmts;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetPivotFmts() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setPivotFmts(CTPivotFmts pivotFmts) {
        generatedSetterHelperImpl(pivotFmts, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTPivotFmts addNewPivotFmts() {
        CTPivotFmts target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetPivotFmts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTView3D getView3D() {
        CTView3D cTView3D;
        synchronized (monitor()) {
            check_orphaned();
            CTView3D target = (CTView3D) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTView3D = target == null ? null : target;
        }
        return cTView3D;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetView3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setView3D(CTView3D view3D) {
        generatedSetterHelperImpl(view3D, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTView3D addNewView3D() {
        CTView3D target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTView3D) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetView3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTSurface getFloor() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            CTSurface target = (CTSurface) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTSurface = target == null ? null : target;
        }
        return cTSurface;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetFloor() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setFloor(CTSurface floor) {
        generatedSetterHelperImpl(floor, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTSurface addNewFloor() {
        CTSurface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurface) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetFloor() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTSurface getSideWall() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            CTSurface target = (CTSurface) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTSurface = target == null ? null : target;
        }
        return cTSurface;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetSideWall() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setSideWall(CTSurface sideWall) {
        generatedSetterHelperImpl(sideWall, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTSurface addNewSideWall() {
        CTSurface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurface) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetSideWall() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTSurface getBackWall() {
        CTSurface cTSurface;
        synchronized (monitor()) {
            check_orphaned();
            CTSurface target = (CTSurface) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTSurface = target == null ? null : target;
        }
        return cTSurface;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetBackWall() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setBackWall(CTSurface backWall) {
        generatedSetterHelperImpl(backWall, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTSurface addNewBackWall() {
        CTSurface target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSurface) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetBackWall() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTPlotArea getPlotArea() {
        CTPlotArea cTPlotArea;
        synchronized (monitor()) {
            check_orphaned();
            CTPlotArea target = (CTPlotArea) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTPlotArea = target == null ? null : target;
        }
        return cTPlotArea;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setPlotArea(CTPlotArea plotArea) {
        generatedSetterHelperImpl(plotArea, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTPlotArea addNewPlotArea() {
        CTPlotArea target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPlotArea) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTLegend getLegend() {
        CTLegend cTLegend;
        synchronized (monitor()) {
            check_orphaned();
            CTLegend target = (CTLegend) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            cTLegend = target == null ? null : target;
        }
        return cTLegend;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetLegend() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setLegend(CTLegend legend) {
        generatedSetterHelperImpl(legend, PROPERTY_QNAME[8], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTLegend addNewLegend() {
        CTLegend target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLegend) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetLegend() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTBoolean getPlotVisOnly() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetPlotVisOnly() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setPlotVisOnly(CTBoolean plotVisOnly) {
        generatedSetterHelperImpl(plotVisOnly, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTBoolean addNewPlotVisOnly() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetPlotVisOnly() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTDispBlanksAs getDispBlanksAs() {
        CTDispBlanksAs cTDispBlanksAs;
        synchronized (monitor()) {
            check_orphaned();
            CTDispBlanksAs target = (CTDispBlanksAs) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTDispBlanksAs = target == null ? null : target;
        }
        return cTDispBlanksAs;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetDispBlanksAs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setDispBlanksAs(CTDispBlanksAs dispBlanksAs) {
        generatedSetterHelperImpl(dispBlanksAs, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTDispBlanksAs addNewDispBlanksAs() {
        CTDispBlanksAs target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDispBlanksAs) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetDispBlanksAs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTBoolean getShowDLblsOverMax() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetShowDLblsOverMax() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setShowDLblsOverMax(CTBoolean showDLblsOverMax) {
        generatedSetterHelperImpl(showDLblsOverMax, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTBoolean addNewShowDLblsOverMax() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetShowDLblsOverMax() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTChart
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }
}
