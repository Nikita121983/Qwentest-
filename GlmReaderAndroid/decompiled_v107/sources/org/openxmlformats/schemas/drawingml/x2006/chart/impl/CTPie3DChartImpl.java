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
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;

/* loaded from: classes11.dex */
public class CTPie3DChartImpl extends XmlComplexContentImpl implements CTPie3DChart {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "varyColors"), new QName(XSSFRelation.NS_CHART, "ser"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTPie3DChartImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTBoolean getVaryColors() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public boolean isSetVaryColors() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void setVaryColors(CTBoolean varyColors) {
        generatedSetterHelperImpl(varyColors, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTBoolean addNewVaryColors() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void unsetVaryColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public List<CTPieSer> getSerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPie3DChartImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPie3DChartImpl.this.getSerArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPie3DChartImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTPie3DChartImpl.this.setSerArray(((Integer) obj).intValue(), (CTPieSer) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPie3DChartImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTPie3DChartImpl.this.insertNewSer(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPie3DChartImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTPie3DChartImpl.this.removeSer(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTPie3DChartImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTPie3DChartImpl.this.sizeOfSerArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTPieSer[] getSerArray() {
        return (CTPieSer[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTPieSer[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTPieSer getSerArray(int i) {
        CTPieSer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPieSer) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public int sizeOfSerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void setSerArray(CTPieSer[] serArray) {
        check_orphaned();
        arraySetterHelper(serArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void setSerArray(int i, CTPieSer ser) {
        generatedSetterHelperImpl(ser, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTPieSer insertNewSer(int i) {
        CTPieSer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPieSer) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTPieSer addNewSer() {
        CTPieSer target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPieSer) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void removeSer(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            CTDLbls target = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTDLbls = target == null ? null : target;
        }
        return cTDLbls;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public boolean isSetDLbls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void setDLbls(CTDLbls dLbls) {
        generatedSetterHelperImpl(dLbls, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTDLbls addNewDLbls() {
        CTDLbls target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
