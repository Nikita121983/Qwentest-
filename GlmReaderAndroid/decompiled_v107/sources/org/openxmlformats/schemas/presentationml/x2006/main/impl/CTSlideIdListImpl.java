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
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;

/* loaded from: classes11.dex */
public class CTSlideIdListImpl extends XmlComplexContentImpl implements CTSlideIdList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "sldId")};
    private static final long serialVersionUID = 1;

    public CTSlideIdListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public List<CTSlideIdListEntry> getSldIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideIdListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSlideIdListImpl.this.getSldIdArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideIdListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTSlideIdListImpl.this.setSldIdArray(((Integer) obj).intValue(), (CTSlideIdListEntry) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideIdListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTSlideIdListImpl.this.insertNewSldId(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideIdListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTSlideIdListImpl.this.removeSldId(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideIdListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTSlideIdListImpl.this.sizeOfSldIdArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public CTSlideIdListEntry[] getSldIdArray() {
        return (CTSlideIdListEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTSlideIdListEntry[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public CTSlideIdListEntry getSldIdArray(int i) {
        CTSlideIdListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideIdListEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public int sizeOfSldIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public void setSldIdArray(CTSlideIdListEntry[] sldIdArray) {
        check_orphaned();
        arraySetterHelper(sldIdArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public void setSldIdArray(int i, CTSlideIdListEntry sldId) {
        generatedSetterHelperImpl(sldId, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public CTSlideIdListEntry insertNewSldId(int i) {
        CTSlideIdListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideIdListEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public CTSlideIdListEntry addNewSldId() {
        CTSlideIdListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTSlideIdListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdList
    public void removeSldId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
