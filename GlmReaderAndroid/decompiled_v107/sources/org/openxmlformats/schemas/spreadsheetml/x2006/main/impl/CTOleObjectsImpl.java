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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;

/* loaded from: classes12.dex */
public class CTOleObjectsImpl extends XmlComplexContentImpl implements CTOleObjects {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "oleObject")};
    private static final long serialVersionUID = 1;

    public CTOleObjectsImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public List<CTOleObject> getOleObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTOleObjectsImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOleObjectsImpl.this.getOleObjectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTOleObjectsImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTOleObjectsImpl.this.setOleObjectArray(((Integer) obj).intValue(), (CTOleObject) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTOleObjectsImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTOleObjectsImpl.this.insertNewOleObject(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTOleObjectsImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTOleObjectsImpl.this.removeOleObject(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTOleObjectsImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTOleObjectsImpl.this.sizeOfOleObjectArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public CTOleObject[] getOleObjectArray() {
        return (CTOleObject[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTOleObject[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public CTOleObject getOleObjectArray(int i) {
        CTOleObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOleObject) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public int sizeOfOleObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public void setOleObjectArray(CTOleObject[] oleObjectArray) {
        check_orphaned();
        arraySetterHelper(oleObjectArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public void setOleObjectArray(int i, CTOleObject oleObject) {
        generatedSetterHelperImpl(oleObject, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public CTOleObject insertNewOleObject(int i) {
        CTOleObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOleObject) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public CTOleObject addNewOleObject() {
        CTOleObject target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOleObject) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects
    public void removeOleObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
