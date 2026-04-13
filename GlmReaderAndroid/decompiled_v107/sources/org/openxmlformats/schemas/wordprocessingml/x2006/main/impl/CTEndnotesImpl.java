package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;

/* loaded from: classes12.dex */
public class CTEndnotesImpl extends XmlComplexContentImpl implements CTEndnotes {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "endnote")};
    private static final long serialVersionUID = 1;

    public CTEndnotesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public List<CTFtnEdn> getEndnoteList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTEndnotesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEndnotesImpl.this.getEndnoteArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTEndnotesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEndnotesImpl.this.setEndnoteArray(((Integer) obj).intValue(), (CTFtnEdn) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTEndnotesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEndnotesImpl.this.insertNewEndnote(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTEndnotesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEndnotesImpl.this.removeEndnote(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTEndnotesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEndnotesImpl.this.sizeOfEndnoteArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public CTFtnEdn[] getEndnoteArray() {
        return (CTFtnEdn[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTFtnEdn[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public CTFtnEdn getEndnoteArray(int i) {
        CTFtnEdn target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdn) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public int sizeOfEndnoteArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public void setEndnoteArray(CTFtnEdn[] endnoteArray) {
        check_orphaned();
        arraySetterHelper(endnoteArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public void setEndnoteArray(int i, CTFtnEdn endnote) {
        generatedSetterHelperImpl(endnote, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public CTFtnEdn insertNewEndnote(int i) {
        CTFtnEdn target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdn) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public CTFtnEdn addNewEndnote() {
        CTFtnEdn target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdn) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEndnotes
    public void removeEndnote(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
