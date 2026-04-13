package org.openxmlformats.schemas.presentationml.x2006.main.impl;

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
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList;

/* loaded from: classes11.dex */
public class CTTLTimeConditionListImpl extends XmlComplexContentImpl implements CTTLTimeConditionList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cond")};
    private static final long serialVersionUID = 1;

    public CTTLTimeConditionListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public List<CTTLTimeCondition> getCondList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTLTimeConditionListImpl.this.getCondArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTTLTimeConditionListImpl.this.setCondArray(((Integer) obj).intValue(), (CTTLTimeCondition) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTTLTimeConditionListImpl.this.insertNewCond(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTTLTimeConditionListImpl.this.removeCond(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTTLTimeConditionListImpl.this.sizeOfCondArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public CTTLTimeCondition[] getCondArray() {
        return (CTTLTimeCondition[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTTLTimeCondition[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public CTTLTimeCondition getCondArray(int i) {
        CTTLTimeCondition target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeCondition) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public int sizeOfCondArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public void setCondArray(CTTLTimeCondition[] condArray) {
        check_orphaned();
        arraySetterHelper(condArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public void setCondArray(int i, CTTLTimeCondition cond) {
        generatedSetterHelperImpl(cond, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public CTTLTimeCondition insertNewCond(int i) {
        CTTLTimeCondition target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeCondition) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public CTTLTimeCondition addNewCond() {
        CTTLTimeCondition target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTTLTimeCondition) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeConditionList
    public void removeCond(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
