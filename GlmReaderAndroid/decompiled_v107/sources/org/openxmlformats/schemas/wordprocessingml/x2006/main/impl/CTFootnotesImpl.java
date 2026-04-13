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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdn;

/* loaded from: classes12.dex */
public class CTFootnotesImpl extends XmlComplexContentImpl implements CTFootnotes {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_WORDPROCESSINGML, "footnote")};
    private static final long serialVersionUID = 1;

    public CTFootnotesImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public List<CTFtnEdn> getFootnoteList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFootnotesImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFootnotesImpl.this.getFootnoteArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFootnotesImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTFootnotesImpl.this.setFootnoteArray(((Integer) obj).intValue(), (CTFtnEdn) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFootnotesImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTFootnotesImpl.this.insertNewFootnote(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFootnotesImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTFootnotesImpl.this.removeFootnote(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTFootnotesImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTFootnotesImpl.this.sizeOfFootnoteArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public CTFtnEdn[] getFootnoteArray() {
        return (CTFtnEdn[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTFtnEdn[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public CTFtnEdn getFootnoteArray(int i) {
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

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public int sizeOfFootnoteArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public void setFootnoteArray(CTFtnEdn[] footnoteArray) {
        check_orphaned();
        arraySetterHelper(footnoteArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public void setFootnoteArray(int i, CTFtnEdn footnote) {
        generatedSetterHelperImpl(footnote, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public CTFtnEdn insertNewFootnote(int i) {
        CTFtnEdn target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdn) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public CTFtnEdn addNewFootnote() {
        CTFtnEdn target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTFtnEdn) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFootnotes
    public void removeFootnote(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
