package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDPt;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTrendline;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

/* loaded from: classes11.dex */
public class CTScatterSerImpl extends XmlComplexContentImpl implements CTScatterSer {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "idx"), new QName(XSSFRelation.NS_CHART, "order"), new QName(XSSFRelation.NS_CHART, "tx"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "marker"), new QName(XSSFRelation.NS_CHART, "dPt"), new QName(XSSFRelation.NS_CHART, "dLbls"), new QName(XSSFRelation.NS_CHART, "trendline"), new QName(XSSFRelation.NS_CHART, "errBars"), new QName(XSSFRelation.NS_CHART, "xVal"), new QName(XSSFRelation.NS_CHART, "yVal"), new QName(XSSFRelation.NS_CHART, "smooth"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTScatterSerImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTUnsignedInt getIdx() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            CTUnsignedInt target = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTUnsignedInt = target == null ? null : target;
        }
        return cTUnsignedInt;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setIdx(CTUnsignedInt idx) {
        generatedSetterHelperImpl(idx, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTUnsignedInt addNewIdx() {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTUnsignedInt getOrder() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            CTUnsignedInt target = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTUnsignedInt = target == null ? null : target;
        }
        return cTUnsignedInt;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setOrder(CTUnsignedInt order) {
        generatedSetterHelperImpl(order, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTUnsignedInt addNewOrder() {
        CTUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTSerTx getTx() {
        CTSerTx cTSerTx;
        synchronized (monitor()) {
            check_orphaned();
            CTSerTx target = (CTSerTx) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTSerTx = target == null ? null : target;
        }
        return cTSerTx;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetTx() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setTx(CTSerTx tx) {
        generatedSetterHelperImpl(tx, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTSerTx addNewTx() {
        CTSerTx target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSerTx) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetTx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            CTShapeProperties target = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTShapeProperties = target == null ? null : target;
        }
        return cTShapeProperties;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setSpPr(CTShapeProperties spPr) {
        generatedSetterHelperImpl(spPr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTShapeProperties addNewSpPr() {
        CTShapeProperties target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTMarker getMarker() {
        CTMarker cTMarker;
        synchronized (monitor()) {
            check_orphaned();
            CTMarker target = (CTMarker) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTMarker = target == null ? null : target;
        }
        return cTMarker;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetMarker() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setMarker(CTMarker marker) {
        generatedSetterHelperImpl(marker, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTMarker addNewMarker() {
        CTMarker target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTMarker) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetMarker() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public List<CTDPt> getDPtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTScatterSerImpl.this.getDPtArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTScatterSerImpl.this.setDPtArray(((Integer) obj).intValue(), (CTDPt) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTScatterSerImpl.this.insertNewDPt(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTScatterSerImpl.this.removeDPt(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTScatterSerImpl.this.sizeOfDPtArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTDPt[] getDPtArray() {
        return (CTDPt[]) getXmlObjectArray(PROPERTY_QNAME[5], new CTDPt[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTDPt getDPtArray(int i) {
        CTDPt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDPt) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public int sizeOfDPtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setDPtArray(CTDPt[] dPtArray) {
        check_orphaned();
        arraySetterHelper(dPtArray, PROPERTY_QNAME[5]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setDPtArray(int i, CTDPt dPt) {
        generatedSetterHelperImpl(dPt, PROPERTY_QNAME[5], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTDPt insertNewDPt(int i) {
        CTDPt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDPt) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTDPt addNewDPt() {
        CTDPt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDPt) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void removeDPt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTDLbls getDLbls() {
        CTDLbls cTDLbls;
        synchronized (monitor()) {
            check_orphaned();
            CTDLbls target = (CTDLbls) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTDLbls = target == null ? null : target;
        }
        return cTDLbls;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetDLbls() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setDLbls(CTDLbls dLbls) {
        generatedSetterHelperImpl(dLbls, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTDLbls addNewDLbls() {
        CTDLbls target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDLbls) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetDLbls() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public List<CTTrendline> getTrendlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTScatterSerImpl.this.getTrendlineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTScatterSerImpl.this.setTrendlineArray(((Integer) obj).intValue(), (CTTrendline) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda12
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTScatterSerImpl.this.insertNewTrendline(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTScatterSerImpl.this.removeTrendline(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda14
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTScatterSerImpl.this.sizeOfTrendlineArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTTrendline[] getTrendlineArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (XmlObject[]) new CTTrendline[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTTrendline getTrendlineArray(int i) {
        CTTrendline target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public int sizeOfTrendlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setTrendlineArray(CTTrendline[] trendlineArray) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) trendlineArray, PROPERTY_QNAME[7]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setTrendlineArray(int i, CTTrendline trendline) {
        generatedSetterHelperImpl(trendline, PROPERTY_QNAME[7], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTTrendline insertNewTrendline(int i) {
        CTTrendline target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTTrendline addNewTrendline() {
        CTTrendline target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void removeTrendline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public List<CTErrBars> getErrBarsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTScatterSerImpl.this.getErrBarsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTScatterSerImpl.this.setErrBarsArray(((Integer) obj).intValue(), (CTErrBars) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTScatterSerImpl.this.insertNewErrBars(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTScatterSerImpl.this.removeErrBars(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTScatterSerImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTScatterSerImpl.this.sizeOfErrBarsArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTErrBars[] getErrBarsArray() {
        return (CTErrBars[]) getXmlObjectArray(PROPERTY_QNAME[8], new CTErrBars[0]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTErrBars getErrBarsArray(int i) {
        CTErrBars target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTErrBars) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public int sizeOfErrBarsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setErrBarsArray(CTErrBars[] errBarsArray) {
        check_orphaned();
        arraySetterHelper(errBarsArray, PROPERTY_QNAME[8]);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setErrBarsArray(int i, CTErrBars errBars) {
        generatedSetterHelperImpl(errBars, PROPERTY_QNAME[8], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTErrBars insertNewErrBars(int i) {
        CTErrBars target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTErrBars) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTErrBars addNewErrBars() {
        CTErrBars target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTErrBars) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void removeErrBars(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTAxDataSource getXVal() {
        CTAxDataSource cTAxDataSource;
        synchronized (monitor()) {
            check_orphaned();
            CTAxDataSource target = (CTAxDataSource) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            cTAxDataSource = target == null ? null : target;
        }
        return cTAxDataSource;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetXVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setXVal(CTAxDataSource xVal) {
        generatedSetterHelperImpl(xVal, PROPERTY_QNAME[9], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTAxDataSource addNewXVal() {
        CTAxDataSource target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTAxDataSource) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetXVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTNumDataSource getYVal() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            CTNumDataSource target = (CTNumDataSource) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            cTNumDataSource = target == null ? null : target;
        }
        return cTNumDataSource;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetYVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setYVal(CTNumDataSource yVal) {
        generatedSetterHelperImpl(yVal, PROPERTY_QNAME[10], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTNumDataSource addNewYVal() {
        CTNumDataSource target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTNumDataSource) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetYVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTBoolean getSmooth() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            CTBoolean target = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            cTBoolean = target == null ? null : target;
        }
        return cTBoolean;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetSmooth() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setSmooth(CTBoolean smooth) {
        generatedSetterHelperImpl(smooth, PROPERTY_QNAME[11], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTBoolean addNewSmooth() {
        CTBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetSmooth() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            CTExtensionList target = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            cTExtensionList = target == null ? null : target;
        }
        return cTExtensionList;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void setExtLst(CTExtensionList extLst) {
        generatedSetterHelperImpl(extLst, PROPERTY_QNAME[12], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public CTExtensionList addNewExtLst() {
        CTExtensionList target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer
    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }
}
