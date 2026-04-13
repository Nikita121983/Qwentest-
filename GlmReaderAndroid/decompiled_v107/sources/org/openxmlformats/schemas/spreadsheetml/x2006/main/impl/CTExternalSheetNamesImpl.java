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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;

/* loaded from: classes12.dex */
public class CTExternalSheetNamesImpl extends XmlComplexContentImpl implements CTExternalSheetNames {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetName")};
    private static final long serialVersionUID = 1;

    public CTExternalSheetNamesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public List<CTExternalSheetName> getSheetNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalSheetNamesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTExternalSheetNamesImpl.this.getSheetNameArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalSheetNamesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTExternalSheetNamesImpl.this.setSheetNameArray(((Integer) obj).intValue(), (CTExternalSheetName) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalSheetNamesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTExternalSheetNamesImpl.this.insertNewSheetName(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalSheetNamesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTExternalSheetNamesImpl.this.removeSheetName(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTExternalSheetNamesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTExternalSheetNamesImpl.this.sizeOfSheetNameArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public CTExternalSheetName[] getSheetNameArray() {
        return (CTExternalSheetName[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTExternalSheetName[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public CTExternalSheetName getSheetNameArray(int i) {
        CTExternalSheetName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalSheetName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public int sizeOfSheetNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public void setSheetNameArray(CTExternalSheetName[] sheetNameArray) {
        check_orphaned();
        arraySetterHelper(sheetNameArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public void setSheetNameArray(int i, CTExternalSheetName sheetName) {
        generatedSetterHelperImpl(sheetName, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public CTExternalSheetName insertNewSheetName(int i) {
        CTExternalSheetName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalSheetName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public CTExternalSheetName addNewSheetName() {
        CTExternalSheetName target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTExternalSheetName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames
    public void removeSheetName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
