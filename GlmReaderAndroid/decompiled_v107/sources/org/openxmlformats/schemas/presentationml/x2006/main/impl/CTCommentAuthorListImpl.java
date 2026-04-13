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
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthor;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList;

/* loaded from: classes11.dex */
public class CTCommentAuthorListImpl extends XmlComplexContentImpl implements CTCommentAuthorList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "cmAuthor")};
    private static final long serialVersionUID = 1;

    public CTCommentAuthorListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public List<CTCommentAuthor> getCmAuthorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentAuthorListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCommentAuthorListImpl.this.getCmAuthorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentAuthorListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTCommentAuthorListImpl.this.setCmAuthorArray(((Integer) obj).intValue(), (CTCommentAuthor) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentAuthorListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTCommentAuthorListImpl.this.insertNewCmAuthor(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentAuthorListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTCommentAuthorListImpl.this.removeCmAuthor(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTCommentAuthorListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTCommentAuthorListImpl.this.sizeOfCmAuthorArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public CTCommentAuthor[] getCmAuthorArray() {
        return (CTCommentAuthor[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTCommentAuthor[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public CTCommentAuthor getCmAuthorArray(int i) {
        CTCommentAuthor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommentAuthor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public int sizeOfCmAuthorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public void setCmAuthorArray(CTCommentAuthor[] cmAuthorArray) {
        check_orphaned();
        arraySetterHelper(cmAuthorArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public void setCmAuthorArray(int i, CTCommentAuthor cmAuthor) {
        generatedSetterHelperImpl(cmAuthor, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public CTCommentAuthor insertNewCmAuthor(int i) {
        CTCommentAuthor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommentAuthor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public CTCommentAuthor addNewCmAuthor() {
        CTCommentAuthor target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTCommentAuthor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTCommentAuthorList
    public void removeCmAuthor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
