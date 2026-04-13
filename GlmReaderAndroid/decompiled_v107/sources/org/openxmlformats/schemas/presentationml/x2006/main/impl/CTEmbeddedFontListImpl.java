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
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;

/* loaded from: classes11.dex */
public class CTEmbeddedFontListImpl extends XmlComplexContentImpl implements CTEmbeddedFontList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_PRESENTATIONML, "embeddedFont")};
    private static final long serialVersionUID = 1;

    public CTEmbeddedFontListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public List<CTEmbeddedFontListEntry> getEmbeddedFontList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTEmbeddedFontListImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEmbeddedFontListImpl.this.getEmbeddedFontArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTEmbeddedFontListImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTEmbeddedFontListImpl.this.setEmbeddedFontArray(((Integer) obj).intValue(), (CTEmbeddedFontListEntry) obj2);
                }
            }, new Function() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTEmbeddedFontListImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTEmbeddedFontListImpl.this.insertNewEmbeddedFont(((Integer) obj).intValue());
                }
            }, new Consumer() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTEmbeddedFontListImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CTEmbeddedFontListImpl.this.removeEmbeddedFont(((Integer) obj).intValue());
                }
            }, new Supplier() { // from class: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTEmbeddedFontListImpl$$ExternalSyntheticLambda4
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Integer.valueOf(CTEmbeddedFontListImpl.this.sizeOfEmbeddedFontArray());
                }
            });
        }
        return javaListXmlObject;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public CTEmbeddedFontListEntry[] getEmbeddedFontArray() {
        return (CTEmbeddedFontListEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], new CTEmbeddedFontListEntry[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public CTEmbeddedFontListEntry getEmbeddedFontArray(int i) {
        CTEmbeddedFontListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontListEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public int sizeOfEmbeddedFontArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public void setEmbeddedFontArray(CTEmbeddedFontListEntry[] embeddedFontArray) {
        check_orphaned();
        arraySetterHelper(embeddedFontArray, PROPERTY_QNAME[0]);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public void setEmbeddedFontArray(int i, CTEmbeddedFontListEntry embeddedFont) {
        generatedSetterHelperImpl(embeddedFont, PROPERTY_QNAME[0], i, (short) 2);
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public CTEmbeddedFontListEntry insertNewEmbeddedFont(int i) {
        CTEmbeddedFontListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontListEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public CTEmbeddedFontListEntry addNewEmbeddedFont() {
        CTEmbeddedFontListEntry target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTEmbeddedFontListEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList
    public void removeEmbeddedFont(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
