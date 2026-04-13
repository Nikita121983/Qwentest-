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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames;

/* loaded from: classes12.dex */
public class CTExternalDefinedNamesImpl extends XmlComplexContentImpl implements CTExternalDefinedNames {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "definedName")};
    private static final long serialVersionUID = 1;

    public CTExternalDefinedNamesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public List<CTExternalDefinedName> getDefinedNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalDefinedNamesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTExternalDefinedNamesImpl.this.getDefinedNameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalDefinedNamesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTExternalDefinedNamesImpl.this.setDefinedNameArray(((Integer) obj).intValue(), (CTExternalDefinedName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalDefinedNamesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTExternalDefinedNamesImpl.this.insertNewDefinedName(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalDefinedNamesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTExternalDefinedNamesImpl.this.removeDefinedName(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalDefinedNamesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTExternalDefinedNamesImpl.this.sizeOfDefinedNameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public CTExternalDefinedName[] getDefinedNameArray() {
        return (CTExternalDefinedName[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTExternalDefinedName[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public CTExternalDefinedName getDefinedNameArray(int i) {
        CTExternalDefinedName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalDefinedName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public int sizeOfDefinedNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public void setDefinedNameArray(CTExternalDefinedName[] definedNameArray) {
        check_orphaned();
        arraySetterHelper(definedNameArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public void setDefinedNameArray(int i, CTExternalDefinedName definedName) {
        generatedSetterHelperImpl(definedName, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public CTExternalDefinedName insertNewDefinedName(int i) {
        CTExternalDefinedName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalDefinedName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public CTExternalDefinedName addNewDefinedName() {
        CTExternalDefinedName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalDefinedName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames
    public void removeDefinedName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
