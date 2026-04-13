package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;

/* loaded from: classes12.dex */
public class CTDefinedNamesImpl extends XmlComplexContentImpl implements CTDefinedNames {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "definedName")};
    private static final long serialVersionUID = 1;

    public CTDefinedNamesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public List<CTDefinedName> getDefinedNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDefinedNamesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDefinedNamesImpl.this.getDefinedNameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDefinedNamesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTDefinedNamesImpl.this.setDefinedNameArray(((Integer) obj).intValue(), (CTDefinedName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDefinedNamesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTDefinedNamesImpl.this.insertNewDefinedName(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDefinedNamesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTDefinedNamesImpl.this.removeDefinedName(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDefinedNamesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTDefinedNamesImpl.this.sizeOfDefinedNameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public CTDefinedName[] getDefinedNameArray() {
        return (CTDefinedName[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTDefinedName[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public CTDefinedName getDefinedNameArray(int i) {
        CTDefinedName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDefinedName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public int sizeOfDefinedNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public void setDefinedNameArray(CTDefinedName[] definedNameArray) {
        check_orphaned();
        arraySetterHelper(definedNameArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public void setDefinedNameArray(int i, CTDefinedName definedName) {
        generatedSetterHelperImpl(definedName, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public CTDefinedName insertNewDefinedName(int i) {
        CTDefinedName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDefinedName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public CTDefinedName addNewDefinedName() {
        CTDefinedName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTDefinedName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames
    public void removeDefinedName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
