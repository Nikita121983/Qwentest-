package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;

/* loaded from: classes12.dex */
public class CTRstImpl extends XmlComplexContentImpl implements CTRst {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "t"), new QName(XSSFRelation.NS_SPREADSHEETML, "r"), new QName(XSSFRelation.NS_SPREADSHEETML, "rPh"), new QName(XSSFRelation.NS_SPREADSHEETML, "phoneticPr")};
    private static final long serialVersionUID = 1;

    public CTRstImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public String getT() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public STXstring xgetT() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public boolean isSetT() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void setT(String t) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(t);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void xsetT(STXstring t) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STXstring) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(t);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void unsetT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public List<CTRElt> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda5
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRstImpl.this.getRArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda6
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRstImpl.this.setRArray(((Integer) obj).intValue(), (CTRElt) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRstImpl.this.insertNewR(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRstImpl.this.removeR(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRstImpl.this.sizeOfRArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTRElt[] getRArray() {
        return (CTRElt[]) getXmlObjectArray(PROPERTY_QNAME[1], new CTRElt[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTRElt getRArray(int i) {
        CTRElt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRElt) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void setRArray(CTRElt[] rArray) {
        check_orphaned();
        arraySetterHelper(rArray, PROPERTY_QNAME[1]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void setRArray(int i, CTRElt r) {
        generatedSetterHelperImpl(r, PROPERTY_QNAME[1], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTRElt insertNewR(int i) {
        CTRElt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRElt) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTRElt addNewR() {
        CTRElt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTRElt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public List<CTPhoneticRun> getRPhList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRstImpl.this.getRPhArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTRstImpl.this.setRPhArray(((Integer) obj).intValue(), (CTPhoneticRun) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTRstImpl.this.insertNewRPh(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTRstImpl.this.removeRPh(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTRstImpl.this.sizeOfRPhArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTPhoneticRun[] getRPhArray() {
        return (CTPhoneticRun[]) getXmlObjectArray(PROPERTY_QNAME[2], new CTPhoneticRun[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTPhoneticRun getRPhArray(int i) {
        CTPhoneticRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPhoneticRun) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public int sizeOfRPhArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void setRPhArray(CTPhoneticRun[] rPhArray) {
        check_orphaned();
        arraySetterHelper(rPhArray, PROPERTY_QNAME[2]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void setRPhArray(int i, CTPhoneticRun rPh) {
        generatedSetterHelperImpl(rPh, PROPERTY_QNAME[2], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTPhoneticRun insertNewRPh(int i) {
        CTPhoneticRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPhoneticRun) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTPhoneticRun addNewRPh() {
        CTPhoneticRun target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPhoneticRun) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void removeRPh(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTPhoneticPr getPhoneticPr() {
        CTPhoneticPr cTPhoneticPr;
        synchronized (monitor()) {
            check_orphaned();
            CTPhoneticPr target = (CTPhoneticPr) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTPhoneticPr = target == null ? null : target;
        }
        return cTPhoneticPr;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public boolean isSetPhoneticPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void setPhoneticPr(CTPhoneticPr phoneticPr) {
        generatedSetterHelperImpl(phoneticPr, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public CTPhoneticPr addNewPhoneticPr() {
        CTPhoneticPr target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTPhoneticPr) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst
    public void unsetPhoneticPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
