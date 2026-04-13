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
import org.openxmlformats.schemas.presentationml.x2006.main.CTComment;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList;

/* loaded from: classes11.dex */
public class CTCommentListImpl extends XmlComplexContentImpl implements CTCommentList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cm")};
    private static final long serialVersionUID = 1;

    public CTCommentListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public List<CTComment> getCmList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCommentListImpl.this.getCmArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTCommentListImpl.this.setCmArray(((Integer) obj).intValue(), (CTComment) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCommentListImpl.this.insertNewCm(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTCommentListImpl.this.removeCm(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTCommentListImpl.this.sizeOfCmArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public CTComment[] getCmArray() {
        return (CTComment[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTComment[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public CTComment getCmArray(int i) {
        CTComment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComment) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public int sizeOfCmArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public void setCmArray(CTComment[] cmArray) {
        check_orphaned();
        arraySetterHelper(cmArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public void setCmArray(int i, CTComment cm) {
        generatedSetterHelperImpl(cm, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public CTComment insertNewCm(int i) {
        CTComment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComment) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public CTComment addNewCm() {
        CTComment target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTComment) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentList
    public void removeCm(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
