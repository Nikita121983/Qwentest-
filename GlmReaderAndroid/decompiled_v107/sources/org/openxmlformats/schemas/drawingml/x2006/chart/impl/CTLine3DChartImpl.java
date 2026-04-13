package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTGrouping;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

/* loaded from: classes11.dex */
public class CTLine3DChartImpl extends XmlComplexContentImpl implements CTLine3DChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "grouping"), new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "dropLines"), new QName(XSSFRelation.NS_CHART, "gapDepth"), new QName(XSSFRelation.NS_CHART, "axId"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTLine3DChartImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTGrouping getGrouping() {
        CTGrouping cTGrouping;
        synchronized (monitor()) {
            check_orphaned();
            CTGrouping target = (CTGrouping) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTGrouping = target == null ? null : target;
        }
        return cTGrouping;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setGrouping(CTGrouping grouping) {
        generatedSetterHelperImpl(grouping, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTGrouping addNewGrouping() {
        CTGrouping target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGrouping) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTBoolean getVaryColors() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public boolean isSetVaryColors() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setVaryColors(CTBoolean varyColors) {
        generatedSetterHelperImpl(varyColors, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTBoolean addNewVaryColors() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void unsetVaryColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public List<CTLineSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTLine3DChartImpl.this.getSerArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTLine3DChartImpl.this.setSerArray(((Integer) obj).intValue(), (CTLineSer) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTLine3DChartImpl.this.insertNewSer(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTLine3DChartImpl.this.removeSer(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTLine3DChartImpl.this.sizeOfSerArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTLineSer[] getSerArray() {
        return (CTLineSer[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTLineSer[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTLineSer getSerArray(int i) {
        CTLineSer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineSer) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setSerArray(CTLineSer[] serArray) {
        check_orphaned();
        arraySetterHelper(serArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setSerArray(int i, CTLineSer ser) {
        generatedSetterHelperImpl(ser, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTLineSer insertNewSer(int i) {
        CTLineSer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineSer) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTLineSer addNewSer() {
        CTLineSer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTLineSer) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void removeSer(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            CTDLbls target = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTDLbls = target == null ? null : target;
        }
        return cTDLbls;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public boolean isSetDLbls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setDLbls(CTDLbls dLbls) {
        generatedSetterHelperImpl(dLbls, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTDLbls addNewDLbls() {
        CTDLbls target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTChartLines getDropLines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            CTChartLines target = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTChartLines = target == null ? null : target;
        }
        return cTChartLines;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public boolean isSetDropLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setDropLines(CTChartLines dropLines) {
        generatedSetterHelperImpl(dropLines, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTChartLines addNewDropLines() {
        CTChartLines target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void unsetDropLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTGapAmount getGapDepth() {
        CTGapAmount cTGapAmount;
        synchronized (monitor()) {
            check_orphaned();
            CTGapAmount target = (CTGapAmount) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTGapAmount = target == null ? null : target;
        }
        return cTGapAmount;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public boolean isSetGapDepth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setGapDepth(CTGapAmount gapDepth) {
        generatedSetterHelperImpl(gapDepth, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTGapAmount addNewGapDepth() {
        CTGapAmount target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTGapAmount) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void unsetGapDepth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public List<CTUnsignedInt> getAxIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTLine3DChartImpl.this.getAxIdArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTLine3DChartImpl.this.setAxIdArray(((Integer) obj).intValue(), (CTUnsignedInt) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTLine3DChartImpl.this.insertNewAxId(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTLine3DChartImpl.this.removeAxId(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTLine3DChartImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTLine3DChartImpl.this.sizeOfAxIdArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTUnsignedInt[] getAxIdArray() {
        return (CTUnsignedInt[]) getXmlObjectArray(PROPERTY_QNAME[6], new CTUnsignedInt[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTUnsignedInt getAxIdArray(int i) {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public int sizeOfAxIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setAxIdArray(CTUnsignedInt[] axIdArray) {
        check_orphaned();
        arraySetterHelper(axIdArray, PROPERTY_QNAME[6]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setAxIdArray(int i, CTUnsignedInt axId) {
        generatedSetterHelperImpl(axId, PROPERTY_QNAME[6], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTUnsignedInt insertNewAxId(int i) {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTUnsignedInt addNewAxId() {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void removeAxId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }
}
