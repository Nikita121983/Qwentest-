package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

/* loaded from: classes11.dex */
public class CTSerTxImpl extends XmlComplexContentImpl implements CTSerTx {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "strRef"), new QName(XSSFRelation.NS_CHART, "v")};
    private static final long serialVersionUID = 1;

    public CTSerTxImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public CTStrRef getStrRef() {
        CTStrRef cTStrRef;
        synchronized (monitor()) {
            check_orphaned();
            CTStrRef target = (CTStrRef) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTStrRef = target == null ? null : target;
        }
        return cTStrRef;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public boolean isSetStrRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public void setStrRef(CTStrRef strRef) {
        generatedSetterHelperImpl(strRef, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public CTStrRef addNewStrRef() {
        CTStrRef target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTStrRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public void unsetStrRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public String getV() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public STXstring xgetV() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public boolean isSetV() {
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

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public void setV(String v) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(v);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public void xsetV(STXstring v) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STXstring) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(v);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.chart.CTSerTx
    public void unsetV() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
