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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges;

/* loaded from: classes12.dex */
public class CTProtectedRangesImpl extends XmlComplexContentImpl implements CTProtectedRanges {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "protectedRange")};
    private static final long serialVersionUID = 1;

    public CTProtectedRangesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public List<CTProtectedRange> getProtectedRangeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTProtectedRangesImpl.this.getProtectedRangeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTProtectedRangesImpl.this.setProtectedRangeArray(((Integer) obj).intValue(), (CTProtectedRange) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTProtectedRangesImpl.this.insertNewProtectedRange(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTProtectedRangesImpl.this.removeProtectedRange(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTProtectedRangesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTProtectedRangesImpl.this.sizeOfProtectedRangeArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public CTProtectedRange[] getProtectedRangeArray() {
        return (CTProtectedRange[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTProtectedRange[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public CTProtectedRange getProtectedRangeArray(int i) {
        CTProtectedRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProtectedRange) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public int sizeOfProtectedRangeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public void setProtectedRangeArray(CTProtectedRange[] protectedRangeArray) {
        check_orphaned();
        arraySetterHelper(protectedRangeArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public void setProtectedRangeArray(int i, CTProtectedRange protectedRange) {
        generatedSetterHelperImpl(protectedRange, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public CTProtectedRange insertNewProtectedRange(int i) {
        CTProtectedRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProtectedRange) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public CTProtectedRange addNewProtectedRange() {
        CTProtectedRange target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTProtectedRange) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges
    public void removeProtectedRange(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
